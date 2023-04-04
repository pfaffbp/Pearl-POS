package com.kenzie.appserver.service;

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
    }

}
