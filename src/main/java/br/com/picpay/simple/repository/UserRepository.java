package br.com.picpay.simple.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.picpay.simple.domain.user.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByDoccument(String doc);
}
