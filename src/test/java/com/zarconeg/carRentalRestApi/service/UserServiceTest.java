package com.zarconeg.carRentalRestApi.service;

import com.zarconeg.carRentalRestApi.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.util.Date;

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
}
