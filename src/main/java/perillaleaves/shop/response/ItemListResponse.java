package perillaleaves.shop.response;

import perillaleaves.shop.domain.Kinds;

public class ItemListResponse {

    private final Long item_id;

    private final String name;

    private final int price;

    private final Kinds kind;

    public Long getItem_id() {
        return item_id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Kinds getKind() {
        return kind;
    }

    public ItemListResponse(Long item_id, String name, int price, Kinds kind) {
        this.item_id = item_id;
        this.name = name;
        this.price = price;
        this.kind = kind;
    }
}
