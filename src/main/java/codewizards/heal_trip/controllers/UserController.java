package codewizards.heal_trip.controllers;

import codewizards.heal_trip.business.UserService;
import codewizards.heal_trip.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Integer> registerUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.OK);
    }

    @GetMapping(value = "/getById/{user_id}")
    public ResponseEntity<User> getUserById(@PathVariable int user_id) {
        User user = userService.getUserById(user_id);
        if (user == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping(value = "/update/{user_id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable int user_id) {
        return new ResponseEntity<>(userService.updateUser(user_id, user), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteById{user_id}")
    public ResponseEntity<String> deleteUser(@PathVariable int user_id) {
        boolean isUserActive = userService.deleteUser(user_id);
        if (!isUserActive)
            return new ResponseEntity<>("User with id " + user_id + " has been deleted", HttpStatus.OK);
        else
            return new ResponseEntity<>("User with id " + user_id + " does not exist", HttpStatus.NOT_FOUND);
    }

    @PatchMapping(value = "/reActivate{user_id}")
    public ResponseEntity<String> reActivateUser(@PathVariable int user_id) {
        boolean isUserActive = userService.reActivateUser(user_id);
        if (!isUserActive)
            return new ResponseEntity<>("User with id " + user_id + " has been re-activated", HttpStatus.OK);
        else
            return new ResponseEntity<>("User with id " + user_id + " does not exist or already active", HttpStatus.BAD_REQUEST);
    }
}
