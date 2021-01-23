package com.api.quiz.dto;

import com.api.quiz.models.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class RelatorioDTODetalhes implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    
    private String quiz;
    private String tema;
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private Date dataQuiz;
    
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private Date dataPartida;
    
    private int qtdJogadores;
    private float aproveitamento;
    private float mediaAcerto;
    
    private float mediaNota;
    private float mediaSatisfacao;
    private int qtdRecomenda;
    private int qtdNaoRecomenda;
    private int qtdAprendeu;
    private int qtdNaoAprendeu;
    
    private PontuacaoFinalDTO pontuacaoFinal;
    private List<QuestaoRespondidaDTO> questoesRespondidas;

    public RelatorioDTODetalhes() {
    }

    public RelatorioDTODetalhes(Partida obj) {
        this.id = obj.getId();
        this.quiz = obj.getQuiz().getNome();
        this.tema = obj.getQuiz().getTema();
        this.dataQuiz = obj.getQuiz().getDataCriacao();
        this.dataPartida = obj.getData();
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

    public Date getDataQuiz() {
        return dataQuiz;
    }

    public void setDataQuiz(Date dataQuiz) {
        this.dataQuiz = dataQuiz;
    }

    public Date getDataPartida() {
        return dataPartida;
    }

    public void setDataPartida(Date dataPartida) {
        this.dataPartida = dataPartida;
    }

    public int getQtdJogadores() {
        return qtdJogadores;
    }

    public float getAproveitamento() {
        return aproveitamento;
    }

    public void setAproveitamento(float aproveitamento) {
        this.aproveitamento = aproveitamento;
    }

    public float getMediaAcerto() {
        return mediaAcerto;
    }

    public void setMediaAcerto(float mediaAcerto) {
        this.mediaAcerto = mediaAcerto;
    }

    public void setQtdJogadores(int qtdJogadores) {
        this.qtdJogadores = qtdJogadores;
    }

    public float getMediaNota() {
        return mediaNota;
    }

    public void setMediaNota(float mediaNota) {
        this.mediaNota = mediaNota;
    }

    public float getMediaSatisfacao() {
        return mediaSatisfacao;
    }

    public void setMediaSatisfacao(float mediaSatisfacao) {
        this.mediaSatisfacao = mediaSatisfacao;
    }

    public int getQtdRecomenda() {
        return qtdRecomenda;
    }

    public void setQtdRecomenda(int qtdRecomenda) {
        this.qtdRecomenda = qtdRecomenda;
    }

    public int getQtdNaoRecomenda() {
        return qtdNaoRecomenda;
    }

    public void setQtdNaoRecomenda(int qtdNaoRecomenda) {
        this.qtdNaoRecomenda = qtdNaoRecomenda;
    }

    public int getQtdAprendeu() {
        return qtdAprendeu;
    }

    public void setQtdAprendeu(int qtdAprendeu) {
        this.qtdAprendeu = qtdAprendeu;
    }

    public int getQtdNaoAprendeu() {
        return qtdNaoAprendeu;
    }

    public void setQtdNaoAprendeu(int qtdNaoAprendeu) {
        this.qtdNaoAprendeu = qtdNaoAprendeu;
    }

    public PontuacaoFinalDTO getPontuacaoFinal() {
        return pontuacaoFinal;
    }

    public void setPontuacaoFinal(PontuacaoFinalDTO pontuacaoFinal) {
        this.pontuacaoFinal = pontuacaoFinal;
    }

    public List<QuestaoRespondidaDTO> getQuestoesRespondidas() {
        return questoesRespondidas;
    }

    public void setQuestoesRespondidas(List<QuestaoRespondidaDTO> questoesRespondidas) {
        this.questoesRespondidas = questoesRespondidas;
    }
    
}