//package com.kenzie.appserver.service;
//
//import com.kenzie.appserver.controller.model.UserCreateRequest;
//import com.kenzie.appserver.repositories.UserRepository;
//import com.kenzie.appserver.repositories.model.UserRecord;
//import com.kenzie.appserver.service.model.User;
//import io.jsonwebtoken.lang.Assert;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.util.AssertionErrors.assertEquals;
//
//public class UserServiceTest {
//
//    private UserService userService;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @BeforeEach
//    void setUp() {
//        // configure userRepository mock
//        List<User> userList = new ArrayList<>();
//        User user = new User();
//        user.setPassword("John");
//        user.setEmail("john@test.com");
//        userList.add(user);
//
//        when(userRepository.findAll()).thenReturn(userList);
//    }
//
//
//    @Test
//    void testCreateUser() {
//        // GIVEN
//        UserCreateRequest request = new UserCreateRequest("username", "password");
//        UserRecord record = new UserRecord(request.getEmail(), request.getPassword());
//        when(userRepository.save(any())).thenReturn(record);
//
//        // WHEN
//        User createdUser = userService.createUser(request);
//
//        // THEN
//        Assert.notNull(createdUser, "The created user is not null");
//        assertEquals("The password matches", request.getPassword(), createdUser.getPassword());
//        assertEquals("The email matches", request.getEmail(), createdUser.getEmail());
//        verify(userRepository, times(1)).save(any(UserRecord.class));
//    }
//
//
//    @Test
//    void testGetAllUsers() {
//        // create a user object
//        User user = new User();
//        user.setPassword("John");
//        user.setEmail("john@test.com");
//
//        // save the user to the database
//        userRepository.save(user);
//
//        // retrieve all users from the database
//        List<User> userList = userService.getAllUsers();
//
//        // verify that the user is in the list
//        assertTrue(userList.contains(user));
//    }
//
//
//}
//
