package com.revature.service;


import com.revature.models.User;
import com.revature.models.enums.UserRoles;
import com.revature.repositories.UserRepository;
import com.revature.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;

public class UserServiceUnitTests {

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    public void setup() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    User user = new User(1, "test", "test", "test", "test", UserRoles.USER);
    User user2 = new User(2, "test2", "test2", "test2", "test2", UserRoles.USER);

    @Test
    public void createUser(){
        Mockito.when(userRepository.save(user)).thenReturn(user);
        User test = userService.createUser(user);
        Assertions.assertEquals(test,user);
    }

    @Test
    public void whenUpdateUserCalledDoesNotThrowAnException() {
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));
        Assertions.assertDoesNotThrow(() -> userService.updateUser(user,1));
    }

    @Test
    public void whenUpdateUserIsCalledReturnsUpdatedUserObject() {
        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.ofNullable(user));
        Mockito.when(userRepository.save(user)).thenReturn(user2);
        User userToUpdate = userService.updateUser(user,1);
        Assertions.assertEquals(userToUpdate, user2);
    }
}
