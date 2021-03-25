package com.zarconeg.carRentalRestApi.service;

import com.zarconeg.carRentalRestApi.domain.Ruolo;
import com.zarconeg.carRentalRestApi.repository.RuoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RuoloService {

    @Autowired
    private RuoloRepository repository;

    public <S extends Ruolo> S save(S ruolo){
        return repository.save(ruolo);
    }

    public <S extends Ruolo> Iterable<S> saveAll(Iterable<S> ruoloList){
        return repository.saveAll(ruoloList);
    }

    public Optional<Ruolo> findById(Long id){
        return repository.findById(id);
    }

    public boolean existsById(Long id){
        return repository.existsById(id);
    }

    public Iterable<Ruolo> findAll(){
        return repository.findAll();
    }

    public Iterable<Ruolo> findAllById(Iterable<Long> idList){
        return repository.findAllById(idList);
    }

    public long count(){
        return repository.count();
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public void delete(Ruolo ruolo){
        repository.delete(ruolo);
    }

    public void deleteAll(Iterable<? extends Ruolo> ruoloList){
        repository.deleteAll(ruoloList);
    }

    public void deleteAll(){
        repository.deleteAll();
    }

}
