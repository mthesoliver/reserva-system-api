package br.com.reserva.reservasystem.repository;

import br.com.reserva.reservasystem.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByClientId(String clientId);

}
