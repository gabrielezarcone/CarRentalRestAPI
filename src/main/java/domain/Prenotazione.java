package domain;

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


}
