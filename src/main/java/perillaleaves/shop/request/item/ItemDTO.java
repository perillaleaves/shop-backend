package perillaleaves.shop.request.item;

import perillaleaves.shop.domain.enumList.Color;
import perillaleaves.shop.domain.enumList.Kinds;

public class ItemDTO {

    private Long item_id;

    private String name;
    private int price;
    private Kinds kind;

    private Color color;

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

    public Color getColor() {
        return color;
    }

    public ItemDTO() {
    }

    public ItemDTO(String name, int price, Kinds kind) {
        this.name = name;
        this.price = price;
        this.kind = kind;
    }
}
