package br.com.reserva.reservasystem.repository;

import br.com.reserva.reservasystem.enums.Status;
import br.com.reserva.reservasystem.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByStatus(Status status);
    boolean existsByStatus(Status status);

}
