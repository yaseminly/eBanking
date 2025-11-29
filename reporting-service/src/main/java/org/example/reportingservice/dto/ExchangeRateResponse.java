package org.example.reportingservice.dto;

import java.util.Map;

public class ExchangeRateResponse {
    private boolean success;
    private String base;
    private String date;
    private Map<String, Double> rates;
}
