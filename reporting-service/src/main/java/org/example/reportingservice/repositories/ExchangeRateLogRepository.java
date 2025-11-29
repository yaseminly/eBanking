package org.example.reportingservice.repositories;

import org.example.reportingservice.entities.ExchangeRateLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRateLogRepository extends JpaRepository<ExchangeRateLog, Long> {}
