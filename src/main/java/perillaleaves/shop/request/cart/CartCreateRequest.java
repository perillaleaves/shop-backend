package perillaleaves.shop.request.cart;

public class CartCreateRequest {

    private String accessToken;
    private int count;

    public String getAccessToken() {
        return accessToken;
    }

    public int getCount() {
        return count;
    }
}
