package org.example.reportingservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.reportingservice.dto.ExchangeRateResponse;
import org.example.reportingservice.dto.RateDTO;
import org.example.reportingservice.entities.ExchangeRateLog;
import org.example.reportingservice.repositories.ExchangeRateLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportingService {

    private final WebClient webClient;
    private final ExchangeRateLogRepository logRepository;

    public RateDTO getExchangeRate(String from, String to) {

        String url = String.format(
                "https://api.exchangerate.host/latest?base=MAD",
                from, to
        );

        ExchangeRateResponse response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(ExchangeRateResponse.class)
                .block();

        if (response == null || response.getRates() == null) {
            throw new IllegalStateException("Réponse API invalide");
        }

        Double rateValue = response.getRates().get(to);

        if (rateValue == null) {
            throw new IllegalStateException("Taux introuvable dans la réponse API");
        }

        BigDecimal rate = BigDecimal.valueOf(rateValue);

        // DTO
        RateDTO dto = new RateDTO();
        dto.setFrom(from.toUpperCase());
        dto.setTo(to.toUpperCase());
        dto.setRate(rate);
        dto.setDate(response.getDate());

        // Log en base
        ExchangeRateLog logEntry = new ExchangeRateLog();
        logEntry.setFromCurrency(from.toUpperCase());
        logEntry.setToCurrency(to.toUpperCase());
        logEntry.setRate(rate);
        logEntry.setRequestedAt(LocalDateTime.now());

        logRepository.save(logEntry);

        return dto;
    }
}
