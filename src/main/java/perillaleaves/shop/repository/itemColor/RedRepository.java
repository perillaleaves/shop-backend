package perillaleaves.shop.repository.itemColor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import perillaleaves.shop.domain.itemColor.Red;

@Repository
public interface RedRepository extends JpaRepository<Red, Long> {

}
