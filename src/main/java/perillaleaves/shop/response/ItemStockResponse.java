package perillaleaves.shop.response;

public class ItemStockResponse {

    private final String name;
    private final int stock;

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public ItemStockResponse(String name, int stock) {
        this.name = name;
        this.stock = stock;
    }
}
