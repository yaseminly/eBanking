package org.example.transactionservice.service;

import org.example.transactionservice.clients.AccountRestClient;
import org.example.transactionservice.dto.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final AccountRestClient accountRestClient;

    public void transfer(Long sourceId, Long destinationId, double amount) {

        // 1. Vérifier que le montant est positif
        if (amount <= 0) {
            throw new IllegalArgumentException("Le montant doit être supérieur à 0.");
        }

        // 2. Charger le compte source
        AccountDto source = accountRestClient.getAccountById(sourceId);
        if (source == null) {
            throw new RuntimeException("Compte source introuvable !");
        }

        // 3. Charger le compte destination
        AccountDto destination = accountRestClient.getAccountById(destinationId);
        if (destination == null) {
            throw new RuntimeException("Compte destination introuvable !");
        }

        // 4. Vérifier le solde
        if (source.getBalance() < amount) {
            throw new RuntimeException("Solde insuffisant !");
        }

        // 5. Effectuer les opérations
        accountRestClient.debit(sourceId, amount);
        accountRestClient.credit(destinationId, amount);

        // Tu peux ajouter un log si tu veux
        System.out.println("Transfert de " + amount + " effectué de " + sourceId + " vers " + destinationId);
    }
}
