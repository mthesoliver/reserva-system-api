package br.com.reserva.reservasystem.dto;

import br.com.reserva.reservasystem.enums.UserRole;
import br.com.reserva.reservasystem.model.User;

public record UserDTO(
        Long id,
        String name,
        String email,
        String phone,
        UserRole role
) {
    public UserDTO(User user){
        this(user.getId(), user.getName(), user.getEmail(), user.getPhone(), user.getRole());
    }

}
