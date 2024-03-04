package br.com.reserva.reservasystem.controller;

import br.com.reserva.reservasystem.dto.*;
import br.com.reserva.reservasystem.enums.UserRole;
import br.com.reserva.reservasystem.exception.UserRegisterException;
import br.com.reserva.reservasystem.model.User;
import br.com.reserva.reservasystem.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@PreAuthorize("ADMIN")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("ADMIN")
    public ResponseEntity<List<UserDTO>> listAllUsers(){
        List<UserDTO> userList = userService.listAllUsers();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{idOrName}")
    public ResponseEntity<UserByIdDTO> listUserById(@PathVariable String idOrName){
        try{
            UserByIdDTO user = userService.getUserByIdOrName(idOrName);
            return ResponseEntity.ok(user);
        }catch (UserRegisterException exception){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/role-admin")
    public ResponseEntity<List<UserByRoleDTO> > listUserByRoleAdmin(){
        try{
            List<UserByRoleDTO> listAdmin = userService.getUsersByRole(UserRole.ADMIN);
            return ResponseEntity.ok(listAdmin);
        }catch (UserRegisterException exception){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/role-users")
    public ResponseEntity<List<UserByRoleDTO> > listUserByRoleUser(){
        try{
            List<UserByRoleDTO> listUsers = userService.getUsersByRole(UserRole.USER);
            return ResponseEntity.ok(listUsers);
        }catch (UserRegisterException exception){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> registerUser(@RequestBody @Valid UserRegistrationDTO dto){
        try {
            userService.userRegistration(dto);
            return ResponseEntity.ok().build();
        }catch (UserRegisterException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateUser(@RequestBody @Valid UserUpdateDTO dto){
        try{
            userService.updateUserById(dto);
            return ResponseEntity.ok().build();
        }catch (UserRegisterException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable String id){
        try{
            User user = userService.deleteUserByIdOrName(id);
            return ResponseEntity.ok().build();
        }catch (UserRegisterException exception){
            return ResponseEntity.notFound().build();
        }
    }


}
