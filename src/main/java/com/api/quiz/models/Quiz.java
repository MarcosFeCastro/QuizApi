package com.api.quiz.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Quiz implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue( generator = "quiz_seq" )
    @SequenceGenerator( name = "quiz_seq", sequenceName = "seq_quiz", initialValue = 1, allocationSize = 1 )
    private Long id;
    
    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min=1, max=80, message="O nome deve ter entre 1 e 80 caracteres")
    private String nome;
    
    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min=1, max=20, message="O tema deve ter entre 1 e 20 caracteres")
    private String tema;
    
    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min=1, max=200, message="O nome deve ter entre 1 e 200 caracteres")
    private String descricao;
    
    private boolean privado;
    
    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min=1, max=80, message="Informe o idioma")
    private String linguagem;
    
    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min=1, max=20, message="O nome ambiente ter entre 1 e 20 caracteres")
    private String ambiente;
    
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private Date dataCriacao;
    
    @JsonIgnoreProperties("quiz")
    @ManyToOne
    @JoinColumn(name="Usuario_id")
    private Usuario usuario;
    
    @JsonIgnoreProperties("quiz")
    @OneToMany(mappedBy = "quiz", targetEntity = Questao.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Questao> questoes = new ArrayList();
    
    public Quiz(){}

    public Quiz(String nome) {
        this.nome = nome;
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

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isPrivado() {
        return privado;
    }

    public void setPrivado(boolean privado) {
        this.privado = privado;
    }

    public String getLinguagem() {
        return linguagem;
    }

    public void setLinguagem(String linguagem) {
        this.linguagem = linguagem;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Questao> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(List<Questao> questoes) {
        this.questoes = questoes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Quiz other = (Quiz) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}