package perillaleaves.shop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perillaleaves.shop.domain.item.Cart;
import perillaleaves.shop.domain.item.CartItem;
import perillaleaves.shop.domain.item.Item;
import perillaleaves.shop.domain.item.ItemColor;
import perillaleaves.shop.domain.user.Token;
import perillaleaves.shop.domain.user.User;
import perillaleaves.shop.exception.APIError;
import perillaleaves.shop.repository.*;

import java.util.Optional;

@Service
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final ItemColorRepository itemColorRepository;
    private final ItemRepository itemRepository;

    public CartService(CartRepository cartRepository, TokenRepository tokenRepository, UserRepository userRepository, CartItemRepository cartItemRepository, ItemColorRepository itemColorRepository, ItemRepository itemRepository) {
        this.cartRepository = cartRepository;
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
        this.itemColorRepository = itemColorRepository;
        this.itemRepository = itemRepository;
    }

    public void addCart(String accessToken, Long color_id, int count) {
        if (accessToken.isBlank()) {
            throw new APIError("NotLogin", "로그인 유저가 아닙니다.");
        }
        Optional<Token> token = Optional.ofNullable(tokenRepository.findByToken(accessToken));
        if (token.isEmpty()) {
            throw new APIError("NotLogin", "로그인 유저가 아닙니다.");
        }

        if (count < 0) {
            throw new APIError("CheckAgainCount", "수량을 다시 확인해주세요.");
        }

        User user = userRepository.findById(token.get().getUser_id()).get();
        Optional<Cart> cart = cartRepository.findByUser(user);
        ItemColor itemColor = itemColorRepository.findById(color_id).orElse(null);
        Item item = itemRepository.findById(itemColor.getItem().getId()).orElse(null);

        if (cart.isEmpty()) {
            Cart addCart = new Cart(0, user);
            addCart.setCount(addCart.getCount() + count);
            cartRepository.save(addCart);

            CartItem addCartItem = new CartItem(addCart, itemColor, count);
            cartItemRepository.save(addCartItem);
            addCartItem.setTotalPrice(count * item.getPrice());
        }
        if (cart.isPresent()) {
            CartItem addCartItem = new CartItem(cart.get(), itemColor, count);
            cartItemRepository.save(addCartItem);
            cart.get().setCount(cart.get().getCount() + count);
            addCartItem.setTotalPrice(count * item.getPrice());
        }

    }

    public Cart findCartList(String accessToken) {
        if (accessToken.isBlank()) {
            throw new APIError("NotLogin", "로그인 유저가 아닙니다.");
        }
        Optional<Token> token = Optional.ofNullable(tokenRepository.findByToken(accessToken));
        if (token.isEmpty()) {
            throw new APIError("NotLogin", "로그인 유저가 아닙니다.");
        }
        User user = userRepository.findById(token.get().getUser_id()).orElse(null);

        return cartRepository.findByUser(user).orElse(null);
    }

    public void deleteCart(String accessToken, Long cart_id, Long cart_item_id) {
        if (accessToken.isBlank()) {
            throw new APIError("NotLogin", "로그인 유저가 아닙니다.");
        }

        Optional<Token> token = Optional.ofNullable(tokenRepository.findByToken(accessToken));
        if (token.isEmpty()) {
            throw new APIError("NotLogin", "로그인 유저가 아닙니다.");
        }

        Optional<Cart> cart = cartRepository.findById(cart_id);
        if (cart.isEmpty()) {
            throw new APIError("EmptyCart", "장바구니가 비어있습니다.");
        }
        if (!cart.get().getUser().getId().equals(token.get().getUser_id())) {
            throw new APIError("NotLogin", "로그인 유저가 아닙니다.");
        }
        Optional<CartItem> cartItem = cartItemRepository.findById(cart_item_id);
        if (cartItem.isEmpty()) {
            cartRepository.delete(cartItem.get().getCart());
        }
        if (cartItem.isPresent()) {
            cartItemRepository.deleteById(cart_item_id);
        }

    }


}