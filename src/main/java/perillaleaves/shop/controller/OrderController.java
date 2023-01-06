package perillaleaves.shop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import perillaleaves.shop.exception.APIError;
import perillaleaves.shop.request.cart.CartItemOrderRequest;
import perillaleaves.shop.response.ErrorResponse;
import perillaleaves.shop.response.Response;
import perillaleaves.shop.response.ValidateResponse;
import perillaleaves.shop.service.OrderService;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
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
//    @GetMapping("/")
//    public Response<ValidateResponse> orderList() {
//        try {
//
//            return new Response<>(new ValidateResponse("", ""));
//        } catch (APIError e) {
//            return new Response<>(new ErrorResponse(e.getCode(), e.getMessage()));
//        }
//    }

}
