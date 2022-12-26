package perillaleaves.shop.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import perillaleaves.shop.domain.Item;
import perillaleaves.shop.exception.APIError;
import perillaleaves.shop.request.item.ItemDTO;
import perillaleaves.shop.request.item.ItemStockRequest;
import perillaleaves.shop.response.*;
import perillaleaves.shop.service.ItemService;

import java.util.ArrayList;
import java.util.Collection;
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
    public Response<PagingResponse> items() {
        Page<Item> items = itemService.findAll();
        List<ItemPagingResponse> itemResponse = new ArrayList<>();

        for (Item item : items) {
            ItemPagingResponse itemPagingResponse = new ItemPagingResponse(item.getId(), item.getName(), item.getPrice());
            itemResponse.add(itemPagingResponse);
        }

        return new Response<>(new PagingResponse(items.getNumber(),items.getTotalPages(), items.getNumberOfElements(), itemResponse));
    }


}
