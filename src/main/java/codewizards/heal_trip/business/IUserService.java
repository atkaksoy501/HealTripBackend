package codewizards.heal_trip.business;


public interface IUserService {

    void registerUser(String firstName, String lastName, String email, String phone_number, String user_password, String user_role);

}
