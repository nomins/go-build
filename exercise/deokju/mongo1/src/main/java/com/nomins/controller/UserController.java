package com.nomins.controller;

import com.nomins.domain.User;
import com.nomins.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/")
public class UserController {
    private final Logger logger  = LoggerFactory.getLogger(getClass());
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("user")
    public List<User> getAllUsers() {
        logger.info("Getting all users.");
        return userRepository.findAll();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public User addNewUsers(User user) {
        logger.info("Saving user.");
        return userRepository.save(user);
    }
}
