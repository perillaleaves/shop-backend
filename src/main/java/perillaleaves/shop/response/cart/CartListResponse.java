package perillaleaves.shop.response.cart;

import java.util.ArrayList;
import java.util.List;

public class CartListResponse {

    private List<CartItemResponse> cartItemResponseList = new ArrayList<>();

    public List<CartItemResponse> getCartItemResponseList() {
        return cartItemResponseList;
    }

    public CartListResponse(List<CartItemResponse> cartItemResponseList) {
        this.cartItemResponseList = cartItemResponseList;
    }
}
