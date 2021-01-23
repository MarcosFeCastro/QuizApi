package com.api.quiz.dto;

import com.api.quiz.models.Quiz;
import java.io.Serializable;
import java.util.Date;

public class QuizDTOList implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String nome;
    private String tema;
    private String descricao;
    private Date data;
    private String ambiente;
    private String linguagem;
    private int qtdQuestao;

    public QuizDTOList() {
    }

    public QuizDTOList(Quiz obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.tema = obj.getTema();
        this.descricao = obj.getDescricao();
        this.data = obj.getDataCriacao();
        this.ambiente = obj.getAmbiente();
        this.linguagem = obj.getLinguagem();
        this.qtdQuestao = obj.getQuestoes().size();
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    public String getLinguagem() {
        return linguagem;
    }

    public void setLinguagem(String linguagem) {
        this.linguagem = linguagem;
    }

    public int getQtdQuestao() {
        return qtdQuestao;
    }

    public void setQtdQuestao(int qtdQuestao) {
        this.qtdQuestao = qtdQuestao;
    }

}
