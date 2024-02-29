package br.com.reserva.reservasystem.model;

import br.com.reserva.reservasystem.dto.ReservationDTO;
import br.com.reserva.reservasystem.dto.ReservationUpdateDTO;
import br.com.reserva.reservasystem.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "reservas")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "horario_inicio")
    private LocalTime startTime;
    @Column(name = "horario_final")
    private LocalTime endTime;
    @Enumerated(EnumType.STRING)
    @Column(name = "dia")
    private DayOfWeek day;
    @Column(name = "data")
    private LocalDate date;
    @Column(name = "data_da_solicitacao")
    private LocalDateTime dateTimeRequest;
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Services service;

    public Reservation(ReservationDTO dto){
        this.date = dto.date();
        this.day = dto.day();
        this.startTime = dto.startTime();
        this.endTime = dto.endTime();
        this.user = new User(dto.user());
        this.dateTimeRequest = LocalDateTime.now();
        this.status = Status.AGUARDANDO_APROVACAO;
    }

    public Reservation(ReservationDTO dto, Services service) {
        this.startTime = dto.startTime();
        this.endTime = dto.endTime();
        this.day = dto.day();
        this.date = dto.date();
        this.user = new User(dto.user());
        this.service = service;
        this.dateTimeRequest = LocalDateTime.now();
        this.status = Status.AGUARDANDO_APROVACAO;
    }



    public void updateReservationStatus(ReservationUpdateDTO dto) {
        this.status = dto.status();
        this.dateTimeRequest = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", Inicio =" + startTime +
                ", Final =" + endTime +
                ", Dia =" + day +
                ", Data =" + date +
                ", Data da solicitação =" + dateTimeRequest +
                ", Status =" + status +
                ", Usuário =" + user +
                ", Serviço =" + service;
    }
}
