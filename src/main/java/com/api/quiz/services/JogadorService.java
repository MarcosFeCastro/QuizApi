package com.api.quiz.services;

import com.api.quiz.models.Jogador;
import com.api.quiz.models.Partida;
import com.api.quiz.repositories.JogadorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class JogadorService {
    
    @Autowired
    private JogadorRepository jogadorRepository;
    
    @Autowired
    private PartidaService partidaService;
    
    public List<Jogador> findAll(Partida partida_id){
        return jogadorRepository.findAllByPartida(partida_id);
    }
    
    public Jogador find(Partida partida_id){
        return jogadorRepository.findByPartida(partida_id);
    }
    
    public Jogador findById(Long id){
        return jogadorRepository.findById(id).orElse(null);
    }
    
    public Jogador findOne(Long id){
        return jogadorRepository.findOne(id);
    }
    
    public List<Jogador> findByPontuacao(Long id){
        Partida partida = partidaService.find(id);
        Pageable qtdResultados = new PageRequest(0, 5);
        return jogadorRepository.findByPontuacao(partida, qtdResultados);
    }
    
    public Jogador insert(String nome, String token) {
        Partida partida = partidaService.findByToken(token);
        Jogador obj = new Jogador(null, nome, partida);
        return jogadorRepository.save(obj);
    }
    
    public Jogador update(Jogador obj) {
        return jogadorRepository.save(obj);
    }
    
    public void delete(Long id) {
        jogadorRepository.deleteById(id);
    }

}