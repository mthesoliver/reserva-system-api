package br.com.reserva.reservasystem.dto;

import br.com.reserva.reservasystem.enums.DayOfWeek;
import br.com.reserva.reservasystem.model.Services;

import java.time.LocalTime;
import java.util.List;

public record ServiceGetListDTO(Long serviceId,
                                String serviceName,
                                List<DayOfWeek> availableDays,
                                LocalTime startTime,
                                LocalTime endTime) {
    public ServiceGetListDTO(Services services){
        this(services.getId(),services.getServiceName(),services.getAvailableDays(),services.getStartTime(),services.getEndTime());
    }
}
