package perillaleaves.shop.response.orders;

import perillaleaves.shop.domain.enumList.Color;

public class OrderItemResponse {

    private final Long item_color_id;
    private final String name;
    private final int price;
    private final Color color;
    private final int count;
    private final int totalPrice;

    public Long getItem_color_id() {
        return item_color_id;
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

    public int getCount() {
        return count;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public OrderItemResponse(Long item_color_id, String name, int price, Color color, int count, int totalPrice) {
        this.item_color_id = item_color_id;
        this.name = name;
        this.price = price;
        this.color = color;
        this.count = count;
        this.totalPrice = totalPrice;
    }
}
