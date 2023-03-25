package com.kenzie.appserver.service;

import com.kenzie.appserver.controller.model.LoginCreateRequest;

import com.kenzie.appserver.repositories.UserRepository;
import com.kenzie.appserver.repositories.model.UserRecord;
import com.kenzie.appserver.service.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final List<String> revokedTokens = new ArrayList<>();


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authenticateUser(LoginCreateRequest request) {
        UserRecord user = userRepository.findByUsername(request.getUsername());
        return user != null && user.getPassword().equals(request.getPassword());
    }

    public User findUserByUsername(String username) {
        UserRecord user = userRepository.findByUsername(username);
        return user != null ? new User(user.getId(), user.getUsername(), user.getPassword(), user.getEmail()) : null;
    }


    public User findUserByEmail(String email) {
        UserRecord user = userRepository.findUserByEmail(email);
        return user != null ? new User(user.getId(), user.getUsername(), user.getPassword(), user.getEmail()) : null;
    }


    public String createLoginToken(User user, SecretKey key) {
        // Set the expiration time of the token to 1 hour from now
        Date expirationTime = new Date(System.currentTimeMillis() + 3600000);

        // Generate a random secret key to sign the token
        Key secretKey = generateKey();

        // Build the token with the user ID and expiration time
        return Jwts.builder()
                .setSubject(user.getId())
                .setExpiration(expirationTime)
                .signWith(secretKey)
                .compact();
    }

    public SecretKey generateKey() {
        return Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public boolean invalidateToken(String token) {
        // Check if the token is in the list of revoked tokens
        if (revokedTokens.contains(token)) {
            return false;
        } else {
            // Add token to list of revoked tokens
            revokedTokens.add(token);
            return true;
        }
    }


    public boolean isTokenRevoked(String token) {
        // Check if token is in list of revoked tokens
        return revokedTokens.contains(token);
    }

    public User createUser(User user) {
        UserRecord userRecord = new UserRecord(user.getId(), user.getUsername(), user.getPassword(), user.getEmail());
        userRepository.save(userRecord);
        return new User(userRecord.getId(), userRecord.getUsername(), userRecord.getPassword(), userRecord.getEmail());
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(userRecord -> users.add(new User(userRecord.getId(), userRecord.getUsername(), userRecord.getPassword(), userRecord.getEmail())));
        return users;
    }

    public User getUserById(String id) {
        UserRecord userRecord = userRepository.findById(id).orElse(null);
        return userRecord != null ? new User(userRecord.getId(), userRecord.getUsername(), userRecord.getPassword(), userRecord.getEmail()) : null;
    }

    public User updateUser(String id, User user) {
        UserRecord userRecord = userRepository.findById(id).orElse(null);
        if (userRecord == null) {
            return null;
        } else {
            userRecord.setUsername(user.getUsername());
            userRecord.setPassword(user.getPassword());
            userRecord.setEmail(user.getEmail());
            userRepository.save(userRecord);
            return new User(userRecord.getId(), userRecord.getUsername(), userRecord.getPassword(), userRecord.getEmail());
        }
    }
}
