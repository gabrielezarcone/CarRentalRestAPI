package com.zarconeg.carRentalRestApi.exception.auto;

import com.zarconeg.carRentalRestApi.exception.RestCustomException;

public class AutoIntegrityException extends RestCustomException {
    public AutoIntegrityException(){
        super("Errore di integrità dei dati della tabella Auto");
    }
    public AutoIntegrityException(String message) {
        super(message);
    }
}
