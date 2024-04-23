package codewizards.heal_trip.business.rules;

import codewizards.heal_trip.business.messages.UserMessages;
import codewizards.heal_trip.core.utilities.exceptions.types.BusinessException;
import codewizards.heal_trip.dataAccess.UserDao;
import codewizards.heal_trip.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserBusinessRules {
    UserDao userDao;

    public void checkIfUserExistsBefore(String email) {
        Optional<User> user = userDao.findByEmail(email);
        if (user.isPresent()) {
            throw new BusinessException(UserMessages.USER_ALREADY_EXISTS);
        }
    }

    public void checkIfUserExists(int id) {
        Optional<User> user = userDao.findById(id);
        if (user.isEmpty()) {
            throw new BusinessException(UserMessages.USER_NOT_FOUND);
        }
    }
}
