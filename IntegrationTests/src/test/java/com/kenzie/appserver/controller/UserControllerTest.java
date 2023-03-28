package com.kenzie.appserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.appserver.controller.model.UserCreateRequest;
import com.kenzie.appserver.service.UserService;
import com.kenzie.appserver.service.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    private List<User> userList;

    @BeforeEach
    public void setup() {
        userList = new ArrayList<>();
        userList.add(new User("1", "test1", "password1", "test1@example.com"));
        userList.add(new User("2", "test2", "password2", "test2@example.com"));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(userList);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String jsonResult = result.getResponse().getContentAsString();
        List<User> returnedList = objectMapper.readValue(jsonResult, objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
        assert(returnedList.size() == userList.size());
    }

    @Test
    void testCreateUser() throws Exception {
        // Create a new user request
        UserCreateRequest request = new UserCreateRequest("3", "test3", "password3", "test3@example.com");

        // Send a request to create the user
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        // Check if the response is not null before asserting it
        Assertions.assertNotNull(result.getResponse().getContentAsString());
    }



    @Test
    public void testGetUserById() throws Exception {
        User testUser = new User("1", "test1", "password1", "test1@example.com");
        when(userService.getUserById("1")).thenReturn(testUser);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String jsonResult = result.getResponse().getContentAsString();
        User returnedUser = objectMapper.readValue(jsonResult, User.class);
        assert(returnedUser.getId().equals("1"));
    }

}
