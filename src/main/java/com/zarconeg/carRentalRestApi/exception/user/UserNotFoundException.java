package com.zarconeg.carRentalRestApi.exception.user;

import com.zarconeg.carRentalRestApi.exception.RestCustomException;

public class UserNotFoundException extends RestCustomException {

    public UserNotFoundException(){
        super();
    }

    public UserNotFoundException(String errorMessage){
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
