package br.com.picpay.simple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.picpay.simple.domain.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
}
