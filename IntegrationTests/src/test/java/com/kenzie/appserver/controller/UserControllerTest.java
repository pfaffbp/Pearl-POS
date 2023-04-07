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
