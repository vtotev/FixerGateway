package com.task.fixergateway.persistence.repository;

import com.task.fixergateway.persistence.entity.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
    Optional<Statistics> findByRequestId(String requestId);
}
