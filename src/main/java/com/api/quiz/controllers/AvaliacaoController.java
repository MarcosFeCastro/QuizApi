package com.api.quiz.controllers;

import com.api.quiz.models.Avaliacao;
import com.api.quiz.models.Jogador;
import com.api.quiz.models.Partida;
import com.api.quiz.services.AvaliacaoService;
import com.api.quiz.services.JogadorService;
import com.api.quiz.services.PartidaService;
import java.net.URI;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value="/api/avaliacao")
public class AvaliacaoController {
    
    @Autowired
    private AvaliacaoService service;
    
    @Autowired
    private JogadorService jogadorService;
    
    @Autowired
    private PartidaService partidaService;
    
    @RequestMapping(value="/{jogador_id}/{partida_id}", method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Avaliacao avaliacao, @PathVariable("jogador_id") Long jogador_id, @PathVariable("partida_id") Long partida_id) {
        Partida partida = partidaService.find(partida_id);
        Jogador jogador = jogadorService.findOne(jogador_id);
        Avaliacao obj = service.insert(avaliacao, jogador, partida);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand( obj.getId() ).toUri();
        return ResponseEntity.created(uri).build();
    }
    
}
