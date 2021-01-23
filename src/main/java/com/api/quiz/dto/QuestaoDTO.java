package com.api.quiz.dto;

import com.api.quiz.models.Questao;
import java.io.Serializable;

public class QuestaoDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String titulo;
    private String alternativaA;
    private String alternativaB;
    private String alternativaC;
    private String alternativaD;
    private String ac;
    private int dificuldade;
    private int tempoResposta;
    private int ordem;
    
    private String quiz;

    public QuestaoDTO() {
    }

    public QuestaoDTO(Questao obj) {
        this.id = obj.getId();
        this.titulo = obj.getTitulo();
        this.alternativaA = obj.getAlternativaA();
        this.alternativaB = obj.getAlternativaB();
        this.alternativaC = obj.getAlternativaC();
        this.alternativaD = obj.getAlternativaD();
        this.ac = obj.getAc();
        this.dificuldade = obj.getDificuldade();
        this.tempoResposta = obj.getTempoResposta();
        this.ordem = obj.getOrdem();
        this.quiz = obj.getQuiz().getNome();
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

    public int getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }

    public int getTempoResposta() {
        return tempoResposta;
    }

    public void setTempoResposta(int tempoResposta) {
        this.tempoResposta = tempoResposta;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public String getQuiz() {
        return quiz;
    }

    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }

}