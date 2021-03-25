package com.zarconeg.carRentalRestApi.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestCustomException extends Exception{

    protected String errorMessage = "Si è verificato un errore";

    public RestCustomException(){
        super();
    }

    public RestCustomException(String errorMessage){
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
