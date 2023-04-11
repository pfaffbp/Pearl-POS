package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.UserRepository;
import com.kenzie.appserver.repositories.model.UserRecord;
import com.kenzie.appserver.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public boolean createUser(String email, String password) {
        Optional <UserRecord> record = userRepository.findById(email);
        if (record.isPresent()) {
            return false;
        } else {
            UserRecord userRecord = new UserRecord(email, password);
            userRepository.save(userRecord);
            return true;
        }
    }

    public User login(String email, String password) {
        Optional<UserRecord> record = userRepository.findById(email);
        if (record.isPresent()) {
            UserRecord userRecord = record.get();
            if (userRecord.getPassword().equals(password)) {
                return new User(email, password);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }


    public Optional<UserRecord> getUser(String email) {
        return userRepository.findById(email);
    }

    public boolean checkEmailUniqueness(String email) {
        Optional<UserRecord> userRecord = userRepository.findById(email);
        return !userRecord.isPresent();
    }


    public Iterable<UserRecord> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean deleteUserByEmail(String email) {
        Optional<UserRecord> record = userRepository.findById(email);
        if (record.isPresent()) {
            userRepository.delete(record.get());
            return true;
        } else {
            return false;
        }
    }

    public boolean updateUserByEmail(String email, String password) {
        Optional<UserRecord> record = userRepository.findById(email);
        if (record.isPresent()) {
            UserRecord userRecord = record.get();
            userRecord.setPassword(password);
            userRepository.save(userRecord);
            return true;
        } else {
            return false;
        }
    }
}
