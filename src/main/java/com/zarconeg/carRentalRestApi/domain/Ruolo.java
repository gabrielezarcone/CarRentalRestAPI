package com.zarconeg.carRentalRestApi.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
// Tolgo il campo users per evitarte la generazione ciclica e ricorsiva dei metodi toString, HashCode e equals
// https://github.com/rzwitserloot/lombok/issues/2255
// https://stackoverflow.com/questions/40266770/spring-jpa-bi-directional-cannot-evaluate-tostring/40267032
@ToString(exclude = "users")
@EqualsAndHashCode(exclude = "users")
public class Ruolo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String ruolo;

    @OneToMany(mappedBy = "ruolo")
    private List<User> users;
}
