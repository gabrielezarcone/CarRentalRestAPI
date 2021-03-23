package com.zarconeg.carRentalRestApi.service;

import com.zarconeg.carRentalRestApi.domain.User;
import com.zarconeg.carRentalRestApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService{
    
    @Autowired private UserRepository repository;

    public <S extends User> S save(S user){
        return repository.save(user);
    }

    public <S extends User> Iterable<S> saveAll(Iterable<S> users){
        return repository.saveAll(users);
    }

    Optional<User> findById(Long id){
        return repository.findById(id);
    }

    public boolean existsById(Long id){
        return repository.existsById(id);
    }

    public Iterable<User> findAll(){
        return repository.findAll();
    }

    public Iterable<User> findAllById(Iterable<Long> idList){
        return repository.findAllById(idList);
    }

    public long count(){
        return repository.count();
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public void delete(User user){
        repository.delete(user);
    }

    public void deleteAll(Iterable<? extends User> users){
        repository.deleteAll(users);
    }

    public void deleteAll(){
        repository.deleteAll();
    }


}
