package perillaleaves.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import perillaleaves.shop.domain.item.Cart;
import perillaleaves.shop.domain.item.CartItem;

import java.util.List;


public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCart(Cart cart);

    CartItem findByCartAndItemColorId(Cart cart, Long item_color_id);

    List<CartItem> findByItemColorId(Long item_color_id);


}