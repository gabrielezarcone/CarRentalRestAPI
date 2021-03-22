package com.zarconeg.carRentalRestApi.service;

import com.zarconeg.carRentalRestApi.domain.User;
import com.zarconeg.carRentalRestApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService{
    
    @Autowired private UserRepository repository;

    <S extends User> S save(S user){
        return repository.save(user);
    }

    <S extends User> Iterable<S> saveAll(Iterable<S> users){
        return repository.saveAll(users);
    }

    Optional<User> findById(Long id){
        return repository.findById(id);
    }

    boolean existsById(Long id){
        return repository.existsById(id);
    }

    Iterable<User> findAll(){
        return repository.findAll();
    }

    Iterable<User> findAllById(Iterable<Long> idList){
        return repository.findAllById(idList);
    }

    long count(){
        return repository.count();
    }

    void deleteById(Long id){
        repository.deleteById(id);
    }

    void delete(User user){
        repository.delete(user);
    }

    void deleteAll(Iterable<? extends User> users){
        repository.deleteAll(users);
    }

    void deleteAll(){
        repository.deleteAll();
    }


}
