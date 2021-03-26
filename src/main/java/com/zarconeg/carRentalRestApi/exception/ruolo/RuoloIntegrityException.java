package com.zarconeg.carRentalRestApi.exception.ruolo;

import com.zarconeg.carRentalRestApi.exception.RestCustomException;

public class RuoloIntegrityException extends RestCustomException {
    public RuoloIntegrityException() {
        super("Si è verificato un problema di integrità dei dati sulla tabella Ruolo");
    }
    public RuoloIntegrityException(String message) {
        super(message);
    }
}
