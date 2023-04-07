package com.kenzie.appserver.controller;

<<<<<<< HEAD
import com.kenzie.appserver.controller.model.LoginCreateRequest;
import com.kenzie.appserver.controller.model.LoginResponse;
=======
import com.kenzie.appserver.controller.model.LoginRequest;
>>>>>>> 78e3b20 (login and create user all test passing for service and controller)
import com.kenzie.appserver.service.UserService;
import com.kenzie.appserver.service.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
import javax.crypto.SecretKey;
import java.util.Objects;

=======

import javax.naming.AuthenticationException;

import static org.junit.Assert.assertThrows;
>>>>>>> 78e3b20 (login and create user all test passing for service and controller)
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LoginControllerTest {

    private LoginController loginController;

    @Mock
    private UserService userService;
<<<<<<< HEAD
    private SecretKey key;


    @BeforeEach
    public void setUp() {
        userService = mock(UserService.class);
        MockitoAnnotations.openMocks(this);
        key = userService.generateKey();
        loginController = new LoginController(userService);
    }




    @Test
    public void testLoginSuccessful() {
        String username = "alice";
        String password = "password";
        User user = new User(username, password, password, password);
        LoginCreateRequest loginCreateRequest = new LoginCreateRequest(username, password );
        String token = "jwt.token.12345";

        // Mock the behavior of the userService
        when(userService.findUserByUsername(username)).thenReturn(user);
        when(userService.authenticateUser(loginCreateRequest)).thenReturn(true);
        when(userService.createLoginToken(user, key)).thenReturn(token);

        // Call the login endpoint
        ResponseEntity<LoginResponse> response = loginController.login(loginCreateRequest);

        // Verify that the response contains the correct token and status code
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(token, Objects.requireNonNull(response.getBody()).getToken());

        // Verify that the userService methods were called with the correct arguments
        verify(userService).findUserByUsername(username);
        verify(userService).authenticateUser(loginCreateRequest);
        verify(userService).createLoginToken(user, key);
    }

    @Test
    void testLoginUnauthorized() {

        String username = "alice";
        String password = "password";
        LoginCreateRequest loginCreateRequest = new LoginCreateRequest(username, password );
        loginCreateRequest.setUsername("alice");
        loginCreateRequest.setPassword("wrong_password");

        when(userService.authenticateUser(eq(loginCreateRequest))).thenReturn(false);


        ResponseEntity<LoginResponse> response = loginController.login(loginCreateRequest);

        verify(userService).authenticateUser(eq(loginCreateRequest));
        verify(userService, never()).findUserByUsername(anyString());
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
    @Test
    void testLogout() {
        String token = "test-token";

        when(userService.invalidateToken("Bearer " + token)).thenReturn(true);


        ResponseEntity<Void> response = loginController.logout("Bearer " + token);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verify that invalidateToken was called exactly once with the expected argument
        verify(userService, times(1)).invalidateToken("Bearer " + token);
    }

    @Test
    void testLogoutFailure() {
        String token = "test-token";
        // Mock the failure response from the service layer
        when(userService.invalidateToken("Bearer " + token)).thenReturn(false);
        // Call the logout endpoint with the correct token format
        ResponseEntity<Void> response = loginController.logout("Bearer " + token);
        // Verify that the service layer was called with the correct token
        verify(userService).invalidateToken("Bearer " + token);
        // Verify that the response has the correct status code
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
}


=======

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
>>>>>>> 78e3b20 (login and create user all test passing for service and controller)
