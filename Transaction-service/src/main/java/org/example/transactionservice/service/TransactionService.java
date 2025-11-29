package org.example.transactionservice.service;

import org.example.transactionservice.clients.AccountRestClient;
import org.example.transactionservice.dto.AccountDto;
import org.example.transactionservice.entities.BankTransaction;
import org.example.transactionservice.repositories.BankTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final AccountRestClient accountRestClient;
    private final BankTransactionRepository transactionRepository;

    public void transfer(Long sourceId, Long destinationId, double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Le montant doit être supérieur à 0.");
        }

        AccountDto source = accountRestClient.getAccountById(sourceId);
        if (source == null) throw new RuntimeException("Compte source introuvable !");

        AccountDto destination = accountRestClient.getAccountById(destinationId);
        if (destination == null) throw new RuntimeException("Compte destination introuvable !");

        if (source.getBalance() < amount) throw new RuntimeException("Solde insuffisant !");

        // 1. Effectuer les opérations sur le microservice compte
        accountRestClient.debit(sourceId, amount);
        accountRestClient.credit(destinationId, amount);

        // 2. Enregistrer dans H2
        BankTransaction transaction = new BankTransaction();
        transaction.setSourceAccountId(sourceId);
        transaction.setDestinationAccountId(destinationId);
        transaction.setAmount(amount);

        transactionRepository.save(transaction);

        System.out.println("Transaction sauvegardée !");
    }
}
