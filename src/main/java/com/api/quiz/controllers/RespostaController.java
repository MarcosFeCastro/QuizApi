package com.api.quiz.controllers;

import com.api.quiz.dto.RankingDTO;
import com.api.quiz.dto.RespostaDTO;
import com.api.quiz.models.Jogador;
import com.api.quiz.models.Resposta;
import com.api.quiz.services.JogadorService;
import com.api.quiz.services.PartidaService;
import com.api.quiz.services.RespostaService;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value="/api/resposta")
public class RespostaController {
    
    @Autowired
    private RespostaService service;
    
    @Autowired
    private PartidaService partidaService;
    
    @Autowired
    private JogadorService jogadorService;
    
    //Receber as respostas dos jogadores e calcular pontuação -- JOGADOR
    @RequestMapping(value="/{token}/{id}", method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody RespostaDTO resposta, @PathVariable String token, @PathVariable Long id) {
        Resposta obj = service.insert(resposta, token, id);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{acerto}").buildAndExpand( obj.isCerto() ).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    //Enviar o pontuação parcial dos jogadores -- PROF
    @PreAuthorize("hasAnyRole('PROFESSOR')")
    @RequestMapping(value="/ranking/{token}", method=RequestMethod.GET)
    public ResponseEntity<List<RankingDTO>> findRanking( @PathVariable String token ) {
        List<Resposta> list = service.findRanking(token);
        List<RankingDTO> listDto = new ArrayList();
        list.forEach((r) -> {
            listDto.add(new RankingDTO(r));
        });
        return ResponseEntity.ok().body( listDto );
    }
    
    //Enviar o pontuação parcial dos jogadores -- PROF
    @PreAuthorize("hasAnyRole('PROFESSOR')")
    @RequestMapping(value="/rank/{token}", method=RequestMethod.GET)
    public ResponseEntity<List<RankingDTO>> findRank( @PathVariable String token ) {
        List<Jogador> list = service.findRank(token);
        List<RankingDTO> listDto = new ArrayList();
        list.forEach((r) -> {
            listDto.add(new RankingDTO(r));
        });
        return ResponseEntity.ok().body( listDto );
    }
    
}