package com.zarconeg.carRentalRestApi.exception.auto;

public class AutoIntegrityException extends Throwable {
    public AutoIntegrityException(){
        super("Errore di integrità dei dati della tabella Auto");
    }
    public AutoIntegrityException(String message) {
        super(message);
    }
}
