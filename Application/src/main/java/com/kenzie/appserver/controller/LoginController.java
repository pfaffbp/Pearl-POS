package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.LoginCreateRequest;
import com.kenzie.appserver.controller.model.LoginResponse;
import com.kenzie.appserver.service.UserService;
import com.kenzie.appserver.service.model.User;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
<<<<<<< HEAD
    public ResponseEntity<LoginResponse> login(@RequestBody LoginCreateRequest loginCreateRequest) {
        if (!userService.authenticateUser(loginCreateRequest)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Find user in the database by username
        User user = userService.findUserByUsername(loginCreateRequest.getUsername());

        // Check if user exists and password matches
        if (user != null && user.getPassword().equals(loginCreateRequest.getPassword())) {
            // Create a new login token for the user
            String token = userService.createLoginToken(user, Keys.secretKeyFor(SignatureAlgorithm.HS256));

            // Return login token in the response
            return ResponseEntity.ok(new LoginResponse(token));
        } else {
            // Return 401 Unauthorized status code if login failed
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String token) {
        // Remove the token from the server-side storage or database
        boolean success = userService.invalidateToken(token);

        // Return success or failure response
        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
=======
    public ResponseEntity<User> login(@RequestBody LoginRequest request) throws AuthenticationException {
        User user = userService.loginUser(request);
        return ResponseEntity.ok(user);
    }
}

<<<<<<< HEAD

>>>>>>> 78e3b20 (login and create user all test passing for service and controller)
=======
>>>>>>> eb90ed7 (Revert "login and create user all test passing for service and controller")
