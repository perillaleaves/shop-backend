package perillaleaves.shop.response.item;

import perillaleaves.shop.domain.Kinds;

public class ItemViewDetailsResponse {

    private final Long item_id;
    private final String name;
    private final int price;

    private final Kinds kinds;

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
