package com.zarconeg.carRentalRestApi.service;

import com.zarconeg.carRentalRestApi.domain.Auto;
import com.zarconeg.carRentalRestApi.repository.AutoRepository;
import com.zarconeg.carRentalRestApi.repository.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutoService {

    @Autowired
    private AutoRepository repository;

    <S extends Auto> S save(S auto){
        return repository.save(auto);
    }

    <S extends Auto> Iterable<S> saveAll(Iterable<S> autos){
        return repository.saveAll(autos);
    }

    Optional<Auto> findById(Long id){
        return repository.findById(id);
    }

    boolean existsById(Long id){
        return repository.existsById(id);
    }

    Iterable<Auto> findAll(){
        return repository.findAll();
    }

    Iterable<Auto> findAllById(Iterable<Long> idList){
        return repository.findAllById(idList);
    }

    long count(){
        return repository.count();
    }

    void deleteById(Long id){
        repository.deleteById(id);
    }

    void delete(Auto auto){
        repository.delete(auto);
    }

    void deleteAll(Iterable<? extends Auto> autos){
        repository.deleteAll(autos);
    }

    void deleteAll(){
        repository.deleteAll();
    }

}
