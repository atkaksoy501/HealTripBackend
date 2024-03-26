package codewizards.heal_trip.api.controllers;

import codewizards.heal_trip.DTO.UserDTO;
import codewizards.heal_trip.business.abstracts.IAuthService;
import codewizards.heal_trip.security.CustomUserDetails;
import codewizards.heal_trip.security.config.JwtUtils;
import codewizards.heal_trip.business.DTOs.requests.authentication.AuthenticationRequest;
import codewizards.heal_trip.security.JpaUserDetailsService;
import codewizards.heal_trip.security.UserSecurity;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthsController {

    private final AuthenticationManager authenticationManager;

    private final JpaUserDetailsService jpaUserDetailsService;

    private final IAuthService IAuthService;

    private final JwtUtils jwtUtils;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request, HttpServletResponse response) {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword(),
                            new ArrayList<>()));
            final CustomUserDetails user = jpaUserDetailsService.loadUserByUsername(request.getEmail());
            if (user != null) {
                String jwt = jwtUtils.generateToken(user);
                Cookie cookie = new Cookie("jwt", jwt);
                cookie.setMaxAge(24 * 60 * 60); // expires in 1 days
//                cookie.setSecure(true);
                cookie.setHttpOnly(true);
                cookie.setPath("/"); // Global
                response.addCookie(cookie);
                return ResponseEntity.ok(jwt);
            }
            return ResponseEntity.status(400).body("Error authenticating");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(400).body("" + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserSecurity> register(@RequestBody UserDTO user) throws Exception {
        return ResponseEntity.ok(IAuthService.AddUser(user).map(UserSecurity::new).orElseThrow(() -> new Exception("Unknown")));
    }

}
