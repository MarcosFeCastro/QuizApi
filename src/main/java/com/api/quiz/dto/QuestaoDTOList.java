package com.api.quiz.dto;

import com.api.quiz.models.Questao;
import java.io.Serializable;

public class QuestaoDTOList implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String titulo;
    private int ordem;

    public QuestaoDTOList() {
    }

    public QuestaoDTOList(Questao obj) {
        this.id = obj.getId();
        this.titulo = obj.getTitulo();
        this.ordem = obj.getOrdem();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

}