package com.api.quiz.services;

import com.api.quiz.models.Usuario;
import java.util.Date;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

public abstract class AbstractEmailService implements EmailService {
    
    @Value("${default.sender}")
    private String sender;
    
    @Autowired
    private TemplateEngine templateEngine;
    
    @Autowired
    private JavaMailSender javaMailSender;
    
    @Autowired
    private BCryptPasswordEncoder pe;
    
    @Override
    public void sendNewAccountEmail(Usuario usuario) {
        SimpleMailMessage sm = prepareSimpleMailMessageFromUsuario(usuario);
        sendEmail(sm);
    }
    
    protected SimpleMailMessage prepareSimpleMailMessageFromUsuario(Usuario usuario) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(usuario.getEmail());
        sm.setFrom(sender);
        sm.setSubject("Cadastro: " + usuario.getNome());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(usuario.toString());
        return sm;
    }
    
    protected String htmlFromTemplateUsuario(Usuario usuario, String tipoEmail) {
        Context context = new Context();
        context.setVariable("usuario", usuario);
        return templateEngine.process("email/"+ tipoEmail +"Usuario", context);
    }
    
    @Override
    public void sendNewAccountHtmlEmail(Usuario usuario) {
        try {
            MimeMessage mm = prepareMimeMessageFromUsuario(usuario);
            sendHtmlEmail(mm);
        }
        catch (MessagingException e) {
            sendNewAccountEmail(usuario);
        }
    }
    
    protected MimeMessage prepareMimeMessageFromUsuario(Usuario usuario) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
        mmh.setTo(usuario.getEmail());
        mmh.setFrom(sender);
        mmh.setSubject("Usuário cadastrado: " + usuario.getNome());
        mmh.setSentDate(new Date(System.currentTimeMillis()));
        mmh.setText(htmlFromTemplateUsuario(usuario, "cadastro"), true);
        return mimeMessage;
    }

    @Override
    public void sendNewPasswordHtmlEmail(Usuario usuario, String newPass) {
        try {
            MimeMessage mm = prepareNewPasswordEmail(usuario, newPass);
            sendHtmlEmail(mm);
        }
        catch (MessagingException e) {
            sendNewAccountEmail(usuario);
        }
    }

    protected MimeMessage prepareNewPasswordEmail(Usuario usuario, String newPass) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
        mmh.setTo(usuario.getEmail());
        mmh.setFrom(sender);
        mmh.setSubject("Nova senha para: " + usuario.getNome());
        mmh.setSentDate(new Date(System.currentTimeMillis()));
        usuario.setSenha( newPass );
        mmh.setText(htmlFromTemplateUsuario(usuario, "novaSenha"), true);
        return mimeMessage;
    }
    
}