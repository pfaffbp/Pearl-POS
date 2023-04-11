package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.UserCreateRequest;
import com.kenzie.appserver.controller.model.UserLoginRequest;
import com.kenzie.appserver.repositories.model.UserRecord;
import com.kenzie.appserver.service.UserService;
import com.kenzie.appserver.service.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/userTable")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> createUser(@RequestBody @Valid UserCreateRequest request) {
        boolean success = userService.createUser(request.getEmail(), request.getPassword());
        return success ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody @Valid UserLoginRequest request) {
        User user = userService.login(request.getEmail(), request.getPassword());
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @DeleteMapping("/deleteUserByEmail")
    public ResponseEntity<Void> deleteByEmail(@RequestParam String email) {
        boolean success = userService.deleteUserByEmail(email);
        return success ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @PutMapping("/updateUser")
    public ResponseEntity<Void> forgotPassword(@RequestParam String email, @RequestParam String password) {
        boolean success = userService.updateUserByEmail(email, password);
        return success ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @GetMapping("/users/{email}")
    public ResponseEntity<User> getUser(@PathVariable String email) {
        Optional<UserRecord> user = userService.getUser(email);
        return user.map(userRecord -> ResponseEntity.ok(new User(userRecord.getEmail(), userRecord.getPassword()))).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("/check")
    public ResponseEntity<Boolean> checkEmailUnique(@RequestBody @Valid UserLoginRequest request) {
        boolean success = userService.checkEmailUniqueness(request.getEmail());
        return success ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @GetMapping ("/getByEmail")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        Optional<UserRecord> user = userService.getUser(email);
        return user.map(userRecord -> ResponseEntity.ok(new User(userRecord.getEmail(), userRecord.getPassword()))).orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @GetMapping ("/getAll")
    public ResponseEntity<Iterable<UserRecord>> getAllUsers() {
        Iterable<UserRecord> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping ("/logout")
    public ResponseEntity<Void> logoutUser() {
        return ResponseEntity.ok().build();

    }

}
