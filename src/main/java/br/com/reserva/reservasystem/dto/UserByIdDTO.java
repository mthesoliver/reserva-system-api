package br.com.reserva.reservasystem.dto;

import br.com.reserva.reservasystem.enums.UserRole;
import br.com.reserva.reservasystem.model.User;

import java.util.List;
import java.util.stream.Collectors;

public record UserByIdDTO(
        Long id,
        String name,
        String email,
        String phone,
        UserRole role,
        List<ServicesDTO> services
) {

    public UserByIdDTO(User user){
        this(user.getId(), user.getName(), user.getEmail(), user.getPhone(), user.getRole(), user.getServices().stream().map(ServicesDTO::new).collect(Collectors.toList()));
    }
}
