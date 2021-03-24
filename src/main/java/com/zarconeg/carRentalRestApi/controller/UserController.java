package com.zarconeg.carRentalRestApi.controller;

import com.zarconeg.carRentalRestApi.domain.User;
import com.zarconeg.carRentalRestApi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    // GET /api/user
    // Restituisce tutti gli utenti
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> allUsers(){
        List<User> users = (List<User>) userService.findAll();
        //String u = users.get(0).toString();
        if (users.isEmpty()){
            LOG.error("Tentativo di accedere alla lista utenti, ma la lista è vuota");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        LOG.info("Mostro la lista degli utenti");
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

     // POST /api/user
     // Crea un nuovo utente
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> newUser(@RequestBody User newUser, UriComponentsBuilder ucBuilder){
        userService.save(newUser);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(newUser.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
}
