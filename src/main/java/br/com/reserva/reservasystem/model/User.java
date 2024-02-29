package br.com.reserva.reservasystem.model;

import br.com.reserva.reservasystem.dto.UserDTO;
import br.com.reserva.reservasystem.dto.UserRegistrationDTO;
import br.com.reserva.reservasystem.dto.UserUpdateDTO;
import br.com.reserva.reservasystem.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String name;
    private String email;
    @Setter
    private String phone;
    @Enumerated(EnumType.STRING)
    @Column(name = "funcao")
    @Setter
    private UserRole role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Services> services = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    public User(String name, String email, String phone, UserRole role){
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    public User(UserRegistrationDTO dto) {
        this.name = dto.nome();
        this.email = dto.email();
        this.phone = dto.telefone();
        this.role = dto.funcao();
    }

    public User(UserDTO dto) {
        this.name = dto.name();
        this.phone = dto.phone();
        this.email = dto.email();
        this.role = UserRole.USER;
    }

    public User(String name, String email, String phone) {
    }

    public void userUpdateData(UserUpdateDTO dto){
        this.name = dto.nome();
        this.email = dto.email();
        this.phone = dto.telefone();
        this.role = dto.funcao();
    }

    @Override
    public String toString() {
        return  "id= " + id +
                ", Nome = '" + name + '\'' +
                ", Email = '" + email + '\'' +
                ", Telefone = '" + phone + '\'' +
                ", Função = " + role;
    }

    public void setServices() {
        List<Services> services = new ArrayList<>();
        this.services= services;
    }
}
