package com.zarconeg.carRentalRestApi.service;

import com.zarconeg.carRentalRestApi.domain.Ruolo;
import com.zarconeg.carRentalRestApi.repository.RuoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RuoloService {

    @Autowired
    private RuoloRepository repository;

    <S extends Ruolo> S save(S ruolo){
        return repository.save(ruolo);
    }

    <S extends Ruolo> Iterable<S> saveAll(Iterable<S> ruoloList){
        return repository.saveAll(ruoloList);
    }

    Optional<Ruolo> findById(Long id){
        return repository.findById(id);
    }

    boolean existsById(Long id){
        return repository.existsById(id);
    }

    Iterable<Ruolo> findAll(){
        return repository.findAll();
    }

    Iterable<Ruolo> findAllById(Iterable<Long> idList){
        return repository.findAllById(idList);
    }

    long count(){
        return repository.count();
    }

    void deleteById(Long id){
        repository.deleteById(id);
    }

    void delete(Ruolo ruolo){
        repository.delete(ruolo);
    }

    void deleteAll(Iterable<? extends Ruolo> ruoloList){
        repository.deleteAll(ruoloList);
    }

    void deleteAll(){
        repository.deleteAll();
    }

}
