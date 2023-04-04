package com.kenzie.appserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.appserver.controller.model.UserCreateRequest;
import com.kenzie.appserver.service.UserService;
import com.kenzie.appserver.service.model.User;
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
    }

    @Test
    void testCreateUser() throws Exception {
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