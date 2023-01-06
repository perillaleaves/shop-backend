package perillaleaves.shop.response.orders;

import java.util.ArrayList;
import java.util.List;

public class OrderListResponse {

    private List<OrdersResponse> content = new ArrayList<>();

    public List<OrdersResponse> getContent() {
        return content;
    }

    public OrderListResponse(List<OrdersResponse> content) {
        this.content = content;
    }
}
