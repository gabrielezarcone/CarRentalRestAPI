package com.zarconeg.carRentalRestApi.service;

import com.zarconeg.carRentalRestApi.domain.Prenotazione;
import com.zarconeg.carRentalRestApi.domain.Prenotazione;
import com.zarconeg.carRentalRestApi.exception.prenotazione.PrenotazioneNotFoundException;
import com.zarconeg.carRentalRestApi.exception.prenotazione.PrenotazioneNotFoundException;
import com.zarconeg.carRentalRestApi.repository.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository repository;

    public void update(long id, Prenotazione updatedPrenotazione) throws PrenotazioneNotFoundException {
        Optional<Prenotazione> prenotazioneOptional = findById(id);
        if (prenotazioneOptional.isPresent()){
            Prenotazione prenotazione = prenotazioneOptional.get();
            prenotazione.setStato(updatedPrenotazione.getStato());
            prenotazione.setInizio(updatedPrenotazione.getInizio());
            prenotazione.setFine(updatedPrenotazione.getFine());
            prenotazione.setUser(updatedPrenotazione.getUser());
            prenotazione.setAuto(updatedPrenotazione.getAuto());
        }
        else {
            throw new PrenotazioneNotFoundException();
        }
    }
    public <S extends Prenotazione> S save(S prenotazione){
        return repository.save(prenotazione);
    }

    public <S extends Prenotazione> Iterable<S> saveAll(Iterable<S> prenotazioneList){
        return repository.saveAll(prenotazioneList);
    }

    public Optional<Prenotazione> findById(Long id){
        return repository.findById(id);
    }

    public boolean existsById(Long id){
        return repository.existsById(id);
    }

    public Iterable<Prenotazione> findAll(){
        return repository.findAll();
    }

    public Iterable<Prenotazione> findAllById(Iterable<Long> idList){
        return repository.findAllById(idList);
    }

    public long count(){
        return repository.count();
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public void delete(Prenotazione prenotazione){
        repository.delete(prenotazione);
    }

    public void deleteAll(Iterable<? extends Prenotazione> prenotazioneList){
        repository.deleteAll(prenotazioneList);
    }

    public void deleteAll(){
        repository.deleteAll();
    }


}
