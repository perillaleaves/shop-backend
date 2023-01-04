package perillaleaves.shop.request.cart;

public class CartCountRequest {

    private int count;

    public int getCount() {
        return count;
    }

    public CartCountRequest() {
    }

    public CartCountRequest(int count) {
        this.count = count;
    }
}
