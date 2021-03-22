package com.zarconeg.carRentalRestApi.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty( message = "{NotEmpty.Auto.costruttore.validation}" )
    private String costruttore;

    @NotEmpty( message = "{NotEmpty.Auto.modello.validation}" )
    private String modello;

    @Past( message = "{Past.Auto.immatricolazione.validation}" )
    private Date immatricolazione;

    private String targa;

    private String tipologia;

    @OneToMany(mappedBy = "auto")
    private List<Prenotazione> prenotazioneList;
}
