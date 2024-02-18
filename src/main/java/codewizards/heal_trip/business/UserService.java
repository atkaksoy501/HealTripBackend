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
        this.userDTO = userDTO;
    }

    @Override
    public void registerUser(String firstName, String lastName, String email, String phone_number, String user_password, String user_role) {
        User newUser = new User();
        newUser.setFirst_name(firstName);
        newUser.setLast_name(lastName);
        newUser.setEmail(email);
        newUser.setPhone_number(phone_number);
        newUser.setUser_password(user_password);
        newUser.setUser_role(user_role);
        userDTO.save(newUser);
    }

}
