package br.com.reserva.reservasystem.repository;

import br.com.reserva.reservasystem.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Services, Long> {
}
