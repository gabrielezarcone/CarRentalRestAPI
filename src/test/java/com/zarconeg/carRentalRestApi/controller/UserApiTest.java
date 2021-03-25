package com.zarconeg.carRentalRestApi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zarconeg.carRentalRestApi.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class UserApiTest {  // Questa ed altre soluzioni qui: https://spring.io/guides/gs/testing-web/

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("POST /api/user/")
    void testApi() throws Exception {
        User user = new User();
        user.setUsername("test");
        user.setBirthDate(new Date());
        user.setEmail("test@test.com");
        user.setPassword("testPassword");
        user.setName("Test");
        user.setSurname("Test2");
        user.setPrenotazioneList(new ArrayList<>());
        // ----------------------------------------------
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);
        // ----------------------------------------------
        mockMvc.perform(MockMvcRequestBuilders.post("/user/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }
}
