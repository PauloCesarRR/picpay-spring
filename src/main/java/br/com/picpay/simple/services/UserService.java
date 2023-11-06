package br.com.picpay.simple.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.picpay.simple.domain.user.User;
import br.com.picpay.simple.domain.user.UserType;
import br.com.picpay.simple.repositories.UserRepository;

import java.math.BigDecimal;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getUserType() == UserType.MERCHANT){
            throw new Exception("Usuário do tipo lojista não está autorizado a realizar transação");
        }

        if(sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Saldo insuficiente");
        }
    }

    public User findUserById(Long id) throws Exception{
        return this.repo.findById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public User saveUser(User user){
        return this.repo.save(user);
    }

}
