package com.zarconeg.carRentalRestApi.controller;

import com.zarconeg.carRentalRestApi.domain.Prenotazione;
import com.zarconeg.carRentalRestApi.exception.prenotazione.PrenotazioneIntegrityException;
import com.zarconeg.carRentalRestApi.exception.prenotazione.PrenotazioneNotFoundException;
import com.zarconeg.carRentalRestApi.service.PrenotazioneService;
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
@RequestMapping("/prenotazione")
public class PrenotazioneController {
    @Autowired
    private PrenotazioneService prenotazioneService;
    
    private static final Logger LOG = LoggerFactory.getLogger(PrenotazioneController.class);

    // GET /api/prenotazione
    // Restituisce tutte le prenotazioni
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Prenotazione>> prenotazioneList(){
        List<Prenotazione> prenotazioneList = (List<Prenotazione>) prenotazioneService.findAll();
        if (prenotazioneList.isEmpty()){
            LOG.error("Tentativo di accedere alla lista prenotazioni, ma la lista è vuota");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        LOG.info("Mostro la lista delle prenotazioni");
        return new ResponseEntity<>(prenotazioneList, HttpStatus.OK);
    }

    // GET /api/prenotazione/{id}
    // Restituisce una prenotazione dato un id
    @GetMapping("/{id}")
    public ResponseEntity<Prenotazione> getPrenotazioneById(@PathVariable long id) throws PrenotazioneNotFoundException {
        LOG.info("Recupero prenotazione con id: {}", id);
        Optional<Prenotazione> prenotazione = prenotazioneService.findById(id);
        if (prenotazione.isPresent()){
            LOG.info("Invio prenotazione {}", prenotazione.get());
            return new ResponseEntity<>(prenotazione.get(), HttpStatus.OK);
        }
        else {
            throw new PrenotazioneNotFoundException();
        }
    }

    // POST /api/prenotazione
    // Crea un nuovo prenotazione
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> newPrenotazione(@RequestBody Prenotazione newPrenotazione, UriComponentsBuilder ucBuilder) throws PrenotazioneIntegrityException {
        LOG.info("Creo nuovo prenotazione: {}", newPrenotazione);
        HttpHeaders headers = new HttpHeaders();
        try {
            prenotazioneService.save(newPrenotazione);
        }catch (DataIntegrityViolationException exception){
            throw new PrenotazioneIntegrityException("Errore di integrità dei dati, impossibile creare nuova prenotazione");
        }
        headers.setLocation(ucBuilder.path("/api/prenotazione/{id}").buildAndExpand(newPrenotazione.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // PUT /api/prenotazione/{id}
    // Aggiorna la prenotazione sostituendo tutte le informazioni con quelle della prenotazione ricevuta
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updatePrenotazione(
            @PathVariable long id,
            @RequestBody Prenotazione updatedPrenotazione,
            UriComponentsBuilder ucBuilder
    ) throws PrenotazioneNotFoundException {
        LOG.info("Recupero prenotazione con id: {} per aggiornarlo", id);
        prenotazioneService.update(id, updatedPrenotazione);
        LOG.info("Aggiornato prenotazione: {}", updatedPrenotazione);
        // --------------------------
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/prenotazione/{id}").buildAndExpand(updatedPrenotazione.getId()).toUri());
        // --------------------------
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    // DELETE /api/prenotazione/{id}
    // Cancella una prenotazione in base all'id
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletePrenotazioneById(@PathVariable long id) throws PrenotazioneNotFoundException {
        LOG.info("Recupero prenotazione con id: {} per eliminazione", id);
        Optional<Prenotazione> prenotazione = prenotazioneService.findById(id);
        if (prenotazione.isPresent()){
            LOG.info("Elimino  prenotazione: {}", prenotazione.get());
            prenotazioneService.delete(prenotazione.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            throw new PrenotazioneNotFoundException();
        }
    }

    // DELETE /api/prenotazione/
    // Cancella tutte le prenotazioni
    @DeleteMapping(value = "/")
    public ResponseEntity<String> deleteAllPrenotazione() {
        LOG.warn("Elimino  tutte le prenotazioni");
        prenotazioneService.deleteAll();
        return new ResponseEntity<>("Eliminate tutte le prenotazioni",HttpStatus.OK);
    }

    // GET /api/prenotazione/count/
    // Ritorna il numero di tutte le prenotazioni
    @GetMapping(value = "/count/")
    public ResponseEntity<Long> countAllPrenotazione() {
        LOG.warn("Conto gli prenotazioni");
        long count = prenotazioneService.count();
        return new ResponseEntity<>(count ,HttpStatus.OK);
    }
}
