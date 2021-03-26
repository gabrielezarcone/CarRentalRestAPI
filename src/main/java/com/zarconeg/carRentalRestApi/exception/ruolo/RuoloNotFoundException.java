package com.zarconeg.carRentalRestApi.exception.ruolo;

import com.zarconeg.carRentalRestApi.exception.RestCustomException;

public class RuoloNotFoundException extends RestCustomException {

    public RuoloNotFoundException(){
        super("Ruolo non trovato");
    }

    public RuoloNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
