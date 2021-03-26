package com.zarconeg.carRentalRestApi.service;

import com.zarconeg.carRentalRestApi.domain.User;
import com.zarconeg.carRentalRestApi.exception.user.UserNotFoundException;
import com.zarconeg.carRentalRestApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

@Service
@Transactional
public class UserService{
    
    @Autowired private UserRepository repository;
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

    public <S extends User> S save(S user){
        return repository.save(user);
    }

    public void update(long id, User updatedUser) throws UserNotFoundException {
        Optional<User> userOptional = findById(id);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            user.setUsername(updatedUser.getUsername());
            user.setName(updatedUser.getName());
            user.setSurname(updatedUser.getSurname());
            if (!user.getPassword().equals(updatedUser.getPassword())){
                user.setPassword(bCryptPasswordEncoder.encode(updatedUser.getPassword()));
            }
            user.setEmail(updatedUser.getEmail());
            user.setBirthDate(updatedUser.getBirthDate());
            user.setDeleted(updatedUser.isDeleted());
            user.setRuolo(updatedUser.getRuolo());
            user.setPrenotazioneList(updatedUser.getPrenotazioneList());
        }
        else {
            throw new UserNotFoundException();
        }
    }

    public <S extends User> Iterable<S> saveAll(Iterable<S> users){
        return repository.saveAll(users);
    }

    public Optional<User> findById(Long id){
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
