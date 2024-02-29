package br.com.reserva.reservasystem.dto;

import br.com.reserva.reservasystem.enums.Status;
import br.com.reserva.reservasystem.model.Reservation;
import br.com.reserva.reservasystem.model.User;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record ReservationListDTO(Long reservationId,
                                 User user,
                                 LocalTime startTime,
                                 LocalTime endTime,
                                 DayOfWeek day,
                                 LocalDate date,
                                 Status status,
                                 LocalDateTime dateTimeRequest) {
    public ReservationListDTO(Reservation reservation){
        this(reservation.getId(),reservation.getUser(), reservation.getStartTime(), reservation.getEndTime(), reservation.getDay(), reservation.getDate(), reservation.getStatus(),reservation.getDateTimeRequest());
    }
}
