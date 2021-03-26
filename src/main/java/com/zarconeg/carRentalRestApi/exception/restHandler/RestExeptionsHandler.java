package com.zarconeg.carRentalRestApi.exception.restHandler;

import com.zarconeg.carRentalRestApi.exception.auto.AutoIntegrityException;
import com.zarconeg.carRentalRestApi.exception.auto.AutoNotFoundException;
import com.zarconeg.carRentalRestApi.exception.ruolo.RuoloIntegrityException;
import com.zarconeg.carRentalRestApi.exception.ruolo.RuoloNotFoundException;
import com.zarconeg.carRentalRestApi.exception.user.UserIntegrityException;
import com.zarconeg.carRentalRestApi.exception.user.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public final Logger LOG = LoggerFactory.getLogger(RestExeptionsHandler.class);

    // User --------------------------------------------------------------------------------------

    @ExceptionHandler(UserIntegrityException.class)
    public final ResponseEntity<ErrorResponse> userIntegrity(Exception ex){
        ErrorResponse error = new ErrorResponse();
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST);
        // ----------------------------------------
        LOG.error(error.getMessage());
        // ----------------------------------------
        return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorResponse> userNotFound(Exception ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
        errorResponse.setMessage(ex.getMessage());
        // ----------------------------------------
        LOG.error(errorResponse.getMessage());
        // ----------------------------------------
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    // Auto --------------------------------------------------------------------------------------

    @ExceptionHandler(AutoNotFoundException.class)
    public final ResponseEntity<ErrorResponse> autoNotFound(Exception ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
        errorResponse.setMessage(ex.getMessage());
        // ----------------------------------------
        LOG.error(errorResponse.getMessage());
        // ----------------------------------------
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    @ExceptionHandler(AutoIntegrityException.class)
    public final ResponseEntity<ErrorResponse> autoIntegrity(Exception ex){
        ErrorResponse error = new ErrorResponse();
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST);
        // ----------------------------------------
        LOG.error(error.getMessage());
        // ----------------------------------------
        return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
    }

    // Ruolo --------------------------------------------------------------------------------------

    @ExceptionHandler(RuoloNotFoundException.class)
    public final ResponseEntity<ErrorResponse> ruoloNotFound(Exception ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
        errorResponse.setMessage(ex.getMessage());
        // ----------------------------------------
        LOG.error(errorResponse.getMessage());
        // ----------------------------------------
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    @ExceptionHandler(RuoloIntegrityException.class)
    public final ResponseEntity<ErrorResponse> ruoloIntegrity(Exception ex){
        ErrorResponse error = new ErrorResponse();
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST);
        // ----------------------------------------
        LOG.error(error.getMessage());
        // ----------------------------------------
        return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
    }
}
