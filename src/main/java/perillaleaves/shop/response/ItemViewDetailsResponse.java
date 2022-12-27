package perillaleaves.shop.response;

import perillaleaves.shop.domain.Kinds;

public class ItemViewDetailsResponse {

    private Long item_id;
    private String name;
    private int price;

    private Kinds kinds;

    public Long getItem_id() {
        return item_id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Kinds getKinds() {
        return kinds;
    }

    public ItemViewDetailsResponse(Long item_id, String name, int price, Kinds kinds) {
        this.item_id = item_id;
        this.name = name;
        this.price = price;
        this.kinds = kinds;
    }
}
