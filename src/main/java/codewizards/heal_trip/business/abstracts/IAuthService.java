package codewizards.heal_trip.business.abstracts;

import codewizards.heal_trip.DTO.UserDTO;
import codewizards.heal_trip.entities.User;

import java.util.Optional;

public interface IAuthService {
    Optional<User> AddUser(UserDTO user);
}
