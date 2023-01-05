package perillaleaves.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import perillaleaves.shop.domain.item.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
