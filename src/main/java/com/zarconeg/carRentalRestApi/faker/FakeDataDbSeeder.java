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

    private static final Logger LOG = LoggerFactory.getLogger(FakeDataDbSeeder.class);

    @PostConstruct
    public void seeder(){
        LOG.warn(Arrays.toString(environment.getActiveProfiles()));
    }
}
