package com.api.quiz.services;

import com.api.quiz.security.UserSS;
import com.api.quiz.models.Usuario;
import com.api.quiz.models.enums.Perfil;
import com.api.quiz.repositories.UsuarioRepository;
import com.api.quiz.services.exceptions.AuthorizationException;
import com.api.quiz.services.exceptions.ObjectAlreadyExistsException;
import com.api.quiz.services.exceptions.ObjectNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository userRepository;
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private BCryptPasswordEncoder pe;
    
    public Usuario find(Long id) {
        UserSS user = UserService.authenticated();
        if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
            throw new AuthorizationException("Acesso negado");
        }
        Optional<Usuario> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
            "Usuario não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
    }
    
    public Usuario findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public List<Usuario> findAll() {
        return userRepository.findAll();
    }
    
    public Usuario insert(Usuario obj) {
        if( findByEmail( obj.getEmail() ) != null ) {
            throw new ObjectAlreadyExistsException(
            "Email inválido: " + obj.getEmail());
        }
        obj.setId(null);
        obj.setSenha( pe.encode( obj.getSenha() ) );
        Date dataAtual = new Date();
        obj.setDataCadastro(dataAtual);
        userRepository.save(obj);
        emailService.sendNewAccountHtmlEmail(obj);
        return obj;
    }
    
    public Usuario update(Usuario obj, Long id) {
        obj.setId(id);
        find(obj.getId());
        return userRepository.save(obj);
    }
    
    public void delete(Long id) {
        find(id);
        userRepository.deleteById(id);
    }
    
}