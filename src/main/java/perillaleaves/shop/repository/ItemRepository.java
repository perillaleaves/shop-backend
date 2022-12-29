package perillaleaves.shop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import perillaleaves.shop.domain.item.Item;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Page<Item> findAll(Pageable pageable);

    Optional<Item> findByName(String name);

}
