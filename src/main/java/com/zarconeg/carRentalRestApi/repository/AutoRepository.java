package com.zarconeg.carRentalRestApi.repository;

import com.zarconeg.carRentalRestApi.domain.Auto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoRepository extends CrudRepository<Auto, Long> {
}
