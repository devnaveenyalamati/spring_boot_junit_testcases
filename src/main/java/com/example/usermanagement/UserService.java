package com.example.usermanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(String name, String email) {
        if (name == null || email == null || name.isBlank() || email.isBlank()) {
            throw new IllegalArgumentException("Name and email cannot be null or empty");
        }
        User user = new User(name, email);
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public User updateUser(Long id, String newName, String newEmail) {
        User user = getUserById(id);
        user.setName(newName);
        user.setEmail(newEmail);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
