package com.api.quiz.controllers;

import com.api.quiz.dto.UsuarioDTO;
import com.api.quiz.models.Usuario;
import com.api.quiz.services.UsuarioService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value="/api/usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioService service;
    
    @PreAuthorize("hasAnyRole('PROFESSOR')")
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<UsuarioDTO> find(@PathVariable Long id) {
        Usuario obj = service.find(id);
        UsuarioDTO objDto = new UsuarioDTO(obj);
        return ResponseEntity.ok().body(objDto);
    }
    
    @PreAuthorize("hasAnyRole('PROFESSOR')")
    @RequestMapping(value="/email", method=RequestMethod.GET)
    public ResponseEntity<UsuarioDTO> findByEmail(@RequestParam(value="value") String email) {
        Usuario obj = service.findByEmail(email);
        UsuarioDTO objDto = new UsuarioDTO(obj);
        return ResponseEntity.ok().body(objDto);
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Usuario obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    @PreAuthorize("hasAnyRole('PROFESSOR')")
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody Usuario obj, @PathVariable Long id) {
        obj = service.update(obj, id);
        return ResponseEntity.noContent().build();
    }
    
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        List<Usuario> listObj = service.findAll();
        List<UsuarioDTO> listDto = new ArrayList(); // = list.stream().map(obj -> new QuizDTO(obj)).collect(Collectors.toList());
        for(Usuario q : listObj) { listDto.add(new UsuarioDTO(q)); }
        return ResponseEntity.ok().body(listDto);
    }
    
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}