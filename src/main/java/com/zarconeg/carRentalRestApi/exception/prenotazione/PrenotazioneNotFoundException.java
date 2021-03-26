package com.zarconeg.carRentalRestApi.exception.prenotazione;

import com.zarconeg.carRentalRestApi.exception.RestCustomException;

public class PrenotazioneNotFoundException extends RestCustomException {

    public PrenotazioneNotFoundException(){
        super("Prenotazione non trovata");
    }

    public PrenotazioneNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
