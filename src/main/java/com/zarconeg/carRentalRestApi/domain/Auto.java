package com.zarconeg.carRentalRestApi.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String costruttore;

    private String modello;

    private Date immatricolazione;

    private String targa;

    private String tipologia;

    @OneToMany(mappedBy = "auto")
    private List<Prenotazione> prenotazioneList;
}
