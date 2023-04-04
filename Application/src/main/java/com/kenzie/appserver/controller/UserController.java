package com.kenzie.appserver.controller;

<<<<<<< HEAD
=======

import com.kenzie.appserver.controller.model.UserResponse;
import com.kenzie.appserver.service.UserService;
>>>>>>> c82455f (added the sales and productList double check make sure everything is correct)
import com.kenzie.appserver.service.model.User;
import com.kenzie.appserver.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

<<<<<<< HEAD
    @GetMapping
<<<<<<< HEAD
    public List<User> getAllUsers() {
        return userService.getAllUsers();
=======
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserResponse> responses = new ArrayList<>();
        for (User user : users) {
            responses.add(convertUserToUserResponse(user));
=======

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody UserCreateRequest request) {
        boolean isLoggedIn = userService.login(request);
        if (isLoggedIn) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
>>>>>>> 78e3b20 (login and create user all test passing for service and controller)
        }
        return new ResponseEntity<>(responses, HttpStatus.OK);

>>>>>>> c82455f (added the sales and productList double check make sure everything is correct)
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }
<<<<<<< HEAD
}
=======

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        boolean deleted = userService.deleteUser(id);
        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public UserResponse convertUserToUserResponse(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail());
    }
}

>>>>>>> c82455f (added the sales and productList double check make sure everything is correct)
