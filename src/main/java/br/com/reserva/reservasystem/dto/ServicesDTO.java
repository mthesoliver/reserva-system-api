package br.com.reserva.reservasystem.dto;

import br.com.reserva.reservasystem.enums.DayOfWeek;
import br.com.reserva.reservasystem.model.Services;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public record ServicesDTO(
        Long serviceId,
        String serviceName,
        List<DayOfWeek> availableDays,
        LocalTime startTime,
        LocalTime endTime,
        List<ReservationListDTO> reservations
) {
    public ServicesDTO(Services services){
        this(services.getId(),services.getServiceName(),services.getAvailableDays(),services.getStartTime(),services.getEndTime(),services.getReservations().stream().map(ReservationListDTO::new).collect(Collectors.toList()));
    }
}
