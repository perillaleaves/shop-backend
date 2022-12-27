package perillaleaves.shop.repository.itemColor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import perillaleaves.shop.domain.itemColor.White;

@Repository
public interface WhiteRepository extends JpaRepository<White, Long> {
}
