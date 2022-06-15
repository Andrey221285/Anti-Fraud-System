package antifraud.persistence.dao;

import antifraud.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserNameIgnoreCase(String userName);
}
