package com.api.quiz.models;

import com.api.quiz.models.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Usuario implements Serializable{
	
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( generator = "usuario_seq" )
    @SequenceGenerator( name = "usuario_seq", sequenceName = "seq_usuario", initialValue = 1, allocationSize = 1 )
    private Long id;
    
    @NotEmpty(message="Preenchimento obrigatório")
    @Email(message="Email inválido")
    private String email;

    @NotEmpty(message="Preenchimento obrigatório")
    private String nome;
    
    @NotEmpty(message="Preenchimento obrigatório")
    private String sobrenome;
    
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private Date dataCadastro;

    @NotEmpty(message="Preenchimento obrigatório")
    private String senha;

    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name="PERFIS")
    private Set<Integer> perfis = new HashSet<>();
    
    @OneToMany(mappedBy="usuario", cascade=CascadeType.ALL)
    private List<Quiz> quiz = new ArrayList<>();

    public Usuario() {
        addPerfil(Perfil.PROFESSOR);
    }

    public Usuario(String email, String nome, String sobrenome, String senha) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.senha = senha;
        addPerfil(Perfil.PROFESSOR);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }
    
    public void addPerfil(Perfil perfil) {
        perfis.add(perfil.getCod());
    }
    
    public List<Quiz> getQuiz() {
        return quiz;
    }

    public void setQuiz(List<Quiz> quiz) {
        this.quiz = quiz;
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder builder = new StringBuilder();
        builder.append("Seja bem vindo, ");
        builder.append(getNome());
        builder.append("!");
        builder.append("\n");
        builder.append("Uma nova conta foi registrada com o Email: ");
        builder.append(getEmail());
        builder.append(". No dia ");
        builder.append(dateFormat.format(getDataCadastro()));
        builder.append(".");
        return builder.toString();
    }
    
}