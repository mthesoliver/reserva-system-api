package br.com.reserva.reservasystem.controller;


import br.com.reserva.reservasystem.dto.MessageDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {

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

}
