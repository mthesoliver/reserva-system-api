package br.com.reserva.reservasystem.dto;

import br.com.reserva.reservasystem.enums.DayOfWeek;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;
import java.util.List;

public record ServiceRegistrationDTO(@NotBlank
                                     String nomeServico,
                                     @NotNull
                                     List<DayOfWeek> diasDisponiveis,
                                     @NotNull
                                     LocalTime horarioInicio,
                                     @NotNull
                                     LocalTime horarioFinal,
                                     @NotNull
                                     Long userId) {
}
