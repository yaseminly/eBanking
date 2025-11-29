package org.example.reportingservice.controller;



import lombok.RequiredArgsConstructor;
import org.example.reportingservice.dto.RateDTO;
import org.example.reportingservice.entities.ExchangeRateLog;
import org.example.reportingservice.repositories.ExchangeRateLogRepository;
import org.example.reportingservice.service.ReportingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reporting")
@RequiredArgsConstructor
public class ReportingController {

    private final ReportingService reportingService;
    private final ExchangeRateLogRepository logRepository;

    // 🔹 Endpoint pour obtenir un taux en temps réel
    @GetMapping("/rate")
    public RateDTO getRate(
            @RequestParam String from,
            @RequestParam String to
    ) {
        return reportingService.getExchangeRate(from, to);
    }

    // 🔹 Endpoint pour consulter l'historique
    @GetMapping("/logs")
    public List<ExchangeRateLog> getLogs() {
        return logRepository.findAll();
    }
}

