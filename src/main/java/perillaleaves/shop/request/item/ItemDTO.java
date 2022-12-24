package perillaleaves.shop.request.item;

import perillaleaves.shop.domain.Kinds;

public class ItemDTO {

    private String name;
    private int price;
    private int stock;
    private Kinds kind;

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public Kinds getKind() {
        return kind;
    }

    public ItemDTO() {
    }

    public ItemDTO(String name, int price, int stock, Kinds kind) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.kind = kind;
    }
}
