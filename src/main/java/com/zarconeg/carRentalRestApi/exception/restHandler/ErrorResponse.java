package com.zarconeg.carRentalRestApi.exception.restHandler;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@EqualsAndHashCode
@RequiredArgsConstructor
@Getter
public class ErrorResponse {

    @Setter
    private Date data = new Date();
    @Setter
    private String message;
    private int code;
    private HttpStatus status;

    public void setStatus(HttpStatus status) {
        this.status = status;
        this.code = status.value();
    }
}
