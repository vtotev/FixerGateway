package com.task.fixergateway.persistence.repository;

import com.task.fixergateway.persistence.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface RateRepository extends JpaRepository<Rate, Short> {
    Optional<Rate> findTopByCurrencyOrderByTimestampDesc(String currency);

    Stream<Rate> findAllByCurrencyAndTimestampAfterOrderByTimestampDesc(String currency, Timestamp timestamp);
}
