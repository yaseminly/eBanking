package org.example.transactionservice.clients;

import org.example.transactionservice.dto.AccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "compte-service") // nom du service enregistré dans Eureka
public interface AccountRestClient {

    // Ajuste les chemins si ton compte-service utilise "/comptes" au lieu de "/accounts"
    @GetMapping("/accounts/{id}")
    AccountDto getAccountById(@PathVariable("id") Long id);

    @PutMapping("/accounts/{id}/debit")
    void debit(@PathVariable("id") Long id, @RequestParam("amount") double amount);

    @PutMapping("/accounts/{id}/credit")
    void credit(@PathVariable("id") Long id, @RequestParam("amount") double amount);
}
