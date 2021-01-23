package com.api.quiz.services;

import com.api.quiz.models.Partida;
import com.api.quiz.models.Questao;
import com.api.quiz.models.Quiz;
import com.api.quiz.models.Usuario;
import com.api.quiz.repositories.PartidaRepository;
import com.api.quiz.repositories.QuestaoRepository;
import com.api.quiz.security.UserSS;
import com.api.quiz.services.exceptions.AuthorizationException;
import com.api.quiz.services.exceptions.ObjectNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class PartidaService {
    
    @Autowired
    private PartidaRepository partidaRepository;
    
    @Autowired
    private QuestaoRepository questaoRepository;
    
    @Autowired
    private QuestaoService questaoService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private QuizService quizService;
    
    private Random rand = new Random();
    
    private Usuario autenticaUsuario(){
        UserSS user = UserService.authenticated();
        if( user == null ) {
            throw new AuthorizationException("Quiz privado");
        }
        Usuario usuario = usuarioService.find(user.getId());
        return usuario;
    }
    
    private String generateToken() {
        char[] token = new char[6];
        for( int i = 0; i < 6; i++ ) {
            token[i] = (char) (rand.nextInt(10) + 48);
        }
        return new String(token);
    }
    
    public Partida findOne(){
        return partidaRepository.findOne();
    }
    
    public Page<Partida> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return partidaRepository.findAllByUsuario(autenticaUsuario(), pageRequest);
    }
    
    public Partida find(Long id) {
        Optional<Partida> obj = partidaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Partida não encontrada! Id: " + id));
    }
    
    public Partida findByToken(String token) {
        if( token.equals("CLOSED") ) {
            return null;
        } else {
            Partida obj = partidaRepository.findByToken(token);
            if ( obj != null ) {
                return obj;
            } else {
                throw new ObjectNotFoundException("Partida não encontrada! Token: " + token);
            }
        }
    }

    public Partida create(Long quiz_id) {
        Quiz quiz = quizService.find(quiz_id);
        String token = null;
        if( quiz.isPrivado() ) {
            if( quiz.getUsuario().equals(autenticaUsuario()) ) {
                token = generateToken();
            } else {
                throw new AuthorizationException("Quiz privado");
            }
        } else {
            token = generateToken();
        }
        while( partidaRepository.findByToken(token) != null ) {
            token = generateToken();
        }
        Date dataAtual = new Date();
        Partida obj = new Partida(null, token, dataAtual, autenticaUsuario(), quiz);
        return partidaRepository.save(obj);
    }
    
    public Partida firstQuestao(Long partida_id){
        Partida partida = find(partida_id);
        Questao questao = questaoRepository.findFirstQuestao(partida.getQuiz());
        if( questao != null ) {
            partida.setQuestaoAtual(questao);
        } else {
            throw new ObjectNotFoundException("Perguntas não encontradas!");
        }
        partida.setUltimaQuestao(questao);
        Partida obj = partidaRepository.save(partida);
        return obj;
    }
    
    public Partida nextQuestao(Long partida_id){
        Partida partida = find(partida_id);
        if ( partida.getUltimaQuestao() == null ){
            Questao primeira = questaoRepository.findFirstQuestao(partida.getQuiz());
        }
        Questao proximaQuestao = questaoRepository.findByOrdem( partida.getQuiz(), (partida.getUltimaQuestao().getOrdem() + 1) );
        if( proximaQuestao != null ) {
            partida.setQuestaoAtual(proximaQuestao);
        } else {
            return null;
        }
        partida.setId( partida.getId() );
        partida.setUltimaQuestao(proximaQuestao);
        Partida obj = partidaRepository.save(partida);
        return obj;
    }
    
    public void removerQuestao(Long partida_id) {
        Partida obj = find(partida_id);
        obj.setId( partida_id );
        obj.setQuestaoAtual( null );
        partidaRepository.save(obj);
    }
    
    public void end(Long partida_id) {
        Partida obj = find(partida_id);
        if( obj.getUsuario().equals(autenticaUsuario()) ) {
            obj.setId(obj.getId());
            obj.setToken("CLOSED");
        } else {
            throw new AuthorizationException("Quiz privado");
        }
        partidaRepository.save(obj);
    }

}