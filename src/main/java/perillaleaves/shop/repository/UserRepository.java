package perillaleaves.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import perillaleaves.shop.domain.user.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLoginId(String login_id);

    Optional<User> findByPhoneNumber(String phone_number);

    Optional<User> findByEmail(String email);

    Optional<User> findByNameAndPhoneNumber(String name, String phone_number);

    Optional<User> findByLoginIdAndNameAndPhoneNumber(String login_id, String name, String phone_number);
}
