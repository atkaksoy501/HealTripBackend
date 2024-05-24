package codewizards.heal_trip.business.concretes;

import codewizards.heal_trip.business.abstracts.IUserService;
import codewizards.heal_trip.dataAccess.PatientDao;
import codewizards.heal_trip.dataAccess.UserDao;
import codewizards.heal_trip.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService implements IUserService {

    private UserDao userDao;
    private PatientDao patientDao;

    @Autowired
    public UserService(UserDao userDao, PatientDao patientDao) {
        this.userDao = userDao;
        this.patientDao = patientDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Integer registerUser(User user) {
        User newUser = new User();
        newUser.setFirst_name(user.getFirst_name());
        newUser.setLast_name(user.getLast_name());
        newUser.setEmail(user.getEmail());
        newUser.setPhone_number(user.getPhone_number());
        newUser.setPassword(user.getPassword());
//        newUser.setUser_role(user.getUser_role());
        newUser.setActive(true);
        newUser = userDao.save(newUser);
        return newUser.getId();
    }

    @Override
    public User getUserById(int user_id) {
        return userDao.findById(user_id).orElse(null);
    }

    @Override
    public User updateUser(int user_id, User user) {
        User dbUser = userDao.findById(user_id).orElse(null);
        if (dbUser != null) {
            if (user.getFirst_name() != null)
                dbUser.setFirst_name(user.getFirst_name());
            if (user.getLast_name() != null)
                dbUser.setLast_name(user.getLast_name());
            if (user.getEmail() != null)
                dbUser.setEmail(user.getEmail());
            if (user.getPhone_number() != null)
                dbUser.setPhone_number(user.getPhone_number());
            if (user.getPassword() != null)
                dbUser.setPassword(user.getPassword());
//            if (user.getUser_role() != null)
//                dbUser.setUser_role(user.getUser_role());
            dbUser = userDao.save(dbUser);
        }
        return dbUser;
    }

    // This method returns true if the user does not exist, and false if the user has been deleted
    @Override
    public boolean deleteUser(int user_id) {
        User user = userDao.findById(user_id).orElse(null);
        if(user != null) {
            if (!user.isActive())
                return true;
            user.setActive(false);
            userDao.save(user);
            return false;
        }
        return true;
    }

    // This method returns true if the user is already active, and false if the user has been reactivated
    @Override
    public boolean reActivateUser(int user_id) {
        User user = userDao.findById(user_id).orElse(null);
        if(user != null) {
            if (user.isActive())
                return true;
            user.setActive(true);
            userDao.save(user);
            return false;
        }
        return true;
    }

}
