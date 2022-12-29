package perillaleaves.shop.response.item;

public class ItemViewDetailsResponse {

    private final Long item_id;
    private final String name;
    private final int price;


    public Long getItem_id() {
        return item_id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }


    public ItemViewDetailsResponse(Long item_id, String name, int price) {
        this.item_id = item_id;
        this.name = name;
        this.price = price;
    }
}
