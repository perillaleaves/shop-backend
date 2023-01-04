package perillaleaves.shop.request.cart;

public class CartCountRequest {

    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public CartCountRequest(String accessToken) {
        this.accessToken = accessToken;
    }
}
