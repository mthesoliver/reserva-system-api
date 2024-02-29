package br.com.reserva.reservasystem.dto;

import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationDTO(
        @NotNull
        LocalTime startTime,
        @NotNull
        LocalTime endTime,
        @NotNull
        DayOfWeek day,
        @NotNull
        LocalDate date,
        @NotNull
        UserDTO user
) {
}
