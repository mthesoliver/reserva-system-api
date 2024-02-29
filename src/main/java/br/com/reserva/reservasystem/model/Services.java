package br.com.reserva.reservasystem.model;

import br.com.reserva.reservasystem.dto.ServiceRegistrationDTO;
import br.com.reserva.reservasystem.dto.ServiceUpdateDTO;
import br.com.reserva.reservasystem.enums.DayOfWeek;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "servicos")
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String serviceName;

    @Enumerated(EnumType.STRING)
    @Column(name = "dias_disponiveis")
    private List<DayOfWeek> availableDays;

    @Column(name = "horario_inicio")
    private LocalTime startTime;
    @Column(name = "horario_final")
    private LocalTime endTime;

    @OneToMany(mappedBy = "service", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Reservation> reservations = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Services(User user, ServiceRegistrationDTO dto) {
        this.user = user;
        this.serviceName = dto.nomeServico();
        this.startTime = dto.horarioInicio();
        this.endTime = dto.horarioFinal();
        this.availableDays = dto.diasDisponiveis();
    }

    public Services(String serviceName, LocalTime startTime, LocalTime endTime, User user, List<DayOfWeek> availableDays){
        this.serviceName = serviceName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.user = user;
        this.availableDays = availableDays;
    }

    public Services(ServiceRegistrationDTO dto){
        this.serviceName = dto.nomeServico();
        this.startTime = dto.horarioInicio();
        this.endTime = dto.horarioFinal();
        this.availableDays = dto.diasDisponiveis();
    }

    public Services(Services services) {
    }

    public void updateService(ServiceUpdateDTO dto){
        this.serviceName = dto.nomeServico();
        this.startTime = dto.horarioInicio();
        this.endTime = dto.horarioFinal();
        this.availableDays = dto.diasDisponiveis();
    }

    @Override
    public String toString() {
        return  "id= " + id +
                ", Serviço = '" + serviceName + '\'' +
                ", Inicio = '" + startTime + '\'' +
                ", Final = '" + endTime + '\'' +
                ", Dias disponíveis = '" + availableDays + '\'' +
                ", Serviço do = " + user;
    }
}
