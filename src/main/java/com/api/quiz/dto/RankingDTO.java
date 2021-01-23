package com.api.quiz.dto;

import com.api.quiz.models.Jogador;
import com.api.quiz.models.Resposta;
import java.io.Serializable;

public class RankingDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String jogador;
    private int pontos;

    public RankingDTO() {
    }

    public RankingDTO(Resposta obj) {
        this.id = obj.getJogador().getId();
        this.jogador = obj.getJogador().getNome();
        this.pontos = obj.getPontos();
    }
    
    public RankingDTO(Jogador obj) {
        this.id = obj.getId();
        this.jogador = obj.getNome();
        this.pontos = obj.getPontuacao();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJogador() {
        return jogador;
    }

    public void setJogador(String jogador) {
        this.jogador = jogador;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

}