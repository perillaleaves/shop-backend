package perillaleaves.shop.request.cart;

public class CartAccessTokenRequest {

    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public CartAccessTokenRequest(String accessToken) {
        this.accessToken = accessToken;
    }
}
