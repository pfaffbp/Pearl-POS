package com.kenzie.appserver.service;

import com.kenzie.appserver.controller.model.LoginCreateRequest;
import com.kenzie.appserver.repositories.UserRepository;
import com.kenzie.appserver.repositories.model.UserRecord;
import com.kenzie.appserver.service.model.User;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.crypto.SecretKey;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository);
    }



    @Test
    void testAuthenticateUser() {
        // GIVEN
        String username = "user";
        String password = "pass";
        LoginCreateRequest loginRequest = new LoginCreateRequest(username, password);
        UserRecord userRecord = new UserRecord("1L", username, password, "testemail");

        // WHEN
        when(userRepository.findByUsername(username)).thenReturn(userRecord);
        boolean authenticated = userService.authenticateUser(loginRequest);

        // THEN
        verify(userRepository, times(1)).findByUsername(username);
        Assertions.assertTrue(authenticated, "The user is authenticated with correct credentials");
    }

    @Test
    void testFindUserByUsername() {
        // GIVEN
        String userId = "12345";
        String username = "testuser";
        UserRecord userRecord = new UserRecord(userId, username, "testpass", "testemail");


        // WHEN
        when(userRepository.findByUsername(username)).thenReturn(userRecord);
        User user = userService.findUserByUsername(username);

        // THEN
        verify(userRepository, times(1)).findByUsername(username);
        assertNotNull(user, "The user is returned");
        assertEquals(userRecord.getId(), user.getId(), "The id matches");
        assertEquals(userRecord.getUsername(), user.getUsername(), "The username matches");
        assertEquals(userRecord.getPassword(), user.getPassword(), "The password matches");
    }


    @Test
    void testFindUserByEmail() {
        // GIVEN
        String email = "testemail";
        UserRecord userRecord = new UserRecord("12345", "testuser", "testpass", email);

        // WHEN
        when(userRepository.findUserByEmail(email)).thenReturn(userRecord);
        User user = userService.findUserByEmail(email);

        // THEN
        verify(userRepository, times(1)).findUserByEmail(email);
        assertNotNull(user, "The user is returned");
        assertEquals(userRecord.getId(), user.getId(), "The id matches");
        assertEquals(userRecord.getUsername(), user.getUsername(), "The username matches");
        assertEquals(userRecord.getPassword(), user.getPassword(), "The password matches");
        assertEquals(userRecord.getEmail(), user.getEmail(), "The email matches");
    }






    @Test
    void testFindUserByUsernameNotFound() {
        // GIVEN
        String username = "nonexistent";

        // WHEN
        when(userRepository.findByUsername(username)).thenReturn(null);
        User user = userService.findUserByUsername(username);

        // THEN
        verify(userRepository, times(1)).findByUsername(username);
        Assertions.assertNull(user, "The user is null when not found");
    }

    @Test
    void testCreateLoginToken() {

        User user = new User("username", "password", "email", "id");
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);


        String token = userService.createLoginToken(user, key);

        assertNotNull(token, "The token is not null");
        Assertions.assertFalse(token.isEmpty(), "The token is not empty");

    }

}
