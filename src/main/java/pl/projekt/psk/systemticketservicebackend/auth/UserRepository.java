package pl.projekt.psk.systemticketservicebackend.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.projekt.psk.systemticketservicebackend.auth.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
