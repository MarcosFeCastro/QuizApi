package com.api.quiz.dto;

import com.api.quiz.models.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

public class RelatorioDTOList implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    
    private String quiz;
    
    private String tema;
    
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private Date data;

    public RelatorioDTOList() {
    }

    public RelatorioDTOList(Partida obj) {
        this.id = obj.getId();
        this.quiz = obj.getQuiz().getNome();
        this.tema = obj.getQuiz().getTema();
        this.data = obj.getData();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuiz() {
        return quiz;
    }

    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

}