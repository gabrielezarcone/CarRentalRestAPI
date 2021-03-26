package com.zarconeg.carRentalRestApi.exception.auto;

import com.zarconeg.carRentalRestApi.exception.RestCustomException;

public class AutoNotFoundException extends RestCustomException {
    public AutoNotFoundException(){
        super("Auto richiesta non trovata");
    }

    public AutoNotFoundException(String message){
        super(message);
    }
}
