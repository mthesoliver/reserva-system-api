package br.com.reserva.reservasystem.dto;

import br.com.reserva.reservasystem.enums.UserRole;
import br.com.reserva.reservasystem.model.User;

public record UserEmailDTO(
        String email
) {
    public UserEmailDTO(User user){
        this(user.getEmail());
    }
}

