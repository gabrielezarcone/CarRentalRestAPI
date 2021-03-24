package com.zarconeg.carRentalRestApi.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Data
// Tolgo il campo prenotazionList per evitarte la generazione ciclica e ricorsiva dei metodi toString, HashCode e equals
// https://github.com/rzwitserloot/lombok/issues/2255
// https://stackoverflow.com/questions/40266770/spring-jpa-bi-directional-cannot-evaluate-tostring/40267032
@ToString(exclude = "prenotazioneList")
@EqualsAndHashCode(exclude = "prenotazioneList")
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
