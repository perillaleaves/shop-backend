package perillaleaves.shop.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import perillaleaves.shop.domain.item.Item;
import perillaleaves.shop.exception.APIError;
import perillaleaves.shop.response.ErrorResponse;
import perillaleaves.shop.response.Response;
import perillaleaves.shop.response.ValidateResponse;
import perillaleaves.shop.service.CartService;
import perillaleaves.shop.service.ItemService;

@RestController
public class CartController {

    private final CartService cartService;
    private final ItemService itemService;

    public CartController(CartService cartService, ItemService itemService) {
        this.cartService = cartService;
        this.itemService = itemService;
    }

    @PostMapping("/create")
    public Response<ValidateResponse> create(String accessToken, @PathVariable("item_id") Long item_id, int count) {

        try {
            Item item = itemService.findById(item_id);
            cartService.addCart(accessToken, item, count);

            return new Response<>(new ValidateResponse("", ""));
        } catch (APIError e) {
            return new Response<>(new ErrorResponse(e.getCode(), e.getMessage()));
        }

    }

}
