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
public class Avaliacao implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue( generator = "avaliacao_seq" )
    @SequenceGenerator( name = "avaliacao_seq", sequenceName = "seq_avaliacao", initialValue = 1, allocationSize = 1 )
    @javax.persistence.Id
    private Long id;
    
    @JsonIgnoreProperties("respostas")
    @ManyToOne
    @JoinColumn(name="jogador_id")
    private Jogador jogador;
    
    private int nota;
    
    private int satisfacao;
    
    private boolean aprendeu;
    
    private boolean recomenda;
    
    @JsonIgnoreProperties("avaliacoes")
    @ManyToOne
    @JoinColumn(name="parida_id")
    private Partida partida;

    public Avaliacao() {
    }

    public Avaliacao(Long id, Jogador jogador, int nota, int satisfacao, boolean aprendeu, boolean recomenda, Partida partida) {
        this.id = id;
        this.jogador = jogador;
        this.nota = nota;
        this.satisfacao = satisfacao;
        this.aprendeu = aprendeu;
        this.recomenda = recomenda;
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

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public int getSatisfacao() {
        return satisfacao;
    }

    public void setSatisfacao(int satisfacao) {
        this.satisfacao = satisfacao;
    }

    public boolean isAprendeu() {
        return aprendeu;
    }

    public void setAprendeu(boolean aprendeu) {
        this.aprendeu = aprendeu;
    }

    public boolean isRecomenda() {
        return recomenda;
    }

    public void setRecomenda(boolean recomenda) {
        this.recomenda = recomenda;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }
    
    
    
}