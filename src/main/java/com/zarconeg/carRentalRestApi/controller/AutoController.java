package com.zarconeg.carRentalRestApi.controller;

import com.zarconeg.carRentalRestApi.domain.Auto;
import com.zarconeg.carRentalRestApi.exception.auto.AutoNotFoundException;
import com.zarconeg.carRentalRestApi.exception.auto.AutoIntegrityException;
import com.zarconeg.carRentalRestApi.service.AutoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auto")
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
    
    // POST /api/auto
    // Crea una nuova auto
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> newAuto(@RequestBody Auto newAuto, UriComponentsBuilder ucBuilder) throws AutoIntegrityException {
        LOG.info("Creo nuova auto: {}", newAuto);
        HttpHeaders headers = new HttpHeaders();
        try {
            autoService.save(newAuto);
        }catch (DataIntegrityViolationException exception){
            throw new AutoIntegrityException("Errore di integrità dei dati, impossibile creare nuova auto");
        }
        headers.setLocation(ucBuilder.path("/api/auto/{id}").buildAndExpand(newAuto.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // PUT /api/auto/{id}
    // Aggiorna l'auto sostituendo tutte le informazioni con quelle dell'auto ricevuta
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateAuto(
            @PathVariable long id,
            @RequestBody Auto updatedAuto,
            UriComponentsBuilder ucBuilder
    ) throws AutoNotFoundException {
        LOG.info("Recupero auto con id: {} per aggiornarla", id);
        autoService.update(id, updatedAuto);
        LOG.info("Aggiornata auto: {}", updatedAuto);
        // --------------------------
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/auto/{id}").buildAndExpand(updatedAuto.getId()).toUri());
        // --------------------------
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    // DELETE /api/auto/{id}
    // Cancella auto in base all'id
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteAutoById(@PathVariable long id) throws AutoNotFoundException {
        LOG.info("Recupero auto con id: {} per eliminazione", id);
        Optional<Auto> auto = autoService.findById(id);
        if (auto.isPresent()){
            LOG.info("Elimino auto: {}", auto.get());
            autoService.delete(auto.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            throw new AutoNotFoundException();
        }
    }
    
    // DELETE /api/auto/
    // Cancella tutte le auto del parco auto
    @DeleteMapping(value = "/")
    public ResponseEntity<String> deleteAllAuto() {
        LOG.warn("Elimino  tutte le auto");
        autoService.deleteAll();
        return new ResponseEntity<>("Eliminati tutte le auto",HttpStatus.OK);
    }

    // GET /api/auto/count/
    // Ritorna il numero di tutte le auto
    @GetMapping(value = "/count/")
    public ResponseEntity<Long> countCars() {
        LOG.warn("Conto le auto");
        long count = autoService.count();
        return new ResponseEntity<>(count ,HttpStatus.OK);
    }
}
