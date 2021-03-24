package com.zarconeg.carRentalRestApi.exception.restHandler;

import com.zarconeg.carRentalRestApi.exception.UserIntegrityException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class RestExeptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserIntegrityException.class)
    public final ResponseEntity<ErrorResponse> userIntegrity(Exception ex){
        ErrorResponse error = new ErrorResponse();
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
    }

}
