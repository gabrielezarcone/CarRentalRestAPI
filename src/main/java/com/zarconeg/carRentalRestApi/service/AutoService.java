package com.zarconeg.carRentalRestApi.service;

import com.zarconeg.carRentalRestApi.domain.Auto;
import com.zarconeg.carRentalRestApi.repository.AutoRepository;
import com.zarconeg.carRentalRestApi.repository.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AutoService {

    @Autowired
    private AutoRepository repository;

    public <S extends Auto> S save(S auto){
        return repository.save(auto);
    }

    public <S extends Auto> Iterable<S> saveAll(Iterable<S> autos){
        return repository.saveAll(autos);
    }

    public Optional<Auto> findById(Long id){
        return repository.findById(id);
    }

    public boolean existsById(Long id){
        return repository.existsById(id);
    }

    public Iterable<Auto> findAll(){
        return repository.findAll();
    }

    public Iterable<Auto> findAllById(Iterable<Long> idList){
        return repository.findAllById(idList);
    }

    public long count(){
        return repository.count();
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public void delete(Auto auto){
        repository.delete(auto);
    }

    public void deleteAll(Iterable<? extends Auto> autos){
        repository.deleteAll(autos);
    }

    public void deleteAll(){
        repository.deleteAll();
    }

}
