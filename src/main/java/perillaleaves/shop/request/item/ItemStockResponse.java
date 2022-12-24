package perillaleaves.shop.request.item;

public class ItemStockResponse {

    private String name;
    private int stock;

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
