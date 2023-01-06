package perillaleaves.shop.controller;

import org.springframework.web.bind.annotation.*;
import perillaleaves.shop.domain.item.Item;
import perillaleaves.shop.domain.item.ItemColor;
import perillaleaves.shop.domain.item.OrderItem;
import perillaleaves.shop.domain.item.Orders;
import perillaleaves.shop.exception.APIError;
import perillaleaves.shop.repository.ItemColorRepository;
import perillaleaves.shop.repository.ItemRepository;
import perillaleaves.shop.request.cart.CartItemOrderRequest;
import perillaleaves.shop.response.ErrorResponse;
import perillaleaves.shop.response.Response;
import perillaleaves.shop.response.ValidateResponse;
import perillaleaves.shop.response.orders.OrderItemResponse;
import perillaleaves.shop.response.orders.OrderListResponse;
import perillaleaves.shop.response.orders.OrdersResponse;
import perillaleaves.shop.service.OrderItemService;
import perillaleaves.shop.service.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {

    private final OrderService orderService;
    private final OrderItemService orderItemService;
    private final ItemColorRepository itemColorRepository;
    private final ItemRepository itemRepository;
    public OrderController(OrderService orderService, OrderItemService orderItemService, ItemColorRepository itemColorRepository, ItemRepository itemRepository) {
        this.orderService = orderService;
        this.orderItemService = orderItemService;
        this.itemColorRepository = itemColorRepository;
        this.itemRepository = itemRepository;
    }


    // 23. 장바구니 상품 선택 주문
    @PostMapping("/orders")
    public Response<ValidateResponse> orderByCart(@RequestBody CartItemOrderRequest request) {
        try {
            orderService.selectionOrder(request.getAccessToken(), request.getCart_item_id().stream().toList());
            return new Response<>(new ValidateResponse("Order", "주문"));
        } catch (APIError e) {
            return new Response<>(new ErrorResponse(e.getCode(), e.getMessage()));
        }
    }

    // 24. 주문 리스트 조회 기능
    @GetMapping("/orders/{accessToken}")
    public Response<OrderListResponse> orderList(@PathVariable("accessToken") String accessToken) {
        try {
            List<Orders> orderList = orderService.getOrderList(accessToken);
            List<OrdersResponse> ordersResponses = new ArrayList<>();

            for (Orders orders : orderList) {
                List<OrderItem> orderItemList = orderItemService.findOrderItems(orders.getId());
                List<OrderItemResponse> orderItemResponses = new ArrayList<>();

                for (OrderItem orderItem : orderItemList) {
                    ItemColor itemColor = itemColorRepository.findById(orderItem.getItemColor().getId()).orElse(null);
                    Item item = itemRepository.findById(itemColor.getItem().getId()).orElse(null);
                    OrderItemResponse orderItemResponse = new OrderItemResponse(itemColor.getId(), item.getName(), item.getPrice(), itemColor.getColor(),orderItem.getCount(), orderItem.getTotalPrice());

                    orderItemResponses.add(orderItemResponse);
                }

                OrdersResponse orderResponse = new OrdersResponse(orders.getId(), orders.getOrder_count(), orders.getOrder_price(), orderItemResponses);
                ordersResponses.add(orderResponse);
            }

            return new Response<>(new OrderListResponse(ordersResponses));
        } catch (APIError e) {
            return new Response<>(new ErrorResponse(e.getCode(), e.getMessage()));
        }
    }

}
