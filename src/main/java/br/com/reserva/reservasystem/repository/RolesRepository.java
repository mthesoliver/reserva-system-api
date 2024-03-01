package br.com.reserva.reservasystem.repository;

import br.com.reserva.reservasystem.enums.UserRole;
import br.com.reserva.reservasystem.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByRole(UserRole rolesName);
}
