package org.example.reportingservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  // yaya
    private String fromCurrency;
    private String toCurrency;
    private BigDecimal rate;
    private LocalDateTime requestedAt;
}
