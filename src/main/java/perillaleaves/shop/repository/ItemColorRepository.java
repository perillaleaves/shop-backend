package perillaleaves.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import perillaleaves.shop.domain.item.Item;
import perillaleaves.shop.domain.item.ItemColor;

import java.util.List;

@Repository
public interface ItemColorRepository extends JpaRepository<ItemColor, Long> {

    List<ItemColor> findByItem(Item item);

    ItemColor findByIdAndItem(Long id, Item item);
}
