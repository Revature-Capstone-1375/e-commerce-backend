package com.revature;


import com.revature.models.User;
import com.revature.models.enums.UserRoles;
import com.revature.repositories.UserRepository;
import com.revature.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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

    @Test
    public void whenDeleteUserCalledDoesNotThrowAnException() {
        Assertions.assertDoesNotThrow(() -> userRepository.delete(user));
    }

    @Test
    public void whenDeleteUserIsCalledShouldCallEach() {
        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        User userToDelete = userRepository.findById(user.getId()).orElse(new User());
        userService.deleteUser(userToDelete);
        Mockito.verify(userRepository, Mockito.times(1)).delete(userToDelete);
    }
}
