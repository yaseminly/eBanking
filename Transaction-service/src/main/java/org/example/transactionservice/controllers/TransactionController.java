package org.example.transactionservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.example.transactionservice.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/transfer")
    public String transfer(
            @RequestParam("sourceId") Long sourceId,
            @RequestParam("destinationId") Long destinationId,
            @RequestParam("amount") double amount
    ) {
        transactionService.transfer(sourceId, destinationId, amount);
        return "Transfer successful";
    }
}
