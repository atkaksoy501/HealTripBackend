package codewizards.heal_trip.controllers;

import codewizards.heal_trip.business.IUsersService;
import codewizards.heal_trip.entities.Users;
import codewizards.heal_trip.entities.UsersRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

    private final IUsersService IUsersService;

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @GetMapping("")
    public List<Users> GetUsers() {
        return IUsersService.GetAllUsers();
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PostMapping("")
    public Users GetUsers(@RequestBody UsersRequest user) {
        return IUsersService.AddUser(user);
    }

}
