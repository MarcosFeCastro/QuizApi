package com.api.quiz.dto;

import com.api.quiz.models.Jogador;
import java.io.Serializable;

public class JogadorDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String nome;
    private String token;
    private Long partidaId;
    private int pontuacao;

    public JogadorDTO() {
    }

    public JogadorDTO(Jogador obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.token = obj.getPartida().getToken();
        this.partidaId = obj.getPartida().getId();
        this.pontuacao = obj.getPontuacao();
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getPartidaId() {
        return partidaId;
    }

    public void setPartidaId(Long partidaId) {
        this.partidaId = partidaId;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
    
}