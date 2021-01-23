package com.api.quiz.dto;

import com.api.quiz.models.Quiz;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

public class QuizDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String nome;
    private String tema;
    private String descricao;
    private boolean privado;
    private String linguagem;
    private String ambiente;
    private String usuario;
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dataCriacao;

    public QuizDTO() {
    }

    public QuizDTO(Quiz obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.tema = obj.getTema();
        this.usuario = obj.getUsuario().getNome();
        this.descricao = obj.getDescricao();
        this.privado = obj.isPrivado();
        this.linguagem = obj.getLinguagem();
        this.ambiente = obj.getAmbiente();
        this.dataCriacao = obj.getDataCriacao();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isPrivado() {
        return privado;
    }

    public void setPrivado(boolean privado) {
        this.privado = privado;
    }

    public String getLinguagem() {
        return linguagem;
    }

    public void setLinguagem(String linguagem) {
        this.linguagem = linguagem;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

}
