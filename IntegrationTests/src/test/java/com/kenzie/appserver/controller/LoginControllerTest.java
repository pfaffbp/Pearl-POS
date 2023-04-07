//package com.kenzie.appserver.controller;
//
//import com.kenzie.appserver.controller.model.LoginCreateRequest;
//import com.kenzie.appserver.controller.model.LoginResponse;
//import com.kenzie.appserver.service.UserService;
//import com.kenzie.appserver.service.model.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.Objects;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//public class LoginControllerTest {
//
//
//    private LoginController loginController;
//
//    @Mock
//    private UserService userService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        loginController = new LoginController(userService);
//    }
//
//    private static final String EMAIL = "alice@example.com";
//    private static final String PASSWORD = "password";
//
//    @Test
//    public void testLoginWithValidCredentialsReturnsLoginResponse() {
//
//        // Create a request object with valid credentials
//        LoginCreateRequest request = new LoginCreateRequest(EMAIL, PASSWORD);
//
//        // Create a user object with matching email and password
//        User user = new User(EMAIL, PASSWORD);
//        when(userService.findUserByEmail(EMAIL)).thenReturn(user);
//
//        // Call the login endpoint
//        ResponseEntity<LoginResponse> response = loginController.login(request);
//
//        // Verify that the response is OK and contains the expected message
//        assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatusCodeValue());
//        assertEquals("Login successful for user " + EMAIL, Objects.requireNonNull(response.getBody()).getMessage());
//
//        // Verify that the findUserByEmail method was called once with the correct email
//        verify(userService, times(1)).findUserByEmail(EMAIL);
//    }
//
//    @Test
//    void testLoginWithInvalidCredentialsReturnsUnauthorized() {
//
//        // Create a request object with invalid credentials
//        LoginCreateRequest request = new LoginCreateRequest(EMAIL, "wrong_password");
//
//        // Create a user object with matching email and password
//        User user = new User(EMAIL, PASSWORD);
//        when(userService.findUserByEmail(EMAIL)).thenReturn(user);
//
//        // Call the login endpoint
//        ResponseEntity<LoginResponse> response = loginController.login(request);
//
//        // Verify that the response is UNAUTHORIZED
//        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
//
//        // Verify that the findUserByEmail method was called once with the correct email
//        verify(userService, times(1)).findUserByEmail(EMAIL);
//    }
//}