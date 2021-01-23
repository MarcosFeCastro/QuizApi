package com.api.quiz.controllers;

import com.api.quiz.dto.RelatorioDTODetalhes;
import com.api.quiz.dto.RelatorioDTOList;
import com.api.quiz.models.Partida;
import com.api.quiz.services.PartidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/relatorio")
@PreAuthorize("hasAnyRole('PROFESSOR')")
public class RelatorioController {
    
    @Autowired
    private PartidaService service;
    
    //Relatorios
    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<Page<RelatorioDTOList>> findAllfindPage(
            @RequestParam(value="page", defaultValue="0") Integer page, 
            @RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage, 
            @RequestParam(value="orderBy", defaultValue="data")String orderBy, 
            @RequestParam(value="direction", defaultValue="DESC")String direction) {
        Page<Partida> listObj = service.findPage(page, linesPerPage, orderBy, direction);
        Page<RelatorioDTOList> objDto = listObj.map(obj -> new RelatorioDTOList(obj));
        return ResponseEntity.ok().body(objDto);
    }
    
    @PreAuthorize("hasAnyRole('PROFESSOR')")
    @RequestMapping(value="/{partida_id}", method=RequestMethod.GET)
    public ResponseEntity<RelatorioDTODetalhes> find(@PathVariable("partida_id") Long partida_id) {
        Partida obj = service.find(partida_id);
        // --> BUSCAR O RESTOOO
        RelatorioDTODetalhes objDto = new RelatorioDTODetalhes(obj);
        return ResponseEntity.ok().body(objDto);
    }    

}