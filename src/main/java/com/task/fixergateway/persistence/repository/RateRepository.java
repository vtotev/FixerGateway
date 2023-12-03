package com.task.fixergateway.persistence.repository;

import com.task.fixergateway.persistence.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface RateRepository extends JpaRepository<Rate, Short> {
    Optional<Rate> findTopByCurrencyOrderByTimestampDesc(String currency);

    List<Rate> findAllByCurrencyAndTimestampAfterOrderByTimestampDesc(String currency, Timestamp timestamp);
}
