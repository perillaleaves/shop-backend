package perillaleaves.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import perillaleaves.shop.domain.item.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
