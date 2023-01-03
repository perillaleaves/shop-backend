package perillaleaves.shop.request.item;

import perillaleaves.shop.domain.enumList.Color;

public class ItemDTO {

    private Long item_id;
    private String name;
    private int price;

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


    public Color getColor() {
        return color;
    }

    public ItemDTO() {
    }

    public ItemDTO(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public ItemDTO(String name, int price, Color color) {
        this.name = name;
        this.price = price;
        this.color = color;
    }
}
