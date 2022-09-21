package og.bumawiki.bumawiki.domain.user.repository;

import og.bumawiki.bumawiki.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
