package com.api.quiz.repositories;

import com.api.quiz.models.Partida;
import com.api.quiz.models.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long> {

    public Partida findByToken(String token);

    @Query("SELECT p FROM Partida p WHERE p.usuario = :usuario_id ORDER BY data DESC")
    public Page<Partida> findAllByUsuario(@Param("usuario_id") Usuario usuario_id, Pageable pageRequest);
    
    @Query("SELECT p FROM Partida p WHERE p.usuario = :usuario_id")
    public Partida findOne();

}