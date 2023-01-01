package perillaleaves.shop.response.item;

import perillaleaves.shop.domain.enumList.Color;

public class ItemColorListResponse {

    private Long item_color_id;
    private Color color;

    public Long getItem_color_id() {
        return item_color_id;
    }

    public Color getColor() {
        return color;
    }

    public ItemColorListResponse(Long item_color_id, Color color) {
        this.item_color_id = item_color_id;
        this.color = color;
    }
}
