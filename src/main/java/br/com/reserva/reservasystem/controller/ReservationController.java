package br.com.reserva.reservasystem.controller;

import br.com.reserva.reservasystem.dto.ReservationDTO;
import br.com.reserva.reservasystem.dto.ReservationListDTO;
import br.com.reserva.reservasystem.dto.ReservationUpdateDTO;
import br.com.reserva.reservasystem.exception.ReservationException;
import br.com.reserva.reservasystem.exception.ServicesException;
import br.com.reserva.reservasystem.model.Reservation;
import br.com.reserva.reservasystem.model.Services;
import br.com.reserva.reservasystem.repository.ServiceRepository;
import br.com.reserva.reservasystem.services.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ServiceRepository serviceRepository;


    @GetMapping("services/{id}/reservations")
    public ResponseEntity<List<Reservation>> listReservationsById(@PathVariable Long id){
        try{
            List<Reservation> reservations = reservationService.getReservationsById(id);
            return ResponseEntity.ok(reservations);
        }catch (ReservationException exception){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("services/{id}/reservations/{status}")
    public ResponseEntity<List<ReservationListDTO>> findByStatus(@PathVariable Long id, @PathVariable String status){
        try{
            List<ReservationListDTO> reservations = reservationService.listReservationByStatus(status);
            return ResponseEntity.ok(reservations);
        }catch (ReservationException exception){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("services/{id}/reservations")
    @Transactional
    public ResponseEntity<String> registerReservation(@PathVariable Long id, @RequestBody @Valid ReservationDTO dto){
        try{
            reservationService.registerReservation(dto, id);
            return ResponseEntity.ok().build();
        }catch (ReservationException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PutMapping("services/{id}/reservations/{reservationId}")
    @Transactional
    public ResponseEntity updateReservation(@PathVariable Long id, @PathVariable Long reservationId, @RequestBody @Valid ReservationUpdateDTO dto){
        try{
            reservationService.updateReservation(dto, id, reservationId);
            return ResponseEntity.ok().build();
        }catch (ReservationException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @DeleteMapping("services/{id}/reservations/{reservationId}")
    public ResponseEntity deleteReservation(@PathVariable Long id, @PathVariable Long reservationId){
        try{
            Reservation reservation = reservationService.deleteReservationById(id, reservationId);
            return ResponseEntity.ok().build();
        }catch (ReservationException exception){
            return ResponseEntity.notFound().build();
        }
    }
}
