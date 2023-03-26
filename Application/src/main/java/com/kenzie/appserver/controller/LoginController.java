package com.kenzie.appserver.controller;

import com.amazonaws.AmazonClientException;
import com.kenzie.appserver.controller.model.LoginCreateRequest;
import com.kenzie.appserver.controller.model.LoginResponse;
import com.kenzie.appserver.service.UserService;
import com.kenzie.appserver.service.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping // POST /login - login
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginCreateRequest loginCreateRequest) {
        try {
            if (!userService.authenticateUser(loginCreateRequest)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            // Find user in the database by username
            User user = userService.findUserByUsername(loginCreateRequest.getUsername());

            // Check if user exists and password matches
            if (user != null && user.getPassword().equals(loginCreateRequest.getPassword())) {
                // Create a new login token for the user
                SecretKey key   = userService.generateKey() ;
                String token = userService.createLoginToken(user, key);

                // Return login token in the response
                LoginResponse loginResponse = new LoginResponse(token);
                return ResponseEntity.ok(loginResponse);
            } else {
                // Return 401 Unauthorized status code if login failed
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (AmazonClientException e) {
            // Log the error and return a generic error response
            log.error("An error occurred while processing login request", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/logout") // POST /login/logout - logout
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") @NotNull String token) {
        try {
            // Remove the token from the server-side storage or database
            boolean success = userService.invalidateToken(token);

            // Return success or failure response
            if (success) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (AmazonClientException e) {
            // Log the error and return a generic error response
            log.error("An error occurred while processing logout request", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
