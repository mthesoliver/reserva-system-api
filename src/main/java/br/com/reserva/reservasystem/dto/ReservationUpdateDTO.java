package br.com.reserva.reservasystem.dto;

import br.com.reserva.reservasystem.enums.Status;
import jakarta.validation.constraints.NotNull;

public record ReservationUpdateDTO(
        @NotNull
        Status status) {
}
