package perillaleaves.shop.response;

public class ItemPagingResponse {

    private Long item_id;

    private String name;

    private int price;

    public Long getItem_id() {
        return item_id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public ItemPagingResponse(Long item_id, String name, int price) {
        this.item_id = item_id;
        this.name = name;
        this.price = price;
    }

}
