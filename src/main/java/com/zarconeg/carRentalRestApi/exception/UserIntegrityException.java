package com.zarconeg.carRentalRestApi.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserIntegrityException extends Exception{

    private String message = "Si è verificato un problema di integrità dei dati dell'utente";

    public UserIntegrityException(){
        super();
    }
    public UserIntegrityException(String message){
        super(message);
        this.message = message;
    }
}
