package codewizards.heal_trip.business;


import codewizards.heal_trip.entities.User;

public interface IUserService {

    Integer registerUser(User user);

    User getUserById(int user_id);

    User updateUser(int user_id, User user);

    boolean deleteUser(int user_id);

    boolean reActivateUser(int user_id);

}
