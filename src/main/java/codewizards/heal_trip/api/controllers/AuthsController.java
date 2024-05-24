package codewizards.heal_trip.api.controllers;

import codewizards.heal_trip.DTO.UserDTO;
import codewizards.heal_trip.business.abstracts.IAuthService;
import codewizards.heal_trip.business.DTOs.requests.authentication.AuthenticationRequest;
import codewizards.heal_trip.core.security.UserSecurity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication Management", description = "Authentication Management APIs")
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@CrossOrigin
public class AuthsController {

    private final IAuthService authService;

    @Operation(summary = "Login User")
    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.OK)
    public String authenticate(@Valid @RequestBody AuthenticationRequest request) {
        return authService.login(request);
    }

    @Operation(summary = "Register User")
    @PostMapping("/register")
    public ResponseEntity<UserSecurity> register(@Valid @RequestBody UserDTO user) throws Exception {
        return ResponseEntity.ok(authService.AddUser(user).map(UserSecurity::new).orElseThrow(() -> new Exception("Unknown")));
    }

    @Operation(summary = "Register Patient")
    @PostMapping("/register/patient")
    public ResponseEntity<UserSecurity> registerPatient(@Valid @RequestBody UserDTO patient) throws Exception {
        return ResponseEntity.ok(authService.addPatient(patient).map(UserSecurity::new).orElseThrow(() -> new Exception("Unknown")));
    }

}
