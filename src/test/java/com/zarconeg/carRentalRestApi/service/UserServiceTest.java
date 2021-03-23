package com.zarconeg.carRentalRestApi.service;

import com.zarconeg.carRentalRestApi.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // usa il database reale e non uno autoconfigurato
class UserServiceTest {

    @Autowired private UserService userService;

    @Test
    @DisplayName("Salva nuovo utente")
    void saveUser(){
        User user = new User();
        user.setUsername("test");
        user.setBirthDate(new Date());
        user.setEmail("test@test.com");
        user.setPassword("testPassword");
        user.setName("Test");
        user.setSurname("Test2");
        // -------------------------------
        User user1 = userService.save(user);
        assertEquals(user,user1);
    }

    @Test
    @DisplayName("Salva nuovo utente con una mail già usata")
    void saveUserDuplicatedMail(){
        User user = new User();
        user.setUsername("test0");
        user.setEmail("test0@test.com");
        user.setPassword("testPassword");
        // -------------------------------
        User user2 = new User();
        user2.setUsername("test01");
        user2.setEmail("test0@test.com");
        user2.setPassword("testPassword");
        // -------------------------------
        userService.save(user);
        Exception exception = assertThrows(org.springframework.dao.DataIntegrityViolationException.class, () -> {
            userService.save(user2);
        });
        // -------------------------------
        // String expectedMessage = "For input string";
        // String actualMessage = exception.getMessage();
        // assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Trova utente by ID")
    void findUserById(){
        User user = new User();
        user.setUsername("test1");
        user.setBirthDate(new Date());
        user.setEmail("test1@test.com");
        user.setPassword("testPassword");
        user.setName("Test1");
        user.setSurname("Test1-2");
        // -------------------------------
        User user1 = userService.save(user);
        // -------------------------------
        Optional<User> userOpt = userService.findById(user1.getId());
        assertTrue(userOpt.isPresent());
        userOpt.ifPresent( userObj -> {
            assertEquals(userObj.getUsername(), "test1");
            assertEquals(userObj.getEmail(), "test1@test.com");
            assertEquals(userObj.getPassword(), "testPassword");
        } );
    }

    @Test
    @DisplayName("Elimina utente")
    void deleteUser(){
        User user = new User();
        user.setUsername("test2");
        user.setBirthDate(new Date());
        user.setEmail("test2@test.com");
        user.setPassword("testPassword");
        user.setName("Test2");
        user.setSurname("Test2-2");
        // -------------------------------
        User user1 = userService.save(user);
        // -------------------------------
        Optional<User> userOpt = userService.findById(user1.getId());
        assertTrue(userOpt.isPresent());
        // -------------------------------
        userOpt.ifPresent( userObj -> userService.delete(userObj));
        // -------------------------------
        userOpt = userService.findById(user1.getId());
        assertFalse(userOpt.isPresent());
    }

    @Test
    @DisplayName("Elimina utente by ID")
    void deleteUserById(){
        User user = new User();
        user.setUsername("test3");
        user.setBirthDate(new Date());
        user.setEmail("test3@test.com");
        user.setPassword("test3Password");
        user.setName("Test3");
        user.setSurname("Test3-2");
        // -------------------------------
        User user1 = userService.save(user);
        // -------------------------------
        Optional<User> userOpt = userService.findById(user1.getId());
        assertTrue(userOpt.isPresent());
        // -------------------------------
        userOpt.ifPresent( userObj -> userService.deleteById(userObj.getId()));
        // -------------------------------
        userOpt = userService.findById(user1.getId());
        assertFalse(userOpt.isPresent());
    }
}
