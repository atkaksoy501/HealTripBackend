package codewizards.heal_trip.business.concretes;

import codewizards.heal_trip.DTO.UserDTO;
import codewizards.heal_trip.business.DTOs.requests.authentication.AuthenticationRequest;
import codewizards.heal_trip.business.abstracts.IAuthService;
import codewizards.heal_trip.business.messages.AuthMessages;
import codewizards.heal_trip.core.security.CustomUserDetails;
import codewizards.heal_trip.core.security.JpaUserDetailsService;
import codewizards.heal_trip.core.security.config.JwtUtils;
import codewizards.heal_trip.core.utilities.exceptions.types.BusinessException;
import codewizards.heal_trip.dataAccess.UserDao;
import codewizards.heal_trip.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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

    private static final String[] WHITE_LIST_URLS = {
            "/swagger-ui/**",
            "/v2/api-docs/**",
            "/v3/api-docs/**",
            "/v3/api-docs/**",
            "/auth/register",
            "/auth/authenticate",
            "/department/getAll",
            "/department/getAllSorted",
            "/department/getAllByPage",
            "/retreat/getAll",
            "/retreat/getByDepartmentId/**"
    };

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

    @Override
    public HttpSecurity configureSecurity(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers(WHITE_LIST_URLS).permitAll()
//                        .requestMatchers("/swagger-ui/index.html").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET).permitAll()
                        .anyRequest().authenticated()
        );
        return http;
    }
}
