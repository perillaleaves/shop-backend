package perillaleaves.shop.response.orders;

import java.util.ArrayList;
import java.util.List;

public class OrdersResponse {

    private Long orders_id;
    private int order_count;
    private int order_price;
    private List<OrderItemResponse> orderItem_content = new ArrayList<>();

    public Long getOrders_id() {
        return orders_id;
    }

    public int getOrder_count() {
        return order_count;
    }

    public int getOrder_price() {
        return order_price;
    }

    public List<OrderItemResponse> getOrderItem_content() {
        return orderItem_content;
    }

    public OrdersResponse(Long orders_id, int order_count, int order_price, List<OrderItemResponse> orderItem_content) {
        this.orders_id = orders_id;
        this.order_count = order_count;
        this.order_price = order_price;
        this.orderItem_content = orderItem_content;
    }
}
