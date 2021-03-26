package com.zarconeg.carRentalRestApi.exception.prenotazione;

import com.zarconeg.carRentalRestApi.exception.RestCustomException;

public class PrenotazioneIntegrityException extends RestCustomException {

    public PrenotazioneIntegrityException(){
        super("Si è verificato un problema di integrità dei dati della prenotazione");
    }
    public PrenotazioneIntegrityException(String message){
        super(message);
    }
}
