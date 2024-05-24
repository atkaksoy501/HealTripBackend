package codewizards.heal_trip.dataAccess;

import codewizards.heal_trip.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserDao extends JpaRepository<User, Integer>{
    Optional<User> findByEmail(String email);
}
