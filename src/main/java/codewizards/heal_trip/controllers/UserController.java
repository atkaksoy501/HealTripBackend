package codewizards.heal_trip.controllers;

import codewizards.heal_trip.business.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public void registerUser(String firstName, String lastName, String email, String phone_number, String user_password, String user_role) {
        userService.registerUser(firstName, lastName, email, phone_number, user_password, user_role);
    }
}
