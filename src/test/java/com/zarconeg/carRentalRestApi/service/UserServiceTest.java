package com.zarconeg.carRentalRestApi.service;

import com.zarconeg.carRentalRestApi.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
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
    @DisplayName("Trova utente by ID")
    void findUserById(){
        User user = new User();
        user.setUsername("test");
        user.setBirthDate(new Date());
        user.setEmail("test@test.com");
        user.setPassword("testPassword");
        user.setName("Test");
        user.setSurname("Test2");
        // -------------------------------
        User user1 = userService.save(user);
        // -------------------------------
        Optional<User> userOpt = userService.findById(user1.getId());
        assertTrue(userOpt.isPresent());
        userOpt.ifPresent( userObj -> {
            assertEquals(userObj.getUsername(), "test");
            assertEquals(userObj.getEmail(), "test@test.com");
            assertEquals(userObj.getPassword(), "testPassword");
        } );
    }

    @Test
    @DisplayName("Elimina utente")
    void deleteUser(){
        User user = new User();
        user.setUsername("test");
        user.setBirthDate(new Date());
        user.setEmail("test@test.com");
        user.setPassword("testPassword");
        user.setName("Test");
        user.setSurname("Test2");
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
        user.setUsername("test");
        user.setBirthDate(new Date());
        user.setEmail("test@test.com");
        user.setPassword("testPassword");
        user.setName("Test");
        user.setSurname("Test2");
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
