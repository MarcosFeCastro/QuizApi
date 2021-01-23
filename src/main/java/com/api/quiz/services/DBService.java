package com.api.quiz.services;

import com.api.quiz.models.Questao;
import com.api.quiz.models.Quiz;
import com.api.quiz.models.Usuario;
import com.api.quiz.models.enums.Perfil;
import com.api.quiz.repositories.QuestaoRepository;
import com.api.quiz.repositories.QuizRepository;
import com.api.quiz.repositories.UsuarioRepository;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DBService {
    
    @Autowired
    private QuizRepository qir;
    
    @Autowired
    private QuestaoRepository qr;
    
    @Autowired
    private UsuarioRepository ur;
    
    @Autowired
    private BCryptPasswordEncoder pe;
    
    public void intantiateTestDatabase() {
        
        Usuario u1 = new Usuario("marcos.wb7@gmail.com", "Marcos", "Castro", pe.encode("1234"));
        //Usuario u2 = new Usuario("desenvolvedordev@gmail.com", "Fulano", "de Testes", pe.encode("123"));
        u1.addPerfil(Perfil.ADMIN);
        
        ur.saveAll(Arrays.asList(u1));
        
        /*
        Quiz qi1 = new Quiz("Primeiro Quiz");
        Quiz qi2 = new Quiz("Segundo Quiz");
        Quiz qi3 = new Quiz("Quiz Biologia");
        Quiz qi4 = new Quiz("Quiz Ciências");
        Quiz qi5 = new Quiz("Quiz Geografia");
        Quiz qi6 = new Quiz("Quiz História");
        Quiz qi7 = new Quiz("Quiz Matemática");
        Quiz qi8 = new Quiz("Quiz Português");
        
        Questao q1 = new Questao("questao 1");
        Questao q2 = new Questao("questao 2");
        Questao q3 = new Questao("questao 3");
        
        qi1.getQuestoes().addAll(Arrays.asList(q1, q2));
        q1.setQuiz(qi1);
        q2.setQuiz(qi1);
        
        qi2.getQuestoes().addAll(Arrays.asList(q3));
        q3.setQuiz(qi2);
        
        qir.saveAll(Arrays.asList(qi1, qi2, qi3, qi4, qi5, qi6, qi7, qi8));
        qr.saveAll(Arrays.asList(q1, q2, q3));
        */
        
    }
}