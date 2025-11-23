package service;

import entities.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final AccountRestClient accountRestClient;

    public void transfer(Long sourceId, Long destinationId, double amount) {
        Account source = accountRestClient.getAccountById(sourceId);
        if(source.getBalance() < amount){
            throw new RuntimeException("Solde insuffisant !");
        }

        accountRestClient.debit(sourceId, amount);
        accountRestClient.credit(destinationId, amount);
    }
}
