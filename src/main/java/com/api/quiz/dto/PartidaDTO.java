package com.api.quiz.dto;

import com.api.quiz.models.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PartidaDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    
    private String token;
    
    private Long quizId;
    
    private String quiz;
    
    private String descricao;

    private boolean privado;
    
    private String linguagem;
    
    private String ambiente;
    
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private Date dataCriacao;
    
    private String criadorQuiz;

    private List<QuestaoDTO> questoes = new ArrayList();

    public PartidaDTO() {
    }

    public PartidaDTO(Partida obj) {
        this.id = obj.getId();
        this.token = obj.getToken();
        this.quizId = obj.getQuiz().getId();
        this.quiz = obj.getQuiz().getNome();
        this.descricao = obj.getQuiz().getDescricao();
        this.privado = obj.getQuiz().isPrivado();
        this.linguagem = obj.getQuiz().getLinguagem();
        this.ambiente = obj.getQuiz().getAmbiente();
        this.dataCriacao = obj.getQuiz().getDataCriacao();
        this.criadorQuiz = obj.getQuiz().getUsuario().getNome();
        List<Questao> listQuestoes = obj.getQuiz().getQuestoes();
        for(Questao q : listQuestoes) { questoes.add(new QuestaoDTO(q)); }
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

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
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

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getCriadorQuiz() {
        return criadorQuiz;
    }

    public void setCriadorQuiz(String criadorQuiz) {
        this.criadorQuiz = criadorQuiz;
    }

    public List<QuestaoDTO> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(List<QuestaoDTO> questoes) {
        this.questoes = questoes;
    }

}