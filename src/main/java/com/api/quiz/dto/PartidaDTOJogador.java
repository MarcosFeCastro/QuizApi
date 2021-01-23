package com.api.quiz.dto;

import com.api.quiz.models.*;
import java.io.Serializable;

public class PartidaDTOJogador implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String token;
    private String quiz;
    private String descricao;
    private String usuario;

    public PartidaDTOJogador() {
    }

    public PartidaDTOJogador(Partida obj) {
        this.id = obj.getId();
        this.token = obj.getToken();
        this.quiz = obj.getQuiz().getNome();
        this.descricao = obj.getQuiz().getDescricao();
        this.usuario = obj.getUsuario().getNome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getQuiz() {
        return quiz;
    }

    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
}