package com.zarconeg.carRentalRestApi.domain;

import lombok.Data;

import javax.persistence.*;
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

    private Date inizio;

    private Date fine;

    private Stato stato;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "auto_id", nullable = false)
    private Auto auto;

}
