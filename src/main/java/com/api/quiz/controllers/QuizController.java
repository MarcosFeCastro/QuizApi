package com.api.quiz.controllers;

import com.api.quiz.dto.QuizDTO;
import com.api.quiz.dto.QuizDTOList;
import com.api.quiz.models.Quiz;
import com.api.quiz.services.QuizService;
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
@RequestMapping(value="/api/quiz")
@PreAuthorize("hasAnyRole('PROFESSOR')")
public class QuizController {
    
    @Autowired
    private QuizService service;
    
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<QuizDTO> find(@PathVariable Long id) {
        Quiz obj = service.find(id);
        QuizDTO objDto = new QuizDTO(obj);
        return ResponseEntity.ok().body(objDto);
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<Page<QuizDTOList>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page, 
            @RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage, 
            @RequestParam(value="orderBy", defaultValue="id")String orderBy, 
            @RequestParam(value="direction", defaultValue="DESC")String direction) {
        Page<Quiz> listObj = service.findPage(page, linesPerPage, orderBy, direction);
        Page<QuizDTOList> listDto = listObj.map(obj -> new QuizDTOList(obj));
        return ResponseEntity.ok().body(listDto);
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Quiz obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody Quiz obj, @PathVariable Long id) {
        obj = service.update(obj, id);
        return ResponseEntity.noContent().build();
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}