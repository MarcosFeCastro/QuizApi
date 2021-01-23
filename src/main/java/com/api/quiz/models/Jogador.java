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
public class Jogador implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue( generator = "jogador_seq" )
    @SequenceGenerator( name = "jogador_seq", sequenceName = "seq_jogador", initialValue = 1, allocationSize = 1 )
    @javax.persistence.Id
    private Long id;
    
    private String nome;
    
    private int pontuacao;
    
    @JsonIgnoreProperties("jogadores")
    @ManyToOne
    @JoinColumn(name="partida_id")
    private Partida partida;

    public Jogador() {
    }

    public Jogador(Long id, String nome, Partida partida) {
        this.id = id;
        this.nome = nome;
        this.pontuacao = 0;
        this.partida = partida;
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

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
    
    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }
    
}
