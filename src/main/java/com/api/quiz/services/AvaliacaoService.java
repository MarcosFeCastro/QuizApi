package com.api.quiz.services;

import com.api.quiz.models.Avaliacao;
import com.api.quiz.models.Jogador;
import com.api.quiz.models.Partida;
import com.api.quiz.repositories.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoService {
    
    @Autowired
    private AvaliacaoRepository avaliacaoRepository;
    
    public Avaliacao insert(Avaliacao obj, Jogador jogador, Partida partida) {
        obj.setId(null);
        obj.setJogador(jogador);
        obj.setPartida(partida);
        return avaliacaoRepository.save(obj);
    }
    
}