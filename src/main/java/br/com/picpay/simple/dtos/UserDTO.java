package br.com.picpay.simple.dtos;

import java.math.BigDecimal;

import br.com.picpay.simple.domain.user.UserType;

public record UserDTO(String firstName, String lastName, String document, BigDecimal balance, String email, String password, UserType userType) {

}
