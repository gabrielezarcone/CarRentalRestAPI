package com.zarconeg.carRentalRestApi.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class MessageConfig {

    // https://www.baeldung.com/spring-custom-validation-message-source

    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean getValidator(){
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    // Seleziona dinamicamente il locale per l'internalizzazione
    @Bean
    public LocaleResolver localResolver(){
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        //sessionLocaleResolver.setDefaultLocale(new Locale("it"));
        sessionLocaleResolver.setDefaultLocale(LocaleContextHolder.getLocale());
        return sessionLocaleResolver;
    }
}
