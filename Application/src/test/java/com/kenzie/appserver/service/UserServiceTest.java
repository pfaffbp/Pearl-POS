package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.UserRepository;
import com.kenzie.appserver.repositories.model.UserRecord;
import com.kenzie.appserver.service.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Iterator;
import java.util.Optional;

import static jdk.internal.vm.compiler.word.LocationIdentity.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.ExpectedCount.never;
import static org.springframework.test.web.client.ExpectedCount.times;
import static org.testcontainers.shaded.com.google.common.base.Verify.verify;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    void createUser_valid() {
        // GIVEN
        String email = "test@example.com";
        String password = "password";
        when(userRepository.findById(email)).thenReturn(Optional.empty());

        // WHEN
        boolean result = userService.createUser(email, password);

        // THEN
        Assertions.assertTrue(result, "The user was created successfully");
    }

    @Test
    void createUser_invalid() {
        // GIVEN
        String email = "test@example.com";
        String password = "password";
        when(userRepository.findById(email)).thenReturn(Optional.of(new UserRecord(email, password)));

        // WHEN
        boolean result = userService.createUser(email, password);

        // THEN
        Assertions.assertFalse(result, "The user was not created because email already exists");
    }

    @Test
    void login_valid() {
        // GIVEN
        String email = "test@example.com";
        String password = "password";
        when(userRepository.findById(email)).thenReturn(Optional.of(new UserRecord(email, password)));

        // WHEN
        User user = userService.login(email, password);

        // THEN
        Assertions.assertNotNull(user, "The user is not null when login credentials are correct");
    }

    @Test
    void login_invalid() {
        // GIVEN
        String email = "test@example.com";
        String password = "password";
        when(userRepository.findById(email)).thenReturn(Optional.empty());

        // WHEN
        User user = userService.login(email, password);

        // THEN
        Assertions.assertNull(user, "The user is null when login credentials are incorrect");
    }

    @Test
    void getUser() {
        // GIVEN
        String email = "test@example.com";
        when(userRepository.findById(email)).thenReturn(Optional.of(new UserRecord(email, "password")));

        // WHEN
        Optional<UserRecord> userRecord = userService.getUser(email);

        // THEN
        Assertions.assertTrue(userRecord.isPresent(), "The user record is present");
        Assertions.assertEquals(email, userRecord.get().getEmail(), "The email matches");
    }

    @Test
    void checkEmailUniqueness_unique() {
        // GIVEN
        String email = "test@example.com";
        when(userRepository.findById(email)).thenReturn(Optional.empty());

        // WHEN
        boolean result = userService.checkEmailUniqueness(email);

        // THEN
        Assertions.assertTrue(result, "The email is unique");
    }

    @Test
    void checkEmailUniqueness_notUnique() {
        // GIVEN
        String email = "test@example.com";
        when(userRepository.findById(email)).thenReturn(Optional.of(new UserRecord(email, "password")));

        // WHEN
        boolean result = userService.checkEmailUniqueness(email);

        // THEN
        Assertions.assertFalse(result, "The email is not unique");
    }

    @Test
    void getAllUsers() {
        // GIVEN
        String email = "  email@example.com";
        when(userRepository.findAll()).thenReturn(() -> new Iterator<UserRecord>() {
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public UserRecord next() {
                return new UserRecord(email, "password");
            }
        });
        // WHEN
        Iterable<UserRecord> userRecords = userService.getAllUsers();

        // THEN
        Assertions.assertNotNull(userRecords, "The user records are not null");
        Assertions.assertTrue(userRecords.iterator().hasNext(), "The user records are not empty");
        Assertions.assertEquals(email, userRecords.iterator().next().getEmail(), "The email matches");

    }
    @Test
    void testDeleteUserByEmail() {
        // GIVEN
        String email = "test@example.com";
        UserRecord userRecord = new UserRecord(email, "password");
        Mockito.when(userRepository.findById(email)).thenReturn(Optional.of(userRecord));

        // WHEN
        boolean deleted = userService.deleteUserByEmail(email);

        // THEN
        Mockito.verify(userRepository, Mockito.times(1)).delete(userRecord);
        Assertions.assertTrue(deleted, "The user should be deleted successfully");
    }

    @Test
    void testDeleteUserByEmailNotFound() {
        // GIVEN
        String email = "test@example.com";
        Mockito.when(userRepository.findById(email)).thenReturn(Optional.empty());

        // WHEN
        boolean deleted = userService.deleteUserByEmail(email);

        // THEN
        Mockito.verify(userRepository, Mockito.times(0)).delete(Mockito.any(UserRecord.class));
        Assertions.assertFalse(deleted, "The user should not be deleted if not found");
    }

    @Test
    void testUpdateUserByEmail() {
        // GIVEN
        String email = "test@example.com";
        String newPassword = "newpassword";
        UserRecord userRecord = new UserRecord(email, "password");
        Mockito.when(userRepository.findById(email)).thenReturn(Optional.of(userRecord));

        // WHEN
        boolean updated = userService.updateUserByEmail(email, newPassword);

        // THEN
        Mockito.verify(userRepository, Mockito.times(1)).save(userRecord);
        Assertions.assertTrue(updated, "The user password should be updated successfully");
        Assertions.assertEquals(newPassword, userRecord.getPassword(), "The user password should be changed");
    }

    @Test
    void testUpdateUserByEmailNotFound() {
        // GIVEN
        String email = "test@example.com";
        String newPassword = "newpassword";
        Mockito.when(userRepository.findById(email)).thenReturn(Optional.empty());

        // WHEN
        boolean updated = userService.updateUserByEmail(email, newPassword);

        // THEN
        Mockito.verify(userRepository, Mockito.times(0)).save(Mockito.any(UserRecord.class));
        Assertions.assertFalse(updated, "The user should not be updated if not found");
    }
}