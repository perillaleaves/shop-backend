package perillaleaves.shop.response.item;

import perillaleaves.shop.domain.enumList.Color;

public class ItemColorListResponse {

    private final Long item_color_id;
    private final Color color;
    private int stock;

    public Long getItem_color_id() {
        return item_color_id;
    }

    public Color getColor() {
        return color;
    }

    public int getStock() {
        return stock;
    }

    public ItemColorListResponse(Long item_color_id, Color color) {
        this.item_color_id = item_color_id;
        this.color = color;
    }

    public ItemColorListResponse(Long item_color_id, Color color, int stock) {
        this.item_color_id = item_color_id;
        this.color = color;
        this.stock = stock;
    }
}
