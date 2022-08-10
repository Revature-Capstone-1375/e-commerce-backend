package com.revature.services;

import com.revature.models.User;
import com.revature.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByCredentials(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }


    public User save(User user) {
        return userRepository.save(user);
    }


    public User createUser(User user) {
        return userRepository.save(user);
    }
    
    public void deleteUserById(Integer userId) {
        User userToDelete = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User could not be found"));
        userRepository.delete(userToDelete);
    }

    public User updateUser(User user, Integer userId) {
        User userToUpdate = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User could not be found"));
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setPassword(user.getPassword());
        return userRepository.save(userToUpdate);
    }
}



