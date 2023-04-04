package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.LoginRequest;
import com.kenzie.appserver.service.UserService;
import com.kenzie.appserver.service.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.naming.AuthenticationException;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LoginControllerTest {

    private LoginController loginController;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        loginController = new LoginController(userService);
    }

    private static final String EMAIL = "alice@example.com";
    private static final String PASSWORD = "password";

    @Test
    public void testLoginWithValidCredentialsReturnsUser() throws AuthenticationException {
        // Create a request object with valid credentials
        LoginRequest request = new LoginRequest(EMAIL, PASSWORD);

        // Create a user object with matching email and password
        User user = new User(EMAIL, PASSWORD);
        when(userService.loginUser(request)).thenReturn(user);

        // Call the login endpoint
        ResponseEntity<User> response = loginController.login(request);

        // Verify that the response is OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verify that the response body contains the correct user
        User loginResponse = response.getBody();
        assert loginResponse != null;
        assertEquals(EMAIL, loginResponse.getEmail());
        assertEquals(PASSWORD, loginResponse.getPassword());

        // Verify that the loginUser method was called once with the correct request
        verify(userService, times(1)).loginUser(request);
    }

    @Test
    void testLoginWithInvalidCredentialsThrowsAuthenticationException() throws AuthenticationException {
        // Create a request object with invalid credentials
        LoginRequest request = new LoginRequest(EMAIL, "wrong_password");

        // Throw AuthenticationException when trying to log in user
        doThrow(new AuthenticationException("Invalid email or password")).when(userService).loginUser(request);

        // Call the login endpoint and verify that an exception is thrown
        AuthenticationException exception = assertThrows(AuthenticationException.class, () -> {
            loginController.login(request);
        });
        assertEquals("Invalid email or password", exception.getMessage());

        // Verify that the loginUser method was called once with the correct request
        verify(userService, times(1)).loginUser(request);
    }

}
