package com.api.quiz.controllers;

import com.api.quiz.dto.QuestaoDTO;
import com.api.quiz.dto.QuestaoDTOList;
import com.api.quiz.models.Questao;
import com.api.quiz.models.Quiz;
import com.api.quiz.services.QuestaoService;
import java.net.URI;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value="/api/quiz/{quiz_id}")
@PreAuthorize("hasAnyRole('PROFESSOR')")
public class QuestaoController {
    
    @Autowired
    private QuestaoService service;
    
    @RequestMapping(value="/questoes", method=RequestMethod.GET)
    public ResponseEntity<Page<QuestaoDTOList>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page, 
            @RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage, 
            @RequestParam(value="orderBy", defaultValue="id")String orderBy, 
            @RequestParam(value="direction", defaultValue="ASC")String direction,
            @PathVariable Quiz quiz_id) {
        Page<Questao> listObj = service.findPage(page, linesPerPage, orderBy, direction, quiz_id);
        Page<QuestaoDTOList> listDto = listObj.map(obj -> new QuestaoDTOList(obj));
        return ResponseEntity.ok().body(listDto);
    }
    
    @RequestMapping(value="/questao/{questao_id}", method=RequestMethod.GET)
    public ResponseEntity<QuestaoDTO> find(@PathVariable Quiz quiz_id, @PathVariable Long questao_id) {
        Questao obj = service.find(quiz_id, questao_id);
        QuestaoDTO objDto = new QuestaoDTO(obj);
        return ResponseEntity.ok().body(objDto);
    }
    
    @RequestMapping(value="/questao", method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Questao obj, @PathVariable Quiz quiz_id) {
        obj = service.insert(obj, quiz_id);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{questao_id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    @RequestMapping(value="/questao/{questao_id}", method=RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody Questao obj, @PathVariable Long questao_id, @PathVariable Quiz quiz_id) {
        obj = service.update(obj, questao_id, quiz_id);
        return ResponseEntity.noContent().build();
    }
    
    @RequestMapping(value="/questao/{questao_id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long questao_id, @PathVariable Long quiz_id) {
        service.delete(questao_id, quiz_id);
        return ResponseEntity.noContent().build();
    }
    
}