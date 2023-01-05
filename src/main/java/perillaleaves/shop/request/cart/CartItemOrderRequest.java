package perillaleaves.shop.request.cart;

import java.util.ArrayList;
import java.util.List;

public class CartItemOrderRequest {

    private String accessToken;
    private List<Long> cart_item_id = new ArrayList<>();

    public String getAccessToken() {
        return accessToken;
    }

    public List<Long> getCart_item_id() {
        return cart_item_id;
    }

}
