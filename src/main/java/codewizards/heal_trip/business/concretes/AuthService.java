package codewizards.heal_trip.business.concretes;

import codewizards.heal_trip.DTO.UserDTO;
import codewizards.heal_trip.business.DTOs.requests.authentication.AuthenticationRequest;
import codewizards.heal_trip.business.abstracts.IAuthService;
import codewizards.heal_trip.business.abstracts.IPatientService;
import codewizards.heal_trip.business.messages.AuthMessages;
import codewizards.heal_trip.business.rules.UserBusinessRules;
import codewizards.heal_trip.core.security.CustomUserDetails;
import codewizards.heal_trip.core.security.JpaUserDetailsService;
import codewizards.heal_trip.core.security.config.JwtUtils;
import codewizards.heal_trip.core.utilities.exceptions.types.BusinessException;
import codewizards.heal_trip.dataAccess.UserDao;
import codewizards.heal_trip.entities.Patient;
import codewizards.heal_trip.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService implements IAuthService {
    private final UserDao userRepository;
    private final AuthenticationManager authenticationManager;
    private final JpaUserDetailsService jpaUserDetailsService;
    private final JwtUtils jwtUtils;
    private final IPatientService patientService;
    private final UserBusinessRules userBusinessRules;

    public String login (AuthenticationRequest request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        if (!authentication.isAuthenticated()) {
            throw new BusinessException(AuthMessages.LOGIN_FAILED);
        }
        final CustomUserDetails user = jpaUserDetailsService.loadUserByUsername(request.getEmail());
        // todo: user == null icin business rule yazilacak
        return jwtUtils.generateToken(user);
    }

    public Optional<User> AddUser(UserDTO user) {
        userBusinessRules.checkIfUserExistsBefore(user.getEmail());
        User newUser = new User();
        newUser.setFirst_name(user.getFirst_name());
        newUser.setLast_name(user.getLast_name());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        newUser.setRoles(user.getRoles());
        newUser.setCreateDate(LocalDateTime.now());
        newUser.setActive(true);
        newUser.setPhone_number(user.getPhone_number());
        return Optional.of(userRepository.save(newUser));
    }

    public Optional<Patient> addPatient(UserDTO patient) {
        return Optional.of(patientService.registerPatient(patient));
    }
}
