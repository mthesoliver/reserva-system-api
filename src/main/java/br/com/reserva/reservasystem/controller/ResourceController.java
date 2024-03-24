package br.com.reserva.reservasystem.controller;


import br.com.reserva.reservasystem.dto.MessageDTO;
import br.com.reserva.reservasystem.dto.UserDTO;
import br.com.reserva.reservasystem.model.User;
import br.com.reserva.reservasystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    @PreAuthorize("hasAnyAuthority('USER', 'OIDC_USER', 'ADMIN')")
    public ResponseEntity<MessageDTO> user(Authentication authentication){
        return ResponseEntity.ok(new MessageDTO("Hello " + authentication.getName()));
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<MessageDTO> admin(Authentication authentication){
        return ResponseEntity.ok(new MessageDTO("Hello admin " + authentication.getName()));
    }

    @GetMapping("/logged")
    @PreAuthorize("hasAnyAuthority('USER', 'OIDC_USER', 'ADMIN')")
    public ResponseEntity<MessageDTO> logged(Authentication authentication){
        Optional<User> loggedUser = this.userRepository.findByEmail(authentication.getName());
        return ResponseEntity.ok(new MessageDTO(authentication.getAuthorities().toString()));
    }

    @GetMapping("/currentUser")
    @PreAuthorize("hasAnyAuthority('USER', 'OIDC_USER', 'ADMIN')")
    public ResponseEntity<UserDTO> currentUser(Authentication authentication){
        Optional<UserDTO> loggedUser = this.userRepository.findByEmail(authentication.getName()).stream().map(UserDTO::new).findFirst();
        return ResponseEntity.ok().body(loggedUser.get());
    }
}
