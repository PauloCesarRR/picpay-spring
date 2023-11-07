package br.com.picpay.simple.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.picpay.simple.domain.transaction.Transaction;
import br.com.picpay.simple.dtos.TransactionDTO;
import br.com.picpay.simple.services.TransactionService;

@RestController()
@RequestMapping("/transactions")
public class TransactionController {
    
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/")
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transaction) throws Exception{
        Transaction newTransaction = this.transactionService.createTransaction(transaction);

        return new ResponseEntity<>(newTransaction, HttpStatus.OK);
    }

   
}