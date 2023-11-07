package br.com.picpay.simple.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.picpay.simple.domain.transaction.Transaction;
import br.com.picpay.simple.domain.user.User;
import br.com.picpay.simple.dtos.TransactionDTO;
import br.com.picpay.simple.repositories.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Service
public class TransactionService {
    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository repo;

    @Autowired
    private NotificationService notificationService;


    @Autowired
    private RestTemplate restTemplate;

    public Transaction createTransaction(TransactionDTO transaction) throws Exception {
        User sender = this.userService.findUserById(transaction.senderId());
        User receiver = this.userService.findUserById(transaction.receiverId());

        userService.validateTransaction(sender, transaction.value());

        boolean isAuthorized =this.authorizeTransaction(sender, transaction.value());
        if(!isAuthorized){
            throw new Exception("Transação não autorizada");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        repo.save(newTransaction);
        userService.saveUser(sender);
        userService.saveUser(receiver);

        this.notificationService.sendNotification(sender, "Transação realizada com sucesso");
        this.notificationService.sendNotification(receiver, "Transação recebida com sucesso");


        return newTransaction;
    }

    public boolean authorizeTransaction(User sender, BigDecimal amount){
        /*
        ResponseEntity<Map> authResponse = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);

        if(authResponse.getStatusCode() == HttpStatus.OK) {
            return true;
        } else return false;
        */

        return true;
    }
}