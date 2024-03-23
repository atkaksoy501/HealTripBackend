package codewizards.heal_trip.business.concretes;

import codewizards.heal_trip.DTO.UserDTO;
import codewizards.heal_trip.business.abstracts.IAuthService;
import codewizards.heal_trip.dataAccess.UserDao;
import codewizards.heal_trip.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService implements IAuthService {
    private final UserDao userRepository;

    public Optional<User> AddUser(UserDTO user) {
        User newUser = new User();
        newUser.setFirst_name(user.getFirst_name());
        newUser.setLast_name(user.getLast_name());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        newUser.setRoles(user.getRoles());
        newUser.setCreateDate(LocalDateTime.now());
        return Optional.of(userRepository.save(newUser));
    }
}
