<<<<<<< HEAD
package com.kenzie.appserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.appserver.controller.model.UserCreateRequest;
import com.kenzie.appserver.service.UserService;
import com.kenzie.appserver.service.model.User;
<<<<<<< HEAD
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
=======
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest {

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private UserService userService;

    @Captor
    private ArgumentCaptor<UserCreateRequest> userCreateRequestCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userService)).build();
>>>>>>> 78e3b20 (login and create user all test passing for service and controller)
    }

    @Test
    void testCreateUser() throws Exception {
<<<<<<< HEAD
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
=======
        // Arrange
        UserCreateRequest request = new UserCreateRequest("email@example.com", "password");
        User user = new User(request.getEmail(), request.getPassword());
        when(userService.createUser(any(UserCreateRequest.class))).thenReturn(user);

        // Act
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request));
        mockMvc.perform(builder)
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(request.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value(request.getPassword()));

        // Assert
        verify(userService).createUser(userCreateRequestCaptor.capture());
        UserCreateRequest capturedRequest = userCreateRequestCaptor.getValue();
        assertEquals(request.getEmail(), capturedRequest.getEmail());
        assertEquals(request.getPassword(), capturedRequest.getPassword());
    }
}
>>>>>>> 78e3b20 (login and create user all test passing for service and controller)
=======
//package com.kenzie.appserver.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.kenzie.appserver.controller.model.UserCreateRequest;
//import com.kenzie.appserver.service.UserService;
//import com.kenzie.appserver.service.model.User;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.ResultMatcher;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(UserController.class)
//public class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private UserService userService;
//
//    private List<User> userList;
//
//    @BeforeEach
//    public void setup() {
//        userList = new ArrayList<>();
//        userList.add(new User("test1@example.com", "password1"));
//        userList.add(new User("test2@example.com", "password2"));
//    }
//
//
//    @Test
//    void testCreateUser() throws Exception {
//        UserCreateRequest request = new UserCreateRequest("test3@example.com", "password3");
//        User user = new User(request.getEmail(), request.getPassword());
//        when(userService.createUser(any(User.class))).thenReturn(user);
//
//        MvcResult result = mockMvc.perform(
//                MockMvcRequestBuilders.post("/users")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request))
//        ).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
//
//        Assertions.assertNotNull(result.getResponse().getContentAsString());
//    }
//}
>>>>>>> eb90ed7 (Revert "login and create user all test passing for service and controller")
