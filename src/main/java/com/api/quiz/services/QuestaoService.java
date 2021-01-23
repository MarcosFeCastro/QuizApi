package com.api.quiz.services;

import com.api.quiz.models.Questao;
import com.api.quiz.models.Quiz;
import com.api.quiz.repositories.QuestaoRepository;
import com.api.quiz.services.exceptions.ObjectNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class QuestaoService {
    
    @Autowired
    private QuestaoRepository questaoRepository;
    
    @Autowired
    private QuizService quizService;
    
    public Page<Questao> findPage(Integer page, Integer linesPerPage, String orderBy, String direction, Quiz quiz_id){
        quizService.find(quiz_id.getId());
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return questaoRepository.findAllByQuiz(pageRequest, quiz_id);
    }
    
    public Questao find(Quiz quiz_id, Long questao_id) {
        quizService.find(quiz_id.getId());
        Optional<Questao> obj = questaoRepository.findByIdQuiz(quiz_id, questao_id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
            "Objeto não encontrado! Id: " + questao_id + ", Tipo: " + Quiz.class.getName()));
    }
    
    public int findNextOrdem(Quiz quiz_id) {
        quizService.find(quiz_id.getId());
        Questao obj = questaoRepository.findByQuizOrdem(quiz_id);
        if ( obj == null) {
            return 1;
        } else {
            int valor = obj.getOrdem();
            return ( valor + 1);
        }
    }
    
    public Questao insert(Questao obj, Quiz quiz_id) {
        quizService.find(quiz_id.getId());
        obj.setId(null);
        obj.setQuiz(quiz_id);
        obj.setOrdem(findNextOrdem(quiz_id));
        return questaoRepository.save(obj);
    }
    
    public Questao update(Questao obj, Long questao_id, Quiz quiz_id) {
        quizService.find(quiz_id.getId());
        Questao q = find(quiz_id, questao_id);
        obj.setId(q.getId());
        obj.setQuiz(q.getQuiz());
        obj.setOrdem(q.getOrdem());
        return questaoRepository.save(obj);
    }
    
    public void delete(Long questao_id, Long quiz_id) {
        Quiz quiz = quizService.find(quiz_id);
        find(quiz, questao_id);
        questaoRepository.deleteById(questao_id);
    }
    
}