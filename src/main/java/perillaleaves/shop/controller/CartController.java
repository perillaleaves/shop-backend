package perillaleaves.shop.controller;

import org.springframework.web.bind.annotation.*;
import perillaleaves.shop.domain.item.Cart;
import perillaleaves.shop.domain.item.CartItem;
import perillaleaves.shop.exception.APIError;
import perillaleaves.shop.repository.CartItemRepository;
import perillaleaves.shop.repository.TokenRepository;
import perillaleaves.shop.repository.UserRepository;
import perillaleaves.shop.request.cart.CartAccessTokenRequest;
import perillaleaves.shop.request.cart.CartCountRequest;
import perillaleaves.shop.request.cart.CartCreateRequest;
import perillaleaves.shop.response.ErrorResponse;
import perillaleaves.shop.response.Response;
import perillaleaves.shop.response.ValidateResponse;
import perillaleaves.shop.response.cart.CartCountResponse;
import perillaleaves.shop.response.cart.CartItemResponse;
import perillaleaves.shop.response.cart.CartListResponse;
import perillaleaves.shop.service.CartService;
import perillaleaves.shop.service.ItemService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CartController {

    private final UserRepository userRepository;
    private final CartService cartService;
    private final ItemService itemService;
    private final TokenRepository tokenRepository;
    private final CartItemRepository cartItemRepository;

    public CartController(UserRepository userRepository, CartService cartService, ItemService itemService, TokenRepository tokenRepository, CartItemRepository cartItemRepository) {
        this.userRepository = userRepository;
        this.cartService = cartService;
        this.itemService = itemService;
        this.tokenRepository = tokenRepository;
        this.cartItemRepository = cartItemRepository;
    }

    // 16. 장바구니 등록
    @PostMapping("/cart/{color_id}")
    public Response<ValidateResponse> create(@PathVariable("color_id") Long color_id, @RequestBody CartCreateRequest request) {
        try {
            cartService.addCart(request.getAccessToken(), color_id, request.getCount());
            return new Response<>(new ValidateResponse("addCart", "장바구니 추가"));
        } catch (APIError e) {
            return new Response<>(new ErrorResponse(e.getCode(), e.getMessage()));
        }
    }

    // 17. 장바구니 목록 조회
    @GetMapping("/cart/{accessToken}")
    public Response<CartListResponse> getCartList(@PathVariable("accessToken") String accessToken) {
        try {
            Cart cart = cartService.findCartList(accessToken);
            List<CartItem> cartList = cartItemRepository.findByCart(cart);
            List<CartItemResponse> cartResponse = new ArrayList<>();
            for (CartItem cartItem : cartList) {
                CartItemResponse cartItemResponse = new CartItemResponse(cartItem.getId(),
                        cartItem.getItemColor().getItem().getName(),
                        cartItem.getItemColor().getColor(),
                        cartItem.getCount(),
                        cartItem.getItemColor().getItem().getPrice(),
                        cartItem.getTotalPrice());

                cartResponse.add(cartItemResponse);
            }
            return new Response<>(new CartListResponse(cartResponse));
        } catch (APIError e) {
            return new Response<>(new ErrorResponse(e.getCode(), e.getMessage()));
        }
    }

    // 18. 장바구니 삭제
    @DeleteMapping("/{accessToken}/cartitem/{cart_item_id}")
    public Response<ValidateResponse> deleteCart(@PathVariable("accessToken") String accessToken,
                                                 @PathVariable("cart_item_id") Long cart_item_id) {
        try {
            cartService.deleteCart(accessToken, cart_item_id);
            return new Response<>(new ValidateResponse("delete", "삭제"));
        } catch (APIError e) {
            return new Response<>(new ErrorResponse(e.getCode(), e.getMessage()));
        }
    }

    // 19. 장바구니 수량 조회
    @GetMapping("/cart/count")
    public Response<CartCountResponse> countCart(@ModelAttribute CartAccessTokenRequest request) {
        try {
            List<CartItem> cartItems = cartService.countByCart(request.getAccessToken());
            return new Response<>(new CartCountResponse(cartItems.size()));
        } catch (APIError e) {
            return new Response<>(new ErrorResponse(e.getCode(), e.getMessage()));
        } catch (NullPointerException ne) {
            return new Response<>(new CartCountResponse(0));
        }
    }

    // 20. 장바구니 아이템 수량 변경
    @PutMapping("/cartitem/{cart_item_id}")
    public Response<ValidateResponse> ChangeItemByCartQuantity(@PathVariable("cart_item_id") Long cart_item_id,
                                                               @RequestBody CartCountRequest request) {
        try {
            cartService.updateByCartItemCount(cart_item_id, request.getCount());
            return new Response<>(new ValidateResponse("update", "수량 변경"));
        } catch (APIError e) {
            return new Response<>(new ErrorResponse(e.getCode(), e.getMessage()));
        }
    }


}
