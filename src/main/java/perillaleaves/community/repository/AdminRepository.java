package perillaleaves.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import perillaleaves.community.domain.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}
