package perillaleaves.shop.response.orders;

import perillaleaves.shop.domain.enumList.Color;

public class OrderItemResponse {

    private Long item_color_id;
    private String name;
    private int price;
    private Color color;
    private int count;
    private int totalPrice;

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
