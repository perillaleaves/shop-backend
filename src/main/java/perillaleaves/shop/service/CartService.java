package perillaleaves.shop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perillaleaves.shop.domain.item.Cart;
import perillaleaves.shop.domain.user.Token;
import perillaleaves.shop.domain.user.User;
import perillaleaves.shop.exception.APIError;
import perillaleaves.shop.repository.CartRepository;
import perillaleaves.shop.repository.ItemRepository;
import perillaleaves.shop.repository.TokenRepository;
import perillaleaves.shop.repository.UserRepository;
import perillaleaves.shop.request.item.ItemDTO;

import java.util.Optional;

@Service
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final TokenRepository tokenRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository, TokenRepository tokenRepository, ItemRepository itemRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.tokenRepository = tokenRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    public void addCart(String accessToken, ItemDTO itemDTO) {
        if (accessToken.isBlank()) {
            throw new APIError("NotLogin", "로그인 유저가 아닙니다.");
        }
        Optional<Token> token = Optional.ofNullable(tokenRepository.findByToken(accessToken));
        if (token.isEmpty()) {
            throw new APIError("NotLogin", "로그인 유저가 아닙니다.");
        }

        Cart findCart = cartRepository.findByUserId(token.get().getUser_id());
        User user = userRepository.findById(token.get().getUser_id()).orElse(null);
        if (findCart == null) {
            Cart cart = new Cart(0, user);
            cartRepository.save(cart);
        }

//        itemRepository

    }

}
