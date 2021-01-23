package com.api.quiz.dto;

import java.io.Serializable;

public class RespostaDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String jogador;
    
    private String resposta;
    
    private int tempo;

    public RespostaDTO() {
    }

    public RespostaDTO(String jogador, String resposta, int tempo) {
        this.jogador = jogador;
        this.resposta = resposta;
        this.tempo = tempo;
    }

    public String getJogador() {
        return jogador;
    }

    public void setJogador(String jogador) {
        this.jogador = jogador;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

}