package perillaleaves.shop.controller;

import org.springframework.web.bind.annotation.*;
import perillaleaves.shop.domain.item.Item;
import perillaleaves.shop.domain.user.Token;
import perillaleaves.shop.exception.APIError;
import perillaleaves.shop.repository.TokenRepository;
import perillaleaves.shop.repository.UserRepository;
import perillaleaves.shop.request.cart.CartCreateRequest;
import perillaleaves.shop.response.ErrorResponse;
import perillaleaves.shop.response.Response;
import perillaleaves.shop.response.ValidateResponse;
import perillaleaves.shop.service.CartService;
import perillaleaves.shop.service.ItemService;

@RestController
public class CartController {

    private final UserRepository userRepository;
    private final CartService cartService;
    private final ItemService itemService;
    private final TokenRepository tokenRepository;

    public CartController(UserRepository userRepository, CartService cartService, ItemService itemService, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.cartService = cartService;
        this.itemService = itemService;
        this.tokenRepository = tokenRepository;
    }

    @PostMapping("/cart/{color_id}")
    public Response<ValidateResponse> create(@PathVariable("color_id") Long color_id, @RequestBody CartCreateRequest request) {

        try {
            cartService.addCart(request.getAccessToken(), color_id, request.getCount());

            return new Response<>(new ValidateResponse("gg", "gg"));
        } catch (APIError e) {
            return new Response<>(new ErrorResponse(e.getCode(), e.getMessage()));
        }

    }

}
