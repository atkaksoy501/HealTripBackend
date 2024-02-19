package codewizards.heal_trip.business;

import codewizards.heal_trip.dataAccess.PatientDTO;
import codewizards.heal_trip.dataAccess.UserDTO;
import codewizards.heal_trip.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService implements IUserService{

    private UserDTO userDTO;
    private PatientDTO patientDTO;

    @Autowired
    public UserService(UserDTO userDTO, PatientDTO patientDTO) {
        this.userDTO = userDTO;
        this.patientDTO = patientDTO;
    }

    @Override
    public Integer registerUser(User user) {
        User newUser = new User();
        newUser.setFirst_name(user.getFirst_name());
        newUser.setLast_name(user.getLast_name());
        newUser.setEmail(user.getEmail());
        newUser.setPhone_number(user.getPhone_number());
        newUser.setUser_password(user.getUser_password());
        newUser.setUser_role(user.getUser_role());
        newUser.setActive(true);
        newUser = userDTO.save(newUser);
        return newUser.getUser_id();
    }

    @Override
    public User getUserById(int user_id) {
        return userDTO.findById(user_id).orElse(null);
    }

    @Override
    public User updateUser(int user_id, User user) {
        User dbUser = userDTO.findById(user_id).orElse(null);
        if (dbUser != null) {
            if (user.getFirst_name() != null)
                dbUser.setFirst_name(user.getFirst_name());
            if (user.getLast_name() != null)
                dbUser.setLast_name(user.getLast_name());
            if (user.getEmail() != null)
                dbUser.setEmail(user.getEmail());
            if (user.getPhone_number() != null)
                dbUser.setPhone_number(user.getPhone_number());
            if (user.getUser_password() != null)
                dbUser.setUser_password(user.getUser_password());
            if (user.getUser_role() != null)
                dbUser.setUser_role(user.getUser_role());
            dbUser = userDTO.save(dbUser);
        }
        return dbUser;
    }

    // This method returns true if the user does not exist, and false if the user has been deleted
    @Override
    public boolean deleteUser(int user_id) {
        User user = userDTO.findById(user_id).orElse(null);
        if(user != null) {
            if (!user.isActive())
                return true;
            user.setActive(false);
            userDTO.save(user);
            return false;
        }
        return true;
    }

    // This method returns true if the user is already active, and false if the user has been reactivated
    @Override
    public boolean reActivateUser(int user_id) {
        User user = userDTO.findById(user_id).orElse(null);
        if(user != null) {
            if (user.isActive())
                return true;
            user.setActive(true);
            userDTO.save(user);
            return false;
        }
        return true;
    }

}
