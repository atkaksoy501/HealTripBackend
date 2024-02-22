package codewizards.heal_trip.dataAccess;

import codewizards.heal_trip.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<User, Integer>{
}
