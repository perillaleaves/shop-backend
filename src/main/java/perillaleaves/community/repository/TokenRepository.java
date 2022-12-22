package perillaleaves.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import perillaleaves.community.domain.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

}
