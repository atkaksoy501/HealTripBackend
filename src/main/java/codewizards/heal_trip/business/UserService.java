package codewizards.heal_trip.business;

import codewizards.heal_trip.dataAccess.UserDTO;
import codewizards.heal_trip.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService implements IUserService{

    private UserDTO userDTO;

    @Autowired
    public UserService(UserDTO userDTO) {
        super();
        this.userDTO = userDTO;
    }

    @Override
    public void registerUser(String firstName, String lastName, String email, String phone_number, String user_password, String user_role) {
        this.userDTO.save(new User(firstName, lastName, email, phone_number, user_password, user_role));
    }

}
