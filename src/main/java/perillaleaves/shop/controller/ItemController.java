package perillaleaves.community.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import perillaleaves.community.domain.Item;
import perillaleaves.community.exception.APIError;
import perillaleaves.community.request.item.ItemDTO;
import perillaleaves.community.request.item.ItemStockResponse;
import perillaleaves.community.response.ErrorResponse;
import perillaleaves.community.response.Response;
import perillaleaves.community.response.ValidateResponse;
import perillaleaves.community.service.ItemService;

@RestController
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // 10. 상품 등록
    @PostMapping
    public Response<ValidateResponse> save(ItemDTO itemDTO) {
        try {
            itemService.create(itemDTO);
            return new Response<>(new ValidateResponse("save", " 아이템 등록"));
        } catch (APIError e) {
            return new Response<>(new ErrorResponse(e.getCode(), e.getMessage()));
        }
    }

    // 11. 재고 파악
    @PutMapping("/{item_id}/stock")
    public Response<ItemStockResponse> updatedStock(@PathVariable("item_id") Long item_id, int stock) {
        try {
            Item item = itemService.stockUpdate(item_id, stock);
            return new Response<>(new ItemStockResponse(item.getName(), item.getStock()));
        } catch (APIError e) {
            return new Response<>(new ErrorResponse(e.getCode(), e.getMessage()));
        }
    }


}
