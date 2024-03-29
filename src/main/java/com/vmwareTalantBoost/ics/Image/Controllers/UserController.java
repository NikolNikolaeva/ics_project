package com.vmwareTalantBoost.ics.Image.Controllers;

import com.vmwareTalantBoost.ics.Image.Classes.User;
import com.vmwareTalantBoost.ics.Image.Classes.UserRegistrationDto;
import com.vmwareTalantBoost.ics.Image.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path="users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    //@GetMapping(path = "/id")
    //public User getUserById(@RequestParam long id) {
    //    return userService.getUserById(id);
    //}

    @GetMapping(path = "username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping
    public User registerNewUser(@RequestBody UserRegistrationDto registrationDto) {
       if(userService.existUser(registrationDto.getUsername(), registrationDto.getEmail())) {
            return userService.getUserByUsername(registrationDto.getUsername());
        }

        User user = new User(registrationDto.getUsername(), registrationDto.getEmail(), registrationDto.getPassword());
        return userService.addNewUser(user);
    }

}
