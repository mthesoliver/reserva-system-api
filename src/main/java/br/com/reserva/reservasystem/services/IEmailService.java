package br.com.reserva.reservasystem.services;

public interface IEmailService {

    void sendEmail(String to, String subject, String message);

}
