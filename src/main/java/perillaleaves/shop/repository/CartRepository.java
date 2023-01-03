package perillaleaves.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import perillaleaves.shop.domain.item.Cart;
import perillaleaves.shop.domain.user.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByUser(User user);

    Cart findByUserId(Long user_id);
}