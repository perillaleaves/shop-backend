package perillaleaves.shop.request.item;

import perillaleaves.shop.domain.enumList.Color;
import perillaleaves.shop.domain.item.Item;

public class ItemColorDTO {

    private final Item item;

    private final Color color;

    public Item getItem() {
        return item;
    }

    public Color getColor() {
        return color;
    }


    public ItemColorDTO(Item item, Color color) {
        this.item = item;
        this.color = color;
    }
}
