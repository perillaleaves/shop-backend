package perillaleaves.shop.response.cart;

import perillaleaves.shop.domain.enumList.Color;

public class CartItemResponse {

    private final Long cart_item_id;
    private final String name;
    private final Color color;
    private final int count;
    private final int price;
    private final int totalPrice;

    public Long getCart_item_id() {
        return cart_item_id;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public int getCount() {
        return count;
    }

    public int getPrice() {
        return price;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public CartItemResponse(Long cart_item_id, String name, Color color, int count, int price, int totalPrice) {
        this.cart_item_id = cart_item_id;
        this.name = name;
        this.color = color;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
    }
}
