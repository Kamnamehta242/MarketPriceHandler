package com.santandar.market.dynamodb.repositories;

import com.santandar.market.dynamodb.entities.Instrument;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
@EnableScan
public interface InstrumentRepository extends CrudRepository<Instrument, String> {

    Optional<Instrument> findById(String id);
}