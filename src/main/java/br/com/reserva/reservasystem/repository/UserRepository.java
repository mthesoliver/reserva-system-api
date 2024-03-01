package br.com.reserva.reservasystem.repository;

import br.com.reserva.reservasystem.enums.UserRole;
import br.com.reserva.reservasystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByPhoneOrEmail(String telefone, String email);

    Optional<User> findByName(String idOrName);

    List<User> findByRole(UserRole role);

    Optional<User> findByEmail(String email);
}
