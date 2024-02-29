package br.com.reserva.reservasystem.dto;

import br.com.reserva.reservasystem.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserUpdateDTO(
                            @NotNull
                            Long id,
                            @NotBlank
                            String nome,
                            @Email
                            @NotBlank
                            String email,
                            @NotBlank
                            @Pattern(regexp = "\\(?\\d{2}\\)?\\d?\\d{4}-?\\d{4}")
                            String telefone,
                            UserRole funcao
) {
}