package com.api.quiz.repositories;

import com.api.quiz.models.Questao;
import com.api.quiz.models.Quiz;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestaoRepository extends JpaRepository<Questao, Long> {
    
    Page<Questao> findAllByQuiz(Pageable pageable, Quiz quiz_id);
    
    @Query("SELECT q FROM Questao q WHERE q.quiz = :quiz_id AND q.id = :questao_id")
    public Optional<Questao> findByIdQuiz( @Param("quiz_id") Quiz quiz_id, @Param("questao_id") Long questao_id );
    
    @Query("SELECT q FROM Questao q WHERE q.quiz = :quiz_id AND q.ordem = ( SELECT MAX(ordem) FROM Questao )")
    public Questao findByQuizOrdem( @Param("quiz_id") Quiz quiz_id );
    
    @Query("SELECT q FROM Questao q WHERE q.quiz = :quiz_id AND q.ordem = ( SELECT MIN(ordem) FROM Questao )")
    public Questao findFirstQuestao( @Param("quiz_id") Quiz quiz_id );
    
    @Query("SELECT q FROM Questao q WHERE q.quiz = :quiz_id AND q.ordem = :ordem")
    public Questao findByOrdem( @Param("quiz_id") Quiz quiz_id, @Param("ordem") int ordem );

}