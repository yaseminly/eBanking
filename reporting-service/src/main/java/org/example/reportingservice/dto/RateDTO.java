package org.example.reportingservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RateDTO {
    private String from;
    private String to;
    private BigDecimal rate;
    private String date;
}
