package com.zarconeg.carRentalRestApi.repository;

import com.zarconeg.carRentalRestApi.domain.Prenotazione;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrenotazioneRepository  extends CrudRepository<Prenotazione, Long> {
}
