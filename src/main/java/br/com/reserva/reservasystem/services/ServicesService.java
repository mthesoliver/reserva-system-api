package br.com.reserva.reservasystem.services;

import br.com.reserva.reservasystem.dto.ServiceGetListDTO;
import br.com.reserva.reservasystem.dto.ServiceRegistrationDTO;
import br.com.reserva.reservasystem.dto.ServiceUpdateDTO;
import br.com.reserva.reservasystem.dto.ServicesDTO;
import br.com.reserva.reservasystem.enums.UserRole;
import br.com.reserva.reservasystem.exception.ServicesException;
import br.com.reserva.reservasystem.model.Services;
import br.com.reserva.reservasystem.model.User;
import br.com.reserva.reservasystem.repository.ServiceRepository;
import br.com.reserva.reservasystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServicesService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private UserRepository userRepository;


    public List<ServiceGetListDTO> listAllServices() {
        return serviceRepository.findAll().stream()
                .map(ServiceGetListDTO::new)
                .collect(Collectors.toList());
    }

    public List<ServicesDTO> getServicesById(Long id) {
        List<ServicesDTO> listService;
        listService = serviceRepository.findById(id).stream().map(ServicesDTO::new).collect(Collectors.toList());

        return listService;
    }

    public void registerServices(ServiceRegistrationDTO dto){
        User user = userRepository.getReferenceById(dto.userId());

        if(user.getRole() == UserRole.ADMIN){
            Services services = new Services(user, dto);
            serviceRepository.save(services);
        }else {
            throw new ServicesException("Você não tem permissão para fazer isso");
        }
    }

    public void updateServiceById(ServiceUpdateDTO dto) {
        User user = userRepository.getReferenceById(dto.userId());
        Services service = serviceRepository.getReferenceById(dto.id());

        if(user.getRole() == UserRole.ADMIN) {
            service.updateService(dto);
        }else{
            throw new ServicesException("Você não tem permissão para fazer isso");
        }
    }

    public Services deleteServiceById(Long id) {
        Optional<Services> optionalService;
        optionalService = serviceRepository.findById(id);
        if(optionalService.isPresent()){
            serviceRepository.deleteById(id);
        }

        return optionalService.orElseThrow(() -> new ServicesException("Serviço não encontrado"));
    }
}
