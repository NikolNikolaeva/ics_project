package ics.Image.Services;

import ics.Image.Classes.Image;
import ics.Image.Classes.User;
import ics.Image.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    final private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public boolean existUser(String username, String email)
    {
        //check if the user already exists in our database
        Optional<User> userExist = userRepository.findUserExistByUsernameAndEmail(username,email);
        if (userExist.isPresent()) {
            return true;
        }
        return false;
    }

    public User addNewUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAllUsers();
    }

    public User getUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public boolean userExistingEmail(String email){
        return userRepository.findUserByEmail(email).isPresent();
    }

    public boolean userExistingUsername(String username){
        return userRepository.findUserByUsernameExist(username).isPresent();
    }

    public User updateUser(User user) {
        userRepository.save(user);
        return user;
    }
}
