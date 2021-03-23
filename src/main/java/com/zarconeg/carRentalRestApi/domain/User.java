package com.zarconeg.carRentalRestApi.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Size(max = 70, message = "{Size.User.name.validation}")
    private String name;

    @Size(max = 70, message = "{Size.User.surname.validation}")
    private String surname;

    @Past( message = "{Past.User.birthDate.validation}" )
    private Date birthDate;

    @Email( message = "{Email.User.email.validation}" )
    @NotEmpty( message = "{NotEmpty.User.email.validation}" )
    @Column(unique = true)
    private String email;

    @Size(max = 70, message = "{Size.User.username.validation}")
    @NotEmpty( message = "{NotEmpty.User.username.validation}" )
    @Column(unique = true)
    private String username;

    @Size(min = 8, message = "{Size.User.password.validation}")
    @NotEmpty( message = "{NotEmpty.User.password.validation}" )
    private String password;

    private boolean deleted = false;

    @OneToMany(mappedBy = "user")
    private List<Prenotazione> prenotazioneList;

    @ManyToOne
    @JoinColumn(name = "ruolo_id")
    private Ruolo ruolo;
}
