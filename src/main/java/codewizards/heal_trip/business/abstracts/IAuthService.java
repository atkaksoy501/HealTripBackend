package codewizards.heal_trip.business.abstracts;

import codewizards.heal_trip.DTO.UserDTO;
import codewizards.heal_trip.business.DTOs.requests.authentication.AuthenticationRequest;
import codewizards.heal_trip.entities.Patient;
import codewizards.heal_trip.entities.User;

import java.util.Optional;

public interface IAuthService {

    String login (AuthenticationRequest request);
    Optional<User> AddUser(UserDTO user);

    Optional<Patient> addPatient(UserDTO patient);
}
