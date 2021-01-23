package com.api.quiz.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import org.springframework.data.annotation.Id;

@Entity
public class Resposta implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue( generator = "resposta_seq" )
    @SequenceGenerator( name = "resposta_seq", sequenceName = "seq_resposta", initialValue = 1, allocationSize = 1 )
    @javax.persistence.Id
    private Long id;

    @JsonIgnoreProperties("respostas")
    @ManyToOne
    @JoinColumn(name="jogador_id")
    private Jogador jogador;
    
    private String alternativa;
    
    private boolean certo;
    
    private int tempo;
    
    private int pontos;
    
    @JsonIgnoreProperties("respostas")
    @ManyToOne
    @JoinColumn(name="questao_id")
    private Questao questao;
    
    @JsonIgnoreProperties("respostas")
    @ManyToOne
    @JoinColumn(name="Partida_id")
    private Partida partida;

    public Resposta() {
    }

    public Resposta(Long id, Jogador jogador, String alternativa, boolean certo, int tempo, int pontos, Questao questao, Partida partida) {
        this.id = id;
        this.jogador = jogador;
        this.alternativa = alternativa;
        this.certo = certo;
        this.tempo = tempo;
        this.pontos = pontos;
        this.questao = questao;
        this.partida = partida;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public String getAlternativa() {
        return alternativa;
    }

    public void setAlternativa(String alternativa) {
        this.alternativa = alternativa;
    }

    public boolean isCerto() {
        return certo;
    }

    public void setCerto(boolean certo) {
        this.certo = certo;
    }

    public int getTempo() {
        return tempo;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public Questao getQuestao() {
        return questao;
    }

    public void setQuestao(Questao questao) {
        this.questao = questao;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    
}