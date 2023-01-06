package perillaleaves.shop.service;

import org.springframework.stereotype.Service;
import perillaleaves.shop.domain.item.Cart;
import perillaleaves.shop.domain.item.CartItem;
import perillaleaves.shop.domain.item.OrderItem;
import perillaleaves.shop.domain.item.Orders;
import perillaleaves.shop.domain.user.Token;
import perillaleaves.shop.domain.user.User;
import perillaleaves.shop.exception.APIError;
import perillaleaves.shop.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final TokenRepository tokenRepository;
    private final CartItemRepository cartItemRepository;
    private final OrdersRepository ordersRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(TokenRepository tokenRepository, CartItemRepository cartItemRepository, OrdersRepository ordersRepository, CartRepository cartRepository, UserRepository userRepository, OrderItemRepository orderItemRepository) {
        this.tokenRepository = tokenRepository;
        this.cartItemRepository = cartItemRepository;
        this.ordersRepository = ordersRepository;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.orderItemRepository = orderItemRepository;
    }


    public void selectionOrder(String accessToken, List<Long> cart_item_id) {
        if (accessToken.isBlank()) {
            throw new APIError("NotLogin", "로그인 유저가 아닙니다.");
        }
        Optional<Token> token = Optional.ofNullable(tokenRepository.findByToken(accessToken));
        if (token.isEmpty()) {
            throw new APIError("NotLogin", "로그인 유저가 아닙니다.");
        }
        User user = userRepository.findById(token.get().getUser_id()).orElse(null);
        Orders orders = new Orders(user);

        for (Long id : cart_item_id) {
            CartItem cartItem = cartItemRepository.findById(id).orElse(null);
            orders.setOrder_count(orders.getOrder_count() + cartItem.getCount());
            orders.setOrder_price(orders.getOrder_price() + cartItem.getTotalPrice());
            cartItem.getCart().setCount(cartItem.getCart().getCount() - cartItem.getCount());

            OrderItem orderItem = new OrderItem(orders);
            orderItem.setItemColor(cartItem.getItemColor());
            orderItem.setCount(cartItem.getCount());
            orderItem.setTotalPrice(cartItem.getTotalPrice());
            orderItemRepository.save(orderItem);

            if (cartItem.getItemColor().getStock() < cartItem.getCount()) {
                throw new APIError("OverStock", "재고가 부족합니다.");
            }

            cartItem.getItemColor().setStock(cartItem.getItemColor().getStock() - cartItem.getCount());

            cartItemRepository.deleteById(id);
        }
        ordersRepository.save(orders);

        Cart cart = cartRepository.findByUserId(user.getId());
        if (cart.getCount() <= 0) {
            cartRepository.delete(cart);
        }

    }
}
