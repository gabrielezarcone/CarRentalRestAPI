package com.zarconeg.carRentalRestApi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
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
    @NotNull( message = "{NotNull.Prenotazione.inizio.validation}" )
    private Date inizio;

    @FutureOrPresent( message = "{FutureOrPresent.Prenotazione.fine.validation}" )
    @NotNull( message = "{NotNull.Prenotazione.fine.validation}" )
    private Date fine;

    private Stato stato = Stato.PENDING;

    // -------------------------------------------------------------------------------------------------------------
    // RELAZIONI
    // -------------------------------------------------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference(value = "user_prenotazione")
    private User user;

    @ManyToOne
    @JoinColumn(name = "auto_id", nullable = false)
    @JsonBackReference(value = "auto_prenotazione")
    private Auto auto;

}
