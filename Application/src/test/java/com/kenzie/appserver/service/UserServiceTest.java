package com.kenzie.appserver.service;

<<<<<<< HEAD
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

=======
import com.kenzie.appserver.controller.model.LoginRequest;
import com.kenzie.appserver.controller.model.UserCreateRequest;
import com.kenzie.appserver.repositories.UserRepository;
import com.kenzie.appserver.repositories.model.UserRecord;
import com.kenzie.appserver.service.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.naming.AuthenticationException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class UserServiceTest {

    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    void testCreateUser() {
        UserCreateRequest request = new UserCreateRequest("username", "password");
        UserRecord record = new UserRecord(request.getEmail(), request.getPassword());
        when(userRepository.findByEmail(request.getEmail())).thenReturn(null);
        doNothing().when(userRepository).save(record);

        User createdUser = userService.createUser(request);

        assertNotNull(createdUser);
        assertEquals(request.getPassword(), createdUser.getPassword());
        assertEquals(request.getEmail(), createdUser.getEmail());
        verify(userRepository).findByEmail(request.getEmail());
        verify(userRepository).save(record);
    }

    @Test
    void testCreateExistingUser() {
        UserCreateRequest request = new UserCreateRequest("username", "password");
        UserRecord existingRecord = new UserRecord(request.getEmail(), request.getPassword());
        when(userRepository.findByEmail(request.getEmail())).thenReturn(existingRecord);

        assertThrows(RuntimeException.class, () -> userService.createUser(request));
        verify(userRepository).findByEmail(request.getEmail());
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void testGetAllUsers() {
        List<UserRecord> userRecords = Arrays.asList(
                new UserRecord("user1@example.com", "password1"),
                new UserRecord("user2@example.com", "password2"),
                new UserRecord("user3@example.com", "password3")
        );
        when(userRepository.findAll()).thenReturn(userRecords);

        List<User> actualUsers = userService.getAllUsers();

        assertEquals(userRecords.size(), actualUsers.size(), "Number of users should match");
        for (int i = 0; i < actualUsers.size(); i++) {
            User expectedUser = new User(userRecords.get(i));
            User actualUser = actualUsers.get(i);
            assertEquals(expectedUser.getEmail(), actualUser.getEmail(), "Emails should match");
            assertEquals(expectedUser.getPassword(), actualUser.getPassword(), "Passwords should match");
        }
        verify(userRepository).findAll();
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void testLoginUser() throws Exception {
        String email = "test@test.com";
        String password = "testpassword";
        UserRecord userRecord = new UserRecord(email, password);
        when(userRepository.findByEmail(email)).thenReturn(userRecord);

        User loggedInUser = userService.loginUser(new LoginRequest(email, password));

        assertNotNull(loggedInUser, "Logged in user is not null");
        assertEquals(email, loggedInUser.getEmail(), "Email matches");
        assertEquals(password, loggedInUser.getPassword(), "Password matches");

        assertAuthenticationException("invalid@test.com", "password");
        assertAuthenticationException(email, "wrong-password");
        assertAuthenticationException(email, "");
        verify(userRepository, times(4)).findByEmail(anyString());
        verifyNoMoreInteractions(userRepository);
    }

    private void assertAuthenticationException(String email, String password) {
        AuthenticationException exception = assertThrows(AuthenticationException.class,
                () -> userService.loginUser(new LoginRequest(email, password)));
        assertEquals("Invalid email or password", exception.getMessage());
>>>>>>> 78e3b20 (login and create user all test passing for service and controller)
    }

}
