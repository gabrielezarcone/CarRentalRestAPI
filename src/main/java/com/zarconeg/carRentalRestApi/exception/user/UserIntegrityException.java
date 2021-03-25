package com.zarconeg.carRentalRestApi.exception.user;

import com.zarconeg.carRentalRestApi.exception.RestCustomException;

public class UserIntegrityException extends RestCustomException {

    public UserIntegrityException(){
        super("Si è verificato un problema di integrità dei dati dell'utente");
    }
    public UserIntegrityException(String message){
        super(message);
    }
}
