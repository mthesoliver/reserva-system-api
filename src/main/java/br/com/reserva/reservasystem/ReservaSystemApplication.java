package br.com.reserva.reservasystem;

import br.com.reserva.reservasystem.enums.UserRole;
import br.com.reserva.reservasystem.model.Roles;
import br.com.reserva.reservasystem.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.management.relation.Role;

@SpringBootApplication
public class ReservaSystemApplication /*implements CommandLineRunner*/ {

//	@Autowired
//	RolesRepository rolesRepository;

	public static void main(String[] args) {
		SpringApplication.run(ReservaSystemApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		Roles adminRole = Roles.builder()
//				.role(UserRole.ADMIN).build();
//
//		Roles userRole = Roles.builder()
//				.role(UserRole.USER).build();
//
//		rolesRepository.save(adminRole);
//		rolesRepository.save(userRole);
//	}
}
