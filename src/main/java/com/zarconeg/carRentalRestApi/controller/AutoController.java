package com.zarconeg.carRentalRestApi.controller;

import com.zarconeg.carRentalRestApi.domain.Auto;
import com.zarconeg.carRentalRestApi.exception.auto.AutoNotFoundException;
import com.zarconeg.carRentalRestApi.service.AutoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/autos")
public class AutoController {

    @Autowired
    private AutoService autoService;

    private final Logger LOG = LoggerFactory.getLogger(AutoController.class);

    // GET /api/auto
    // Restituisce tutte le auto
    @GetMapping(value = "/")
    public ResponseEntity<List<Auto>> allCars(){
        List<Auto> cars = (List<Auto>) autoService.findAll();
        if (cars.isEmpty()){
            LOG.error("Tentativo di accedere alla lista delle auto, ma la lista è vuota");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        LOG.info("Mostro la lista delle auto");
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }
    
    // GET /api/auto/{id}
    // Restituisce un auto dato un id
    @GetMapping("/{id}")
    public ResponseEntity<Auto> getAutoById(@PathVariable long id) throws AutoNotFoundException {
        LOG.info("Recupero auto con id: {}", id);
        Optional<Auto> auto = autoService.findById(id);
        if (auto.isPresent()){
            LOG.info("Invio auto {}", auto.get());
            return new ResponseEntity<>(auto.get(), HttpStatus.OK);
        }
        else {
            throw new AutoNotFoundException();
        }
    }
}
