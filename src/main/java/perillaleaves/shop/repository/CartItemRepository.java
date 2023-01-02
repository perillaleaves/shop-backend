package perillaleaves.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import perillaleaves.shop.domain.item.Cart;
import perillaleaves.shop.domain.item.CartItem;

import java.util.List;
import java.util.Optional;


public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCart(Cart cart);

    Optional<CartItem> findByCartIdAndItemColorId(Long cart_id, Long itemColor_id);
}