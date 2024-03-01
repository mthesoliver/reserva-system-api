package br.com.reserva.reservasystem.controller;

import br.com.reserva.reservasystem.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class HomeController {

    @GetMapping
    public ResponseEntity<String> getHome(){
    return ResponseEntity.ok("Reserva.com");
    }

}
