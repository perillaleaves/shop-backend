package perillaleaves.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import perillaleaves.community.domain.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
