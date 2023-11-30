package com.task.fixergateway.persistence.repository;

import com.task.fixergateway.persistence.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface RateRepository extends JpaRepository<Rate, Short> {

    @Query("select r from Rate r where r.currency = :currency order by r.timestamp desc limit 1")
    Optional<Rate> findLatestRate(String currency);

    @Query("select r from Rate r where r.currency = :currency and r.timestamp >= :startTime order by r.timestamp desc")
    Stream<Rate> findAllByCurrencyOrderByTimestampDesc(String currency, Timestamp startTime);
}
