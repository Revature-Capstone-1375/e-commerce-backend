package com.revature.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jayway.jsonpath.JsonPath;
import com.revature.controllers.UserController;
import com.revature.models.User;
import com.revature.models.enums.UserRoles;
import com.revature.services.UserService;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@WebMvcTest(UserController.class)
@EnableWebMvc
public class UserControllerTests {

    @MockBean
    private UserService userService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    User user = new User(1, "test", "test", "test", "test", UserRoles.USER);
    User user2 = new User(2, "test2", "test2", "test2", "test2", UserRoles.USER);

    @Test
    void shouldCreateMockMvc() {
        Assertions.assertNotNull(mockMvc);
    }

    @Test
    void updateUsersAPI() throws Exception {
        // Convert the user to JSON, so we can make the PUT request in MockMVC.
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(user);

        Mockito.when(userService.updateUser(user,1)).thenReturn(user2);

        // Mock the updateUsers method with MockMVC
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
                .put("/users/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // get the JSON from the response
        String actualEmail = JsonPath.read(result.getResponse().getContentAsString(), "$.email");
        String actualFirstName = JsonPath.read(result.getResponse().getContentAsString(), "$.firstName");
        String actualLastName = JsonPath.read(result.getResponse().getContentAsString(), "$.lastName");
        String actualPassword = JsonPath.read(result.getResponse().getContentAsString(), "$.password");

        Assertions.assertEquals(user2.getEmail(), actualEmail);
        Assertions.assertEquals(user2.getFirstName(), actualFirstName);
        Assertions.assertEquals(user2.getPassword(), actualLastName);
        Assertions.assertEquals(user2.getPassword(), actualPassword);
    }
}
