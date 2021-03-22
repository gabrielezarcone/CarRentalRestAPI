package com.zarconeg.carRentalRestApi.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.util.Date;


@Entity
@Data
public class Prenotazione {

    // -----------------------
    public enum Stato{
        RIFIUTATO,
        PENDING,
        APPROVATO
    }
    // -----------------------

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @FutureOrPresent( message = "{FutureOrPresent.Prenotazione.inizio.validation}" )
    @NotEmpty( message = "{NotEmpty.Prenotazione.inizio.validation}" )
    private Date inizio;

    @FutureOrPresent( message = "{FutureOrPresent.Prenotazione.fine.validation}" )
    @NotEmpty( message = "{NotEmpty.Prenotazione.fine.validation}" )
    private Date fine;

    private Stato stato = Stato.PENDING;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "auto_id", nullable = false)
    private Auto auto;

}
