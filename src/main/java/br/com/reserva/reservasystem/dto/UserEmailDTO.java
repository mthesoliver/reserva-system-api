package br.com.reserva.reservasystem.dto;

import br.com.reserva.reservasystem.enums.UserRole;
import br.com.reserva.reservasystem.model.User;

public record UserEmailDTO(
        Long id,
        String email
) {
    public UserEmailDTO(User user){
        this(user.getId(), user.getEmail());
    }
}

