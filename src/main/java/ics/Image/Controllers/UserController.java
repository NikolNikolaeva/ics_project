package ics.Image.Controllers;

import ics.Image.Classes.User;
import ics.Image.Services.UserService;
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

    @GetMapping(path = "/id")
    public User getUserById(@RequestParam long id) {
        return userService.getUserById(id);
    }

    @GetMapping(path = "username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping
    public User registerNewUser(@RequestBody User user) {
       if(userService.existUser(user.getUsername(), user.getEmail())) {
            return userService.getUserByUsername(user.getUsername());
        }

        return userService.addNewUser(user);
    }

}
