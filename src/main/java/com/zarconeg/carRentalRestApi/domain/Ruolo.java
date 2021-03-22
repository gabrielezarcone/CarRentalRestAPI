package com.zarconeg.carRentalRestApi.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Ruolo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String ruolo;

    @OneToMany(mappedBy = "ruolo")
    private List<User> users;
}
