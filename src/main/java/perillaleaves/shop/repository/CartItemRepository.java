package perillaleaves.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import perillaleaves.shop.domain.item.Cart;
import perillaleaves.shop.domain.item.CartItem;
import perillaleaves.shop.domain.item.ItemColor;

import java.util.List;


public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCart(Cart cart);

}