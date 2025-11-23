package org.example.transactionservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.transactionservice.entities.BankTransaction;

public interface BankTransactionRepository extends JpaRepository<BankTransaction, Long> {}