package com.zarconeg.carRentalRestApi.faker;

import com.github.javafaker.Faker;
import com.zarconeg.carRentalRestApi.domain.Auto;
import com.zarconeg.carRentalRestApi.domain.User;
import com.zarconeg.carRentalRestApi.service.AutoService;
import com.zarconeg.carRentalRestApi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Locale;

@Component
public class FakeDataDbSeeder implements ApplicationRunner {

    @Autowired
    private Environment environment;

    private static final String PROD_PROFILE_NAME = "prod";

    private static final Logger LOG = LoggerFactory.getLogger(FakeDataDbSeeder.class);

    @Autowired private UserService userService;
    @Autowired private AutoService autoService;

    // -----------------------------------------------------------------------------------------------------------------


    @Override
    public void run(ApplicationArguments args) throws Exception {
        String[] profiliAttivi = environment.getActiveProfiles();
        boolean isProduction = Arrays.stream(profiliAttivi).anyMatch(PROD_PROFILE_NAME::equals);  // anyMatch al posto di List.contais perchè il secondo non funziona con i primitivi
        if(!isProduction){
            generateFakeData();
        }
    }

    private void generateFakeData() {
        LOG.warn("Genero fake data");

        Faker faker = new Faker(new Locale("it"));

        generaUsers(faker, 200);
        generaAuto(faker, 50);
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
            user.setUsername(faker.name().username());
            user.setDeleted(faker.bool().bool());
            userService.save(user);
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
        }
    }
}
