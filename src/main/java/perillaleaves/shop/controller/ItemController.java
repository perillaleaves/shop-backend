package perillaleaves.shop.controller;

import org.springframework.web.bind.annotation.*;
import perillaleaves.shop.domain.Item;
import perillaleaves.shop.exception.APIError;
import perillaleaves.shop.request.item.ItemDTO;
import perillaleaves.shop.request.item.ItemStockRequest;
import perillaleaves.shop.response.ItemStockResponse;
import perillaleaves.shop.response.ErrorResponse;
import perillaleaves.shop.response.Response;
import perillaleaves.shop.response.ValidateResponse;
import perillaleaves.shop.service.ItemService;

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


}
