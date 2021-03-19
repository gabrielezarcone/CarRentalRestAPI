package domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

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
}
