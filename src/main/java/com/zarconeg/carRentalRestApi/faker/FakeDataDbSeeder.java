package com.zarconeg.carRentalRestApi.faker;

import com.github.javafaker.Faker;
import com.zarconeg.carRentalRestApi.domain.Auto;
import com.zarconeg.carRentalRestApi.domain.Prenotazione;
import com.zarconeg.carRentalRestApi.domain.Ruolo;
import com.zarconeg.carRentalRestApi.domain.User;
import com.zarconeg.carRentalRestApi.service.AutoService;
import com.zarconeg.carRentalRestApi.service.PrenotazioneService;
import com.zarconeg.carRentalRestApi.service.RuoloService;
import com.zarconeg.carRentalRestApi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class FakeDataDbSeeder implements ApplicationRunner {

    @Autowired
    private Environment environment;

    private static final String PROD_PROFILE_NAME = "prod";

    private static final Logger LOG = LoggerFactory.getLogger(FakeDataDbSeeder.class);

    @Autowired private UserService userService;
    @Autowired private AutoService autoService;
    @Autowired private RuoloService ruoloService;
    @Autowired private PrenotazioneService prenotazioneService;

    private final HashMap<Integer, User> users = new HashMap<>();
    private final HashMap<Integer, Auto> cars = new HashMap<>();
    private final HashMap<Integer, Ruolo> roles = new HashMap<>();

    // -----------------------------------------------------------------------------------------------------------------


    @Override
    public void run(ApplicationArguments args) throws Exception {
        String[] profiliAttivi = environment.getActiveProfiles();
        boolean isProduction = Arrays.stream(profiliAttivi).anyMatch(PROD_PROFILE_NAME::equals);  // anyMatch al posto di List.contais perchè il secondo non funziona con i primitivi
        if(isProduction){
            generateFakeData();
        }
    }

    private void generateFakeData() {
        LOG.warn("Genero fake data");

        Faker faker = new Faker(new Locale("it"));

        generaRuoli();
        generaUsers(faker, 200);
        generaAuto(faker, 50);
        generaPrenotazioni(faker, 300);
    }

    // -----------------------------------------------------------------------------------------------------------------

    private void generaUsers(Faker faker, int number){
        for (int i=0; i<number; i++){
            User user = new User();
            user.setName(faker.name().firstName());
            user.setSurname(faker.name().lastName());
            user.setEmail(faker.internet().emailAddress());
            user.setPassword(faker.internet().password());
            user.setBirthDate(faker.date().birthday());
            user.setUsername(faker.name().username()+faker.numerify("##"));
            user.setDeleted(faker.bool().bool());
            user.setRuolo(this.roles.get(faker.number().numberBetween(0, this.roles.size())));
            userService.save(user);
            this.users.put(i, user);
        }
    }

    private void generaAuto(Faker faker, int number){
        for (int i=0; i<number; i++){
            Auto auto = new Auto();
            auto.setCostruttore(faker.superhero().prefix());
            auto.setModello(faker.superhero().power());
            auto.setImmatricolazione(faker.date().birthday());
            auto.setTarga(faker.idNumber().valid());
            auto.setTipologia(faker.superhero().descriptor());
            autoService.save(auto);
            this.cars.put(i, auto);
        }
    }

    private void generaRuoli(){
        Ruolo admin = new Ruolo();
        admin.setRuolo("ROLE_ADMIN");
        Ruolo customer = new Ruolo();
        customer.setRuolo("ROLE_CUSTOMER");
        ruoloService.save(admin);
        ruoloService.save(customer);
        this.roles.put(0, admin);
        this.roles.put(1, customer);
    }

    private void generaPrenotazioni(Faker faker, int number){
        for (int i=0; i<number; i++){
            // --------------------------------------------------------------------
            Date inizio = faker.date().future(100, 2, TimeUnit.DAYS);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(inizio);
            calendar.add(Calendar.DATE, faker.number().numberBetween(0,30));
            Date fine = calendar.getTime();
            // --------------------------------------------------------------------
            Prenotazione prenotazione = new Prenotazione();
            prenotazione.setInizio(inizio);
            prenotazione.setFine(fine);
            List<Prenotazione.Stato> stati = new ArrayList<>();
            // --------------------------------------------------------------------
            stati.add(Prenotazione.Stato.PENDING);
            stati.add(Prenotazione.Stato.APPROVATO);
            stati.add(Prenotazione.Stato.RIFIUTATO);
            prenotazione.setStato(faker.options().nextElement(stati));
            // --------------------------------------------------------------------
            prenotazione.setAuto(this.cars.get(faker.number().numberBetween(0, this.cars.size())));
            prenotazione.setUser(this.users.get(faker.number().numberBetween(0, this.users.size())));
            // --------------------------------------------------------------------
            prenotazioneService.save(prenotazione);
        }
    }
}
