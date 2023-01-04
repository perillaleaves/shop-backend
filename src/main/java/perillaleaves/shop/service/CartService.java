package perillaleaves.shop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perillaleaves.shop.domain.item.Cart;
import perillaleaves.shop.domain.item.CartItem;
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

    public CartItem addCart(String accessToken, Long color_id, int count) {
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

        User user = userRepository.findById(token.get().getUser_id()).orElse(null);
        Cart cart = cartRepository.findByUser(user);
        ItemColor itemColor = itemColorRepository.findById(color_id).orElse(null);

        if (itemColor.getStock() == 0) {
            throw new APIError("OutOfStock", "재고 없음");
        }

        if (cart == null) {
            Cart addCart = new Cart(0, user);
            addCart.setCount(addCart.getCount() + count);
            cartRepository.save(addCart);

            CartItem addCartItem = new CartItem(addCart, itemColor, count);
            addCartItem.setTotalPrice(count * itemColor.getItem().getPrice());
            return cartItemRepository.save(addCartItem);
        }

        CartItem cartItem = cartItemRepository.findByCartAndItemColorId(cart, color_id);
        if (cartItem == null) {
            CartItem addCartItem = new CartItem(cart, itemColor, count);
            cart.setCount(cart.getCount() + count);
            addCartItem.setTotalPrice(count * itemColor.getItem().getPrice());
            return cartItemRepository.save(addCartItem);
        }
        CartItem update = cartItem;
        update.setCart(cartItem.getCart());
        update.setItemColor(cartItem.getItemColor());
        update.setCount(cartItem.getCount() + count);
        cart.setCount(cart.getCount() + count);
        update.setTotalPrice(update.getTotalPrice() + (count * update.getItemColor().getItem().getPrice()));
        return cartItemRepository.save(update);
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

        return cartRepository.findByUser(user);
    }

    public void deleteCart(String accessToken, Long cart_item_id) {
        if (accessToken.isBlank()) {
            throw new APIError("NotLogin", "로그인 유저가 아닙니다.");
        }
        Optional<Token> token = Optional.ofNullable(tokenRepository.findByToken(accessToken));
        if (token.isEmpty()) {
            throw new APIError("NotLogin", "로그인 유저가 아닙니다.");
        }

        Optional<CartItem> cartItem = cartItemRepository.findById(cart_item_id);
        if (cartItem.get().getCart() == null) {
            throw new APIError("EmptyCart", "장바구니가 비어있습니다.");
        }
        if (!cartItem.get().getCart().getUser().getId().equals(token.get().getUser_id())) {
            throw new APIError("NotLogin", "로그인 유저가 아닙니다.");
        }

        if (cartItem.isEmpty()) {
            cartRepository.delete(cartItem.get().getCart());
        }
        if (cartItem.isPresent()) {
            cartItemRepository.deleteById(cart_item_id);
            cartItem.get().getCart().setCount(cartItem.get().getCart().getCount() - cartItem.get().getCount());
        }
    }

    public Cart countByCart(String accessToken) {
        if (accessToken.isBlank()) {
            throw new APIError("NotLogin", "로그인 유저가 아닙니다.");
        }
        Optional<Token> token = Optional.ofNullable(tokenRepository.findByToken(accessToken));
        if (token.isEmpty()) {
            throw new APIError("NotLogin", "로그인 유저가 아닙니다.");
        }

        return cartRepository.findByUserId(token.get().getUser_id());
    }

    public void updateByCartItemCount(Long cart_item_id, int count) {

        CartItem findCartItem = cartItemRepository.findById(cart_item_id).orElse(null);

        if (count < 0) {
            throw new APIError("CheckAgainCount", "수량을 다시 확인해주세요.");
        }

        if (count < findCartItem.getCount()) {
            findCartItem.getCart().setCount(findCartItem.getCart().getCount() - (findCartItem.getCount() - count));
            findCartItem.setCount(count);
        }
        findCartItem.getCart().setCount(findCartItem.getCart().getCount() + (count - findCartItem.getCount()));
        findCartItem.setCount(count);

        if (findCartItem.getCount() <= 0) {
            cartItemRepository.deleteById(cart_item_id);
        }
        if (findCartItem.getCart().getCount() <= 0) {
            cartRepository.deleteById(findCartItem.getCart().getId());
        }

    }


}