package perillaleaves.shop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perillaleaves.shop.domain.item.Cart;
import perillaleaves.shop.domain.item.CartItem;
import perillaleaves.shop.domain.item.Item;
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
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;

    public CartService(CartRepository cartRepository, TokenRepository tokenRepository, ItemRepository itemRepository, UserRepository userRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.tokenRepository = tokenRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public void addCart(String accessToken, Item itemRequest, int count) {
        if (accessToken.isBlank()) {
            throw new APIError("NotLogin", "로그인 유저가 아닙니다.");
        }
        Optional<Token> token = Optional.ofNullable(tokenRepository.findByToken(accessToken));
        if (token.isEmpty()) {
            throw new APIError("NotLogin", "로그인 유저가 아닙니다.");
        }

        Cart cart = cartRepository.findByUserId(token.get().getUser_id());
        User user = userRepository.findById(token.get().getUser_id()).orElse(null);
        if (cart == null) {
            Cart addCart = new Cart(0, user);
            cartRepository.save(addCart);
        }

        Item item = itemRepository.findById(itemRequest.getId()).orElse(null);
        CartItem cartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId());

        if (cartItem == null) {
            CartItem addCartItem = new CartItem(cart, item, count);
            cartItemRepository.save(addCartItem);
        }

        CartItem updatedCartItem = cartItem;
        updatedCartItem.setCart(cartItem.getCart());
        updatedCartItem.setItem(cartItem.getItem());
        updatedCartItem.setCount(++count);
        cartItemRepository.save(updatedCartItem);

        cart.setCount(cart.getCount() + (++count));
    }

}
