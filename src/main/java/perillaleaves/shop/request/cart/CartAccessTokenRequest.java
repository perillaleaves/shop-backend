package perillaleaves.shop.request.cart;

public class CartAccessTokenRequest {

    private final String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public CartAccessTokenRequest(String accessToken) {
        this.accessToken = accessToken;
    }
}
