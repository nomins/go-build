package nomins.finedust02.controller;

import nomins.finedust02.dal.UserRepository;
import nomins.finedust02.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/")
public class UserController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "")
    public List<User> getAllUsers() {
        LOG.info("Getting all users.");
        return userRepository.findAll();
    }

    @GetMapping(value = "/{userId}")
    public User getUser(@PathVariable String userId) {
        LOG.info("Getting user with ID: {}.", userId);
        Optional<User> user = userRepository.findById(userId);
        return user.get();
    }

    @PostMapping(value = "/create")
    public void addNewUsers(@RequestBody User user) {
        LOG.info("Saving user.");
        LOG.info(user.toString());
        userRepository.save(user);
    }

    @GetMapping(value = "/settings/{userId}/{key}")
    public String getUserSetting(@PathVariable String userId, @PathVariable String key) {
        Optional<User> user = userRepository.findById(userId);
        if (user != null) {
            return user.get().getUserSettings().get(key);
        } else {
            return "User not found.";
        }
    }

    @PostMapping(value = "/settings/{userId}/{key}/{value}")
    public String addUserSetting(@PathVariable String userId, @PathVariable String key, @PathVariable String value) {
        Optional<User> user = userRepository.findById(userId);
        if (user != null) {
            User target = user.get();
            target.getUserSettings().put(key, value);
            userRepository.save(target);
            return "Key added";
        } else {
            return "User not found.";
        }
    }

}
