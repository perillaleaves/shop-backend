package perillaleaves.shop.request.item;

import perillaleaves.shop.domain.enumList.Color;

public class ItemColorRequest {

    private Color color;

    public Color getColor() {
        return color;
    }

    public ItemColorRequest() {
    }

    public ItemColorRequest(Color color) {
        this.color = color;
    }
}
