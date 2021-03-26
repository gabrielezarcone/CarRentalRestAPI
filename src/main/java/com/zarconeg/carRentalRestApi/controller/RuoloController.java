package com.zarconeg.carRentalRestApi.controller;

import com.zarconeg.carRentalRestApi.domain.Ruolo;
import com.zarconeg.carRentalRestApi.exception.ruolo.RuoloIntegrityException;
import com.zarconeg.carRentalRestApi.exception.ruolo.RuoloNotFoundException;
import com.zarconeg.carRentalRestApi.service.RuoloService;
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
@RequestMapping("/ruolo")
public class RuoloController {
    @Autowired
    private RuoloService ruoloService;
    
    private static final Logger LOG = LoggerFactory.getLogger(RuoloController.class);
    
    // GET /api/ruolo
    // Restituisce tutti i ruoli
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Ruolo>> ruoloList(){
        List<Ruolo> ruolos = (List<Ruolo>) ruoloService.findAll();
        if (ruolos.isEmpty()){
            LOG.error("Tentativo di accedere alla lista ruoli, ma la lista è vuota");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        LOG.info("Mostro la lista dei ruoli");
        return new ResponseEntity<>(ruolos, HttpStatus.OK);
    }

    // GET /api/ruolo/{id}
    // Restituisce un ruolo dato un id
    @GetMapping("/{id}")
    public ResponseEntity<Ruolo> getRuoloById(@PathVariable long id) throws RuoloNotFoundException {
        LOG.info("Recupero ruolo con id: {}", id);
        Optional<Ruolo> ruolo = ruoloService.findById(id);
        if (ruolo.isPresent()){
            LOG.info("Invio ruolo {}", ruolo.get());
            return new ResponseEntity<>(ruolo.get(), HttpStatus.OK);
        }
        else {
            throw new RuoloNotFoundException();
        }
    }

    // POST /api/ruolo
    // Crea un nuovo ruolo
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> newRuolo(@RequestBody Ruolo newRuolo, UriComponentsBuilder ucBuilder) throws RuoloIntegrityException {
        LOG.info("Creo nuovo ruolo: {}", newRuolo);
        HttpHeaders headers = new HttpHeaders();
        try {
            ruoloService.save(newRuolo);
        }catch (DataIntegrityViolationException exception){
            throw new RuoloIntegrityException("Errore di integrità dei dati, impossibile creare nuovo ruolo");
        }
        headers.setLocation(ucBuilder.path("/api/ruolo/{id}").buildAndExpand(newRuolo.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // PUT /api/ruolo/{id}
    // Aggiorna il ruolo sostituendo tutte le informazioni con quelle del ruolo ricevuto
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateRuolo(
            @PathVariable long id,
            @RequestBody Ruolo updatedRuolo,
            UriComponentsBuilder ucBuilder
    ) throws RuoloNotFoundException {
        LOG.info("Recupero ruolo con id: {} per aggiornarlo", id);
        ruoloService.update(id, updatedRuolo);
        LOG.info("Aggiornato ruolo: {}", updatedRuolo);
        // --------------------------
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/ruolo/{id}").buildAndExpand(updatedRuolo.getId()).toUri());
        // --------------------------
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    // DELETE /api/ruolo/{id}
    // Cancella un ruolo in base all'id
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteRuoloById(@PathVariable long id) throws RuoloNotFoundException {
        LOG.info("Recupero ruolo con id: {} per eliminazione", id);
        Optional<Ruolo> ruolo = ruoloService.findById(id);
        if (ruolo.isPresent()){
            LOG.info("Elimino  ruolo: {}", ruolo.get());
            ruoloService.delete(ruolo.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            throw new RuoloNotFoundException();
        }
    }

    // DELETE /api/ruolo/
    // Cancella tutti i ruoli
    @DeleteMapping(value = "/")
    public ResponseEntity<String> deleteAllRuolo() {
        LOG.warn("Elimino  tutti i ruoli");
        ruoloService.deleteAll();
        return new ResponseEntity<>("Eliminati tutti i ruoli",HttpStatus.OK);
    }

    // GET /api/ruolo/count/
    // Ritorna il numero di tutti i ruoli
    @GetMapping(value = "/count/")
    public ResponseEntity<Long> countAllRuolo() {
        LOG.info("Conto i ruoli");
        long count = ruoloService.count();
        return new ResponseEntity<>(count ,HttpStatus.OK);
    }
}
