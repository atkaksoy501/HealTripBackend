package codewizards.heal_trip.business;

import codewizards.heal_trip.entities.Users;
import codewizards.heal_trip.entities.UsersRequest;

import java.util.Optional;

public interface AuthService {
    public Optional<Users> AddUser(UsersRequest user);
}
