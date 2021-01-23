package com.api.quiz.services;

import com.api.quiz.models.Usuario;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    
    void sendNewAccountEmail(Usuario usuario);
    
    void sendEmail(SimpleMailMessage msg);
    
    
    void sendNewAccountHtmlEmail(Usuario usuario);
    
    void sendHtmlEmail(MimeMessage msg);


    void sendNewPasswordHtmlEmail(Usuario usuario, String newPass);
    
}