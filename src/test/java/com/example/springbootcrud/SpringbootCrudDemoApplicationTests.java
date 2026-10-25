package com.example.springbootcrud;

import com.example.springbootcrud.controller.ApiController;
import com.example.springbootcrud.controller.UserController;
import com.example.springbootcrud.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpringbootCrudDemoApplicationTests {

    private ApiController apiController;
    private UserController userController;

    @BeforeEach
    void setUp() {
        apiController = new ApiController();
        userController = new UserController();
    }

    @Test
    void testHelloEndpoint() {
        String response = apiController.sayHello();
        assertNotNull(response);
        assertTrue(response.contains("Hello!"));
    }

    @Test
    void testStatusEndpoint() {
        String response = apiController.getStatus();
        assertNotNull(response);
        assertTrue(response.contains("UP & Running"));
    }

    @Test
    void testGreetEndpoint() {
        String response = apiController.greetUser("Ravi");
        assertNotNull(response);
        assertTrue(response.contains("Ravi"));
    }

    @Test
    void testGetAllUsers() {
        List<User> users = userController.getAllUsers();
        assertEquals(3, users.size());
    }

    @Test
    void testGetUserById_Found() {
        ResponseEntity<?> response = userController.getUserById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        User user = (User) response.getBody();
        assertEquals("Rajesh Kumar", user.getName());
    }

    @Test
    void testGetUserById_NotFound() {
        ResponseEntity<?> response = userController.getUserById(999L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testCreateUser() {
        User newUser = new User(null, "Vijay Verma", "vijay@example.com");
        ResponseEntity<?> response = userController.createUser(newUser);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        User created = (User) response.getBody();
        assertNotNull(created);
        assertEquals(4L, created.getId());
        assertEquals("Vijay Verma", created.getName());
    }

    @Test
    void testUpdateUser() {
        User updatePayload = new User(null, "Rajesh Updated", "rajesh_new@example.com");
        ResponseEntity<?> response = userController.updateUser(1L, updatePayload);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        User updated = (User) response.getBody();
        assertNotNull(updated);
        assertEquals("Rajesh Updated", updated.getName());
    }

    @Test
    void testDeleteUser() {
        ResponseEntity<String> response = userController.deleteUser(2L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("deleted successfully"));
        assertEquals(2, userController.getAllUsers().size());
    }
}
