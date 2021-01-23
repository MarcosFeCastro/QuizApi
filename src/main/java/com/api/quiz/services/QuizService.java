package com.api.quiz.services;

import com.api.quiz.security.UserSS;
import com.api.quiz.dto.QuizDTO;
import com.api.quiz.models.Quiz;
import com.api.quiz.models.Usuario;
import com.api.quiz.services.exceptions.ObjectNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.quiz.repositories.QuizRepository;
import com.api.quiz.services.exceptions.AuthorizationException;
import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

@Service
public class QuizService {
    
    @Autowired
    private QuizRepository quizRepository;
    
    @Autowired
    private UsuarioService usuarioService;
    
    public Quiz find(Long id) {
        Optional<Quiz> obj = quizRepository.findByIdUsuario(id, autenticaUsuario());
        return obj.orElseThrow(() -> new ObjectNotFoundException(
            "Objeto não encontrado! Id: " + id + ", Tipo: " + Quiz.class.getName()));
    }
    
    private Usuario autenticaUsuario(){
        UserSS user = UserService.authenticated();
        if (user == null) {
            throw new AuthorizationException("Acesso negado");
        }
        Usuario usuario = usuarioService.find(user.getId());
        return usuario;
    }
    
    public Page<Quiz> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return quizRepository.findByUsuario(autenticaUsuario(), pageRequest);
    }
    
    public Quiz insert(Quiz obj) {
        obj.setId(null);
        obj.setUsuario(autenticaUsuario());
        Date dataAtual = new Date();
        obj.setDataCriacao(dataAtual);
        return quizRepository.save(obj);
    }
    
    public Quiz update(Quiz obj, Long id) {
        //obj.setId(id);
        //find(obj.getId());
        
        Quiz o = find(id);
        obj.setDataCriacao(o.getDataCriacao());
        obj.setUsuario(autenticaUsuario());
        return quizRepository.save(obj);
    }
    
    public void delete(Long id) {
        find(id);
        quizRepository.deleteById(id);
    }
    
    public Quiz fromDTO(QuizDTO objDto) {
        return new Quiz(objDto.getNome());
    }

}