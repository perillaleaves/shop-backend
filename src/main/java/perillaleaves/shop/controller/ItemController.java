package perillaleaves.shop.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import perillaleaves.shop.domain.Item;
import perillaleaves.shop.domain.enumList.Kinds;
import perillaleaves.shop.exception.APIError;
import perillaleaves.shop.request.item.ItemDTO;
import perillaleaves.shop.request.item.ItemStockRequest;
import perillaleaves.shop.response.ErrorResponse;
import perillaleaves.shop.response.Response;
import perillaleaves.shop.response.ValidateResponse;
import perillaleaves.shop.response.item.ItemListResponse;
import perillaleaves.shop.response.item.ItemStockResponse;
import perillaleaves.shop.response.item.ItemViewDetailsResponse;
import perillaleaves.shop.response.item.PagingResponse;
import perillaleaves.shop.service.ItemService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // 10. 상품 등록
    @PostMapping("/item")
    public Response<ValidateResponse> save(@RequestBody ItemDTO itemDTO) {
        try {
            itemService.create(itemDTO);
            return new Response<>(new ValidateResponse("save", " 아이템 등록"));
        } catch (APIError e) {
            return new Response<>(new ErrorResponse(e.getCode(), e.getMessage()));
        }
    }

    // 11. 재고 파악
    @PutMapping("/{item_id}")
    public Response<ItemStockResponse> updatedStock(@PathVariable("item_id") Long item_id, @RequestBody ItemStockRequest request) {
        try {
            Item item = itemService.update(item_id, request.getStock());
            return new Response<>(new ItemStockResponse(item.getName(), item.getStock()));
        } catch (APIError e) {
            return new Response<>(new ErrorResponse(e.getCode(), e.getMessage()));
        }
    }

    // 12. 상품 전체 리스트(paging)
    @GetMapping("/items")
    public Response<PagingResponse> findItems(@PageableDefault(page = 0, size = 4, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Item> items = itemService.findAll(pageable);
        List<ItemListResponse> itemResponse = new ArrayList<>();

        for (Item item : items) {
            ItemListResponse itemListResponse = new ItemListResponse(item.getId(), item.getName(), item.getPrice(), item.getKind());
            itemResponse.add(itemListResponse);
        }

        return new Response<>(new PagingResponse(items.getNumber(), items.getTotalPages(), items.getNumberOfElements(), itemResponse));
    }

    // 13. 상품 상세보기
    @GetMapping("/item/{item_id}")
    public Response<ItemViewDetailsResponse> findItem(@PathVariable("item_id") Long item_id) {
        Item item = itemService.findById(item_id);

        return new Response<>(new ItemViewDetailsResponse(item.getId(), item.getName(), item.getPrice(), item.getKind()));
    }

    // 14. 특정 카테고리 상품 전체 조회
    @GetMapping("/{kind}")
    public Response<PagingResponse> findItemsByKind(@PathVariable("kind") Kinds kind
            , @PageableDefault(page = 0, size = 4, direction = Sort.Direction.DESC) Pageable pageable) {

        Page<Item> items = itemService.findAllByKind(kind, pageable);
        List<ItemListResponse> itemResponse = new ArrayList<>();
        for (Item item : items) {
            ItemListResponse itemListResponse = new ItemListResponse(item.getId(), item.getName(), item.getPrice(), item.getKind());
            itemResponse.add(itemListResponse);
        }

        return new Response<>(new PagingResponse(items.getNumber(), items.getTotalPages(), items.getNumberOfElements(), itemResponse));
    }

}
