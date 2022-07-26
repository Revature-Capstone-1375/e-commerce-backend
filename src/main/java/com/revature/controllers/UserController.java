package com.revature.controllers;

import com.revature.models.User;
import com.revature.services.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void deleteUser(@RequestBody User user) {
        userService.deleteUser(user);
    }
}
