package br.com.reserva.reservasystem.services;

import br.com.reserva.reservasystem.dto.ReservationDTO;
import br.com.reserva.reservasystem.dto.ReservationListDTO;
import br.com.reserva.reservasystem.dto.ReservationUpdateDTO;
import br.com.reserva.reservasystem.enums.Status;
import br.com.reserva.reservasystem.exception.ReservationException;
import br.com.reserva.reservasystem.model.Reservation;
import br.com.reserva.reservasystem.model.Services;
import br.com.reserva.reservasystem.repository.ReservationRepository;
import br.com.reserva.reservasystem.repository.ServiceRepository;
import br.com.reserva.reservasystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    private String succesRequestReservation = "Reserva.com - Solicitação de reserva feita com Sucesso";
    private String reservationRequestApprove = "Reserva.com - Sua solicitação de reserva foi confirmada!";
    private String reservationRequestReject = "Reserva.com - Sua solicitação de reserva rejeitada";
    private String reservationRequestAdmin = "Reserva.com - Você tem uma nova solicitação de reserva";

    private String mailMessage;
    private String mailToAdmin;

    public List<Reservation> getReservationsById(Long id) {
        Services services = serviceRepository.getReferenceById(id);
        return services.getReservations().stream().collect(Collectors.toList());
    }

    public void registerReservation(ReservationDTO dto, Long id) {
        Services service = serviceRepository.getReferenceById(id);
        boolean dayIsValid = service.getAvailableDays().stream().anyMatch(d -> d.name().contains(dto.day().name()));
        boolean startTimeIsValid = service.getReservations().stream().anyMatch(s -> s.getStartTime().equals(dto.startTime()));
        boolean endTimeIsValid = service.getReservations().stream().anyMatch(s -> s.getEndTime().equals(dto.endTime()));

        if(serviceRepository.existsById(service.getId()) && dayIsValid && !startTimeIsValid && !endTimeIsValid){
            Reservation reservation = new Reservation(dto,service);

            mailMessage ="Sua solicitação de reserva feito para "  + reservation.getService().getServiceName() + " para o dia " + reservation.getDate() + " das " + reservation.getStartTime() + " até as " + reservation.getEndTime() + " foi feita com sucesso. Agora basta aguardar a aprovaçãço de sua reserva pelo e-mail. Obrigado por usar nossa plataforma!";
            emailService.sendEmail(reservation.getUser().getEmail(), succesRequestReservation, mailMessage);

            mailToAdmin = "Você tem uma nova solicitação de reserva para " + reservation.getService().getServiceName() + " para o dia " + reservation.getDate() + " das " + reservation.getStartTime() + " até as " + reservation.getEndTime() + ". Verifique a solicitação no seu painel e atualize o status da reserva!";
            emailService.sendEmail(service.getUser().getEmail(), reservationRequestAdmin, mailToAdmin);

            reservationRepository.save(reservation);
        }else{
            throw new ReservationException("Serviço indiponível para a data escolhida ou horário selecionado já possui reserva");
        }
    }

    public void updateReservation(ReservationUpdateDTO dto, Long id, Long reservationId) {
        Services service = serviceRepository.getReferenceById(id);
        Reservation reservation = reservationRepository.getReferenceById(reservationId);

        if(serviceRepository.existsById(service.getId()) && reservationRepository.existsById(reservationId) && dto.status() == Status.APROVADO){
            mailMessage = "Sua solicitação de reserva feito para "  + reservation.getService().getServiceName() + " para o dia " + reservation.getDate() + " das " + reservation.getStartTime() + " até as " + reservation.getEndTime() + " foi aprovada.";
            emailService.sendEmail(reservation.getUser().getEmail(), reservationRequestApprove, mailMessage);
            reservation.updateReservationStatus(dto);
        } else if (serviceRepository.existsById(service.getId()) && reservationRepository.existsById(reservationId) && dto.status() == Status.REJEITADO) {
            mailMessage = "Infelizmente sua solicitação de reserva para " + reservation.getService().getServiceName() + " para o dia " + reservation.getDate() + " das " + reservation.getStartTime() + " até as " + reservation.getEndTime() + " foi rejeitada.";
            emailService.sendEmail(reservation.getUser().getEmail(), reservationRequestReject, mailMessage);
            reservation.updateReservationStatus(dto);
        } else {
            throw new ReservationException("Reserva não encontrada");
        }
    }

    public List<ReservationListDTO> listReservationByStatus(String status){
        return reservationRepository.findByStatus(Status.fromString(status)).stream().map(ReservationListDTO::new).collect(Collectors.toList());
    }

    public Reservation deleteReservationById(Long id, Long reservationId) {
        Optional<Services> optionalService = serviceRepository.findById(id);
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);

        if(optionalReservation.isPresent() && optionalService.isPresent()){
            reservationRepository.deleteById(reservationId);
        }

        return optionalReservation.orElseThrow(() -> new ReservationException("Reserva não encontrada"));
    }
}
