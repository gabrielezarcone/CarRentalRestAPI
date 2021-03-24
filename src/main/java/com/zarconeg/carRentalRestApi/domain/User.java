package com.zarconeg.carRentalRestApi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
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

    // -------------------------------------------------------------------------------------------------------------
    // RELAZIONI
    // -------------------------------------------------------------------------------------------------------------
    @OneToMany(mappedBy = "user")
    @JsonManagedReference(value = "user_prenotazione")
    private List<Prenotazione> prenotazioneList;

    @ManyToOne
    @JoinColumn(name = "ruolo_id")
    @JsonBackReference
    private Ruolo ruolo;
}
