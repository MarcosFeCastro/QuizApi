package com.api.quiz.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import org.springframework.data.annotation.Id;

@Entity
public class Partida implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue( generator = "partida_seq" )
    @SequenceGenerator( name = "partida_seq", sequenceName = "seq_partida", initialValue = 1, allocationSize = 1 )
    @javax.persistence.Id
    private Long id;
    
    private String token;
    
    @JoinColumn(name="ordem")
    private Questao questaoAtual;
    
    @JoinColumn(name="ordem")
    private Questao ultimaQuestao;
    
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private Date data;
    
    @JsonIgnoreProperties("partida")
    @OneToMany(mappedBy = "partida", targetEntity = Jogador.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Jogador> jogadores = new ArrayList();
    
    @OneToMany(mappedBy="partida", cascade=CascadeType.ALL)
    private List<Resposta> respostas = new ArrayList<>();
    
    @JsonIgnoreProperties("partidas")
    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario usuario;
    
    @JsonIgnoreProperties("partidas")
    @ManyToOne
    @JoinColumn(name="quiz_id")
    private Quiz quiz;
    
    @JsonIgnoreProperties("partida")
    @OneToMany(mappedBy = "partida", targetEntity = Avaliacao.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Avaliacao> avaliacoes = new ArrayList();

    public Partida() {
    }

    public Partida(Long id, String token, Date data, Usuario usuario, Quiz quiz) {
        this.id = id;
        this.token = token;
        this.data = data;
        this.usuario = usuario;
        this.quiz = quiz;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Questao getQuestaoAtual() {
        return questaoAtual;
    }

    public void setQuestaoAtual(Questao questaoAtual) {
        this.questaoAtual = questaoAtual;
    }

    public Questao getUltimaQuestao() {
        return ultimaQuestao;
    }

    public void setUltimaQuestao(Questao ultimaQuestao) {
        this.ultimaQuestao = ultimaQuestao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public List<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<Resposta> respostas) {
        this.respostas = respostas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }
    
}