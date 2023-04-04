package com.kenzie.appserver.service;

import com.kenzie.appserver.controller.model.LoginRequest;
import com.kenzie.appserver.controller.model.UserCreateRequest;
import com.kenzie.appserver.repositories.UserRepository;
import com.kenzie.appserver.repositories.model.UserRecord;
import com.kenzie.appserver.service.model.User;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private User currentUser;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserCreateRequest request) {
        String email = request.getEmail();
        Optional<UserRecord> existingUser = Optional.ofNullable(userRepository.findByEmail(email));
        if (existingUser.isPresent()) {
            // User already exists, throw an exception or return null/empty response
            // Here we are throwing an exception with appropriate message
            throw new RuntimeException("User with email " + email + " already exists!");
        } else {
            // Create new user
            UserRecord record = new UserRecord(email, request.getPassword());
            userRepository.save(record);
            return new User(record);
        }
    }


    public User loginUser(LoginRequest request) throws AuthenticationException {
        UserRecord record = userRepository.findByEmail(request.getEmail());
        if (record != null && record.getPassword().equals(request.getPassword())) {
            currentUser = new User(record);
            return currentUser;
        } else {
            throw new AuthenticationException("Invalid email or password");
        }
    }


    public boolean logout() {
        if (currentUser != null) {
            currentUser = null;
            return true;
        } else {
            return false;
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean login(UserCreateRequest request) {
        UserRecord record = userRepository.findByEmail(request.getEmail());
        if (record != null && record.getPassword().equals(request.getPassword())) {
            currentUser = new User(record);
            return true;
        } else {
            return false;
        }
    }

    public List<User> getAllUsers() {
        List<UserRecord> records = userRepository.findAll();
        List<User> users = new ArrayList<>();
        for (UserRecord record : records) {
            users.add(new User(record));
        }
        return users;
    }

}
