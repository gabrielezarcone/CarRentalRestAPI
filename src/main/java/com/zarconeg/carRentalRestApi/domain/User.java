package com.zarconeg.carRentalRestApi.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String surname;

    private Date birthDate;

    private String email;

    private String username;

    private String password;

    private boolean deleted;
}
