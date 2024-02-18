package codewizards.heal_trip.business;

import codewizards.heal_trip.entities.User;
import org.springframework.stereotype.Repository;

public interface IUserService {

    void registerUser(String firstName, String lastName, String email, String phone_number, String user_password, String user_role);

}
