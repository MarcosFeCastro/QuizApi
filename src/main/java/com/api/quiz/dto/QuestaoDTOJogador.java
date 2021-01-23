package com.api.quiz.dto;

import com.api.quiz.models.Questao;
import java.io.Serializable;

public class QuestaoDTOJogador implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String titulo;
    private String alternativaA;
    private String alternativaB;
    private String alternativaC;
    private String alternativaD;
    private String ac;
    private int tempoResposta;

    public QuestaoDTOJogador() {
    }

    public QuestaoDTOJogador(Questao obj) {
        this.titulo = obj.getTitulo();
        this.alternativaA = obj.getAlternativaA();
        this.alternativaB = obj.getAlternativaB();
        this.alternativaC = obj.getAlternativaC();
        this.alternativaD = obj.getAlternativaD();
        this.ac = obj.getAc();
        this.tempoResposta = obj.getTempoResposta();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAlternativaA() {
        return alternativaA;
    }

    public void setAlternativaA(String alternativaA) {
        this.alternativaA = alternativaA;
    }

    public String getAlternativaB() {
        return alternativaB;
    }

    public void setAlternativaB(String alternativaB) {
        this.alternativaB = alternativaB;
    }

    public String getAlternativaC() {
        return alternativaC;
    }

    public void setAlternativaC(String alternativaC) {
        this.alternativaC = alternativaC;
    }

    public String getAlternativaD() {
        return alternativaD;
    }

    public void setAlternativaD(String alternativaD) {
        this.alternativaD = alternativaD;
    }

    public String getAc() {
        return ac;
    }

    public void setAc(String ac) {
        this.ac = ac;
    }

    public int getTempoResposta() {
        return tempoResposta;
    }

    public void setTempoResposta(int tempoResposta) {
        this.tempoResposta = tempoResposta;
    }

}