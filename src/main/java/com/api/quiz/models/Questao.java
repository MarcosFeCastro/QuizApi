package com.api.quiz.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;

@Entity
public class Questao implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue( generator = "questao_seq" )
    @SequenceGenerator( name = "questao_seq", sequenceName = "seq_questao", initialValue = 1, allocationSize = 1 )
    @javax.persistence.Id
    private Long id;
    
    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min=1, max=300, message="O titulo deve ter entre 1 e 300 caracteres")
    private String titulo;
    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min=1, max=300, message="O titulo deve ter entre 1 e 300 caracteres")
    private String alternativaA;
    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min=1, max=300, message="O titulo deve ter entre 1 e 300 caracteres")
    private String alternativaB;
    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min=1, max=300, message="O titulo deve ter entre 1 e 300 caracteres")
    private String alternativaC;
    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min=1, max=300, message="O titulo deve ter entre 1 e 300 caracteres")
    private String alternativaD;
    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min=1, max=1, message="Informe a resposta")
    private String ac;

    private int dificuldade;
    private int tempoResposta;
    private int ordem;
    
    @JsonIgnoreProperties("questoes")
    @ManyToOne
    @JoinColumn(name="quiz_id")
    private Quiz quiz;
    
    @JsonIgnoreProperties("questao")
    @OneToMany(mappedBy = "questao", targetEntity = Resposta.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Resposta> respostas = new ArrayList();
    
    public Questao() {}

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
    
    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public List<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<Resposta> respostas) {
        this.respostas = respostas;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final Questao other = (Questao) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
