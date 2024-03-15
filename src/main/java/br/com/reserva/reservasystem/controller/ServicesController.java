package br.com.reserva.reservasystem.controller;

import br.com.reserva.reservasystem.dto.ServiceGetListDTO;
import br.com.reserva.reservasystem.dto.ServiceRegistrationDTO;
import br.com.reserva.reservasystem.dto.ServiceUpdateDTO;
import br.com.reserva.reservasystem.dto.ServicesDTO;
import br.com.reserva.reservasystem.exception.ServicesException;
import br.com.reserva.reservasystem.exception.UserRegisterException;
import br.com.reserva.reservasystem.model.Services;
import br.com.reserva.reservasystem.services.ServicesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServicesController {

    @Autowired
    private ServicesService servicesService;

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<ServiceGetListDTO>> listAllServices(){
        List<ServiceGetListDTO> servicesList = servicesService.listAllServices();
        return ResponseEntity.ok(servicesList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ServicesDTO>> listServicesById(@PathVariable Long id){
        try{
            List<ServicesDTO> services = servicesService.getServicesById(id);
            return ResponseEntity.ok(services);
        }catch (UserRegisterException exception){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> registerService(@RequestBody @Valid ServiceRegistrationDTO dto){
        try{
            servicesService.registerServices(dto);
            return ResponseEntity.ok().build();
        }catch (ServicesException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateService(@RequestBody @Valid ServiceUpdateDTO dto){
        try{
            servicesService.updateServiceById(dto);
            return ResponseEntity.ok().build();
        }catch (ServicesException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteServiceById(@PathVariable Long id){
        try{
            Services services = servicesService.deleteServiceById(id);
            return ResponseEntity.ok().build();
        }catch (ServicesException exception){
            return ResponseEntity.notFound().build();
        }
    }

}
