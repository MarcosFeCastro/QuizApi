package com.api.quiz.services;

import com.api.quiz.dto.RespostaDTO;
import com.api.quiz.models.Jogador;
import com.api.quiz.models.Partida;
import com.api.quiz.models.Resposta;
import com.api.quiz.repositories.JogadorRepository;
import com.api.quiz.repositories.PartidaRepository;
import com.api.quiz.repositories.RespostaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RespostaService {
    
    @Autowired
    private RespostaRepository respostaRepository;
    
    @Autowired
    private PartidaRepository partidaRepository;
    
    @Autowired
    private JogadorRepository jogadorRepository;
    
    public Resposta insert( RespostaDTO resposta, String token, Long id ) {
        Partida partida = partidaRepository.findByToken(token);
        //boolean certo = ( partida.getQuestaoAtual().getAlternativaCorreta().equals(resposta.getResposta()) ) ? true : false;
        boolean certo = ( partida.getQuestaoAtual().getAc().equals( resposta.getResposta() ) );
        
        int pontos;
        int pontosPerdidos = 0;
        if ( certo == true ) {
            if( resposta.getTempo() <= 5 ) {
                pontosPerdidos = 0;
            } else if( resposta.getTempo() >= 90 ) {
                pontosPerdidos = 90;
            } else {
                pontosPerdidos = resposta.getTempo();
            }
            switch ( partida.getQuestaoAtual().getDificuldade() ) {
                case 3:
                    pontos = ( 200 - pontosPerdidos );
                    break;
                case 2:
                    pontos = ( 150 - pontosPerdidos );
                    break;
                default:
                    pontos = ( 100 - pontosPerdidos );
                    break;
            }
        } else {
            pontos = 0;
        }
        System.out.println(pontos);
        System.out.println(resposta.getTempo());
        System.out.println(pontosPerdidos);
        Jogador jogador = jogadorRepository.findOne( id );
        jogador.setPontuacao( jogador.getPontuacao() + pontos );
        Jogador jogadorObj = jogadorRepository.save(jogador);
        Resposta obj = new Resposta(null, jogadorObj, resposta.getResposta(), certo, resposta.getTempo(), pontos, partida.getQuestaoAtual(), partida);
        return respostaRepository.save(obj);
    }
    
    public List<Resposta> findResponta(String token, Jogador jogador){
        Partida partida = partidaRepository.findByToken(token);
        Optional<List<Resposta>> r = respostaRepository.findQuestaoRespondida( jogador, partida, partida.getQuestaoAtual() );
        return r.orElse(null);
    }
    
    public List<Resposta> findRanking( String token ){
        Partida partida = partidaRepository.findByToken(token);
        Pageable qtdResultados = new PageRequest(0, 5);
        List<Resposta> obj = respostaRepository.findByPontuacaoParcial( partida, partida.getUltimaQuestao(), qtdResultados );
        return obj;
    }
    
    public List<Jogador> findRank( String token ){
        Partida partida = partidaRepository.findByToken(token);
        Pageable qtdResultados = new PageRequest(0, 7);
        List<Jogador> obj = jogadorRepository.findByPontuacao( partida, qtdResultados ); 
        return obj;
    }
    
}