package com.zarconeg.carRentalRestApi.service;

import com.zarconeg.carRentalRestApi.domain.Prenotazione;
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

    <S extends Prenotazione> S save(S prenotazione){
        return repository.save(prenotazione);
    }

    <S extends Prenotazione> Iterable<S> saveAll(Iterable<S> prenotazioneList){
        return repository.saveAll(prenotazioneList);
    }

    Optional<Prenotazione> findById(Long id){
        return repository.findById(id);
    }

    boolean existsById(Long id){
        return repository.existsById(id);
    }

    Iterable<Prenotazione> findAll(){
        return repository.findAll();
    }

    Iterable<Prenotazione> findAllById(Iterable<Long> idList){
        return repository.findAllById(idList);
    }

    long count(){
        return repository.count();
    }

    void deleteById(Long id){
        repository.deleteById(id);
    }

    void delete(Prenotazione prenotazione){
        repository.delete(prenotazione);
    }

    void deleteAll(Iterable<? extends Prenotazione> prenotazioneList){
        repository.deleteAll(prenotazioneList);
    }

    void deleteAll(){
        repository.deleteAll();
    }


}
