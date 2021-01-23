package com.api.quiz.controllers;

import com.api.quiz.dto.JogadorDTO;
import com.api.quiz.dto.PartidaDTOJogador;
import com.api.quiz.dto.PartidaInfoDTO;
import com.api.quiz.dto.QuestaoDTO;
import com.api.quiz.dto.QuestaoDTOJogador;
import com.api.quiz.dto.RelatorioDTOList;
import com.api.quiz.models.Jogador;
import com.api.quiz.models.Partida;
import com.api.quiz.models.Questao;
import com.api.quiz.models.Resposta;
import com.api.quiz.services.JogadorService;
import com.api.quiz.services.PartidaService;
import com.api.quiz.services.RespostaService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/partida")
public class PartidaController {
    
    @Autowired
    private PartidaService service;
    
    @Autowired
    private JogadorService jogadorService;
    
    @Autowired
    private RespostaService respostaService;
    
    @PreAuthorize("hasAnyRole('PROFESSOR')")
    @RequestMapping(value="/{quiz_id}", method=RequestMethod.GET)
    public ResponseEntity<PartidaInfoDTO> create(@PathVariable Long quiz_id) {
        Partida obj = service.create(quiz_id);
        PartidaInfoDTO objDto = new PartidaInfoDTO(obj);
        return ResponseEntity.ok().body(objDto);
    }
    
    @PreAuthorize("hasAnyRole('PROFESSOR')")
    @RequestMapping(value="/{token}/jogadores", method=RequestMethod.GET)
    public ResponseEntity<List<JogadorDTO>> jogadores(@PathVariable("token") String token) {
        Partida obj = service.findByToken(token);
        List<Jogador> listObj = jogadorService.findAll(obj);
        List<JogadorDTO> listDto = new ArrayList(); // = list.stream().map(obj -> new QuizDTO(obj)).collect(Collectors.toList());
        for(Jogador j : listObj) { listDto.add(new JogadorDTO(j)); }
        return ResponseEntity.ok().body(listDto);
    }
    
    @PreAuthorize("hasAnyRole('PROFESSOR')")
    @RequestMapping(value="/{token}/jogador/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteJogador(@PathVariable("token") String token, @PathVariable("id") Long id) {
        jogadorService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @PreAuthorize("hasAnyRole('PROFESSOR')")
    @RequestMapping(value="/questao/{id}", method=RequestMethod.GET)
    public ResponseEntity<QuestaoDTO> firstQuestao(@PathVariable Long id) {
        Partida partida = service.firstQuestao( id );
        System.out.println( partida );
        Questao obj = partida.getQuestaoAtual();
        QuestaoDTO objDto = new QuestaoDTO(obj);
        return ResponseEntity.ok().body(objDto);
    }
    
    //Metodo: segir para proxima questao
    @PreAuthorize("hasAnyRole('PROFESSOR')")
    @RequestMapping(value="/proximaQuestao/{id}", method=RequestMethod.GET)
    public ResponseEntity<QuestaoDTO> nextQuestao(@PathVariable Long id) {
        Partida partida = service.nextQuestao( id );
        if( partida == null ) { return ResponseEntity.ok().body( null ); }
        Questao obj = partida.getQuestaoAtual();
        QuestaoDTO objDto = new QuestaoDTO(obj);
        return ResponseEntity.ok().body(objDto);
    }
    
    @PreAuthorize("hasAnyRole('PROFESSOR')")
    @RequestMapping(value="/removerQuestao/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Void> removerQuestao(@PathVariable Long id) {
        service.removerQuestao( id );
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('PROFESSOR')")
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Void> end(@PathVariable Long id) {
        service.end( id );
        return ResponseEntity.noContent().build();
    }
    
    //Jogador
    @RequestMapping(value="/token/{token}", method=RequestMethod.GET)
    public ResponseEntity<PartidaDTOJogador> findPartida(@PathVariable String token) {
        Partida obj = service.findByToken(token);
        PartidaDTOJogador objDto = new PartidaDTOJogador(obj);
        return ResponseEntity.ok().body(objDto);
    }
    
    //Enviar cadastro para partida passando o nome do jogador
    @RequestMapping(value="/token/{token}/nome/{nome}", method=RequestMethod.GET)
    public ResponseEntity<JogadorDTO> entrarPartida(@PathVariable("token") String token, @PathVariable("nome") String nome) {
        Partida partida = service.findByToken(token);
        Jogador obj = jogadorService.insert(nome, token);
        JogadorDTO objDto = new JogadorDTO(obj);
        return ResponseEntity.ok().body(objDto);
    }
    
    @RequestMapping(value="/jogador/token/{token}/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> sairPartida(@PathVariable("token") String token, @PathVariable("id") Long id) {
        jogadorService.delete( id );
        return ResponseEntity.noContent().build();
    }
    
    //Enviar questao atual do quiz
    @GetMapping("/questaoAtual/{partida_id}/{jogador_id}")
    public ResponseEntity<QuestaoDTOJogador> questaoAtual(@PathVariable("partida_id") Long partidaId, @PathVariable("jogador_id") Long jogadorId) {
        Partida obj = service.find(partidaId);
        Jogador jogador = jogadorService.findById(jogadorId);
        if( jogador == null ) {
            QuestaoDTOJogador objDto = new QuestaoDTOJogador();
            objDto.setAc("invalido");
            return ResponseEntity.ok().body( objDto );            
        }
        if( obj.getToken().equals("CLOSED") ) {
            QuestaoDTOJogador objDto = new QuestaoDTOJogador( obj.getUltimaQuestao() );
            objDto.setAc("acabou");
            return ResponseEntity.ok().body( objDto );
        }
        if( obj.getQuestaoAtual() != null ) {
            List<Resposta> r = respostaService.findResponta(obj.getToken(), jogador);
            if ( r != null ) {
                QuestaoDTOJogador objDto = new QuestaoDTOJogador( obj.getUltimaQuestao() );
                objDto.setAc("respondida");
                return ResponseEntity.ok().body( objDto );
            }
            QuestaoDTOJogador objDto = new QuestaoDTOJogador( obj.getQuestaoAtual() );
            return ResponseEntity.ok().body(objDto);
        } else {
            return ResponseEntity.ok().body(null);
        }
    }  

}