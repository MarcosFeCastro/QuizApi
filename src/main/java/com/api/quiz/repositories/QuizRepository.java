package com.api.quiz.repositories;

import com.api.quiz.models.Quiz;
import com.api.quiz.models.Usuario;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    
    Page<Quiz> findByUsuario(Usuario usuario, Pageable pageRequest);
    
    @Query("SELECT q FROM Quiz q WHERE q.id = :id AND q.usuario = :usuario")
    public Optional<Quiz> findByIdUsuario(
            @Param("id") Long id,
            @Param("usuario") Usuario usuario);
}