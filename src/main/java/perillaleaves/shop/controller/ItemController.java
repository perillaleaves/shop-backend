package perillaleaves.shop.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import perillaleaves.shop.domain.item.Item;
import perillaleaves.shop.domain.item.ItemColor;
import perillaleaves.shop.exception.APIError;
import perillaleaves.shop.request.item.ItemDTO;
import perillaleaves.shop.request.item.ItemStockRequest;
import perillaleaves.shop.response.ErrorResponse;
import perillaleaves.shop.response.Response;
import perillaleaves.shop.response.ValidateResponse;
import perillaleaves.shop.response.item.*;
import perillaleaves.shop.service.ItemColorService;
import perillaleaves.shop.service.ItemService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class ItemController {

    private final ItemService itemService;
    private final ItemColorService itemColorService;

    public ItemController(ItemService itemService, ItemColorService itemColorService) {
        this.itemService = itemService;
        this.itemColorService = itemColorService;
    }


    // 12. 상품 등록
    @PostMapping("/item")
    public Response<ValidateResponse> save(@RequestBody ItemDTO itemDTO) {
        try {
            itemService.create(itemDTO);
            return new Response<>(new ValidateResponse("save", " 아이템 등록"));
        } catch (APIError e) {
            return new Response<>(new ErrorResponse(e.getCode(), e.getMessage()));
        }
    }

    // 13. 재고 파악
    @PutMapping("/{color_id}")
    public Response<ValidateResponse> updatedStock(@PathVariable("color_id") Long color_id,
                                                   @RequestBody ItemStockRequest request) {
        try {
            itemService.update(color_id, request.getStock());
            return new Response<>(new ValidateResponse("updateStock", "재고 수정 완료"));
        } catch (APIError e) {
            return new Response<>(new ErrorResponse(e.getCode(), e.getMessage()));
        }
    }

    // 14. 상품 전체 리스트(paging)
    @GetMapping("/items")
    public Response<PagingResponse> findItems(@PageableDefault(page = 0, size = 4, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Item> items = itemService.findAll(pageable);
        List<ItemListResponse> itemResponse = new ArrayList<>();

        for (Item item : items) {
            List<ItemColor> itemColors = itemColorService.findAllByItemId(item.getId());
            List<ItemColorListResponse> itemColorResponse = new ArrayList<>();

            for (ItemColor itemColor : itemColors) {
                ItemColorListResponse itemColorListResponse = new ItemColorListResponse(itemColor.getId(), itemColor.getColor());
                itemColorResponse.add(itemColorListResponse);
            }

            ItemListResponse itemListResponse = new ItemListResponse(item.getId(), item.getName(), item.getPrice(), itemColorResponse);
            itemResponse.add(itemListResponse);

        }

        return new Response<>(new PagingResponse(
                items.getNumber(),
                items.getTotalPages(),
                items.getNumberOfElements(),
                itemResponse));
    }

    // 15. 상품 상세보기
    @GetMapping("/item/{item_id}")
    public Response<ItemViewDetailsResponse> findItem(@PathVariable("item_id") Long item_id) {
        Item item = itemService.findById(item_id);
        List<ItemColor> itemColorList = itemColorService.findAllByItem(item);
        List<ItemColorListResponse> itemColorResponses = new ArrayList<>();
        for (ItemColor itemColor : itemColorList) {
            ItemColorListResponse itemColorListResponse = new ItemColorListResponse(itemColor.getId(), itemColor.getColor());

            itemColorResponses.add(itemColorListResponse);
        }

        return new Response<>(new ItemViewDetailsResponse(item.getId(),
                item.getName(),
                item.getPrice(),
                itemColorResponses));
    }

    // 20. 재고 수정 리스트 조회
    @GetMapping("/items/stock")
    public Response<ItemStockListResponse> findItemStockList() {
        List<Item> items = itemService.findAll();
        List<ItemListResponse> itemListResponseList = new ArrayList<>();
        for (Item item : items) {
            List<ItemColor> itemColors = itemColorService.findAllByItemId(item.getId());
            List<ItemColorListResponse> itemColorResponse = new ArrayList<>();

            for (ItemColor itemColor : itemColors) {
                ItemColorListResponse itemColorListResponse = new ItemColorListResponse(itemColor.getId(), itemColor.getColor(), itemColor.getStock());
                itemColorResponse.add(itemColorListResponse);
            }

            ItemListResponse itemListResponse = new ItemListResponse(item.getId(), item.getName(), item.getPrice(), itemColorResponse);
            itemListResponseList.add(itemListResponse);
        }
        return new Response<>(new ItemStockListResponse(itemListResponseList));
    }

}
