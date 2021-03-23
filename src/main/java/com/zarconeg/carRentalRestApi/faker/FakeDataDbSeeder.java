package com.zarconeg.carRentalRestApi.faker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
public class FakeDataDbSeeder {

    @Autowired
    private Environment environment;

    private static final String PROD_PROFILE_NAME = "prod";

    private static final Logger LOG = LoggerFactory.getLogger(FakeDataDbSeeder.class);


    @PostConstruct
    public void seeder(){
        String[] profiliAttivi = environment.getActiveProfiles();
        boolean isProduction = Arrays.stream(profiliAttivi).anyMatch(PROD_PROFILE_NAME::equals);  // anyMatch al posto di List.contais perchè il secondo non funziona con i primitivi
        if(!isProduction){
            generateFakeData();
        }
    }

    private void generateFakeData() {
        LOG.warn("genera fake data");
    }
}
