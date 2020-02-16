package ch.sekthor.springbootjwt.repositories;

import ch.sekthor.springbootjwt.models.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(@Param("username") String username);

    boolean existsByUsername(@Param("username") String username);
}
