package com.api.quiz.repositories;

import com.api.quiz.models.Jogador;
import com.api.quiz.models.Partida;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Long> {
    
    @Query("SELECT j FROM Jogador j WHERE j.partida = :partida_id ORDER BY id")
    public List<Jogador> findAllByPartida(@Param("partida_id") Partida partida_id);

    public Jogador findByPartida(Partida partida_id);

    @Query("SELECT j FROM Jogador j WHERE j.id = :id")
    public Jogador findOne(@Param("id") Long id);
    
    //BUSCAR RANK FINAL PARA RELATORIO
    @Query("SELECT j FROM Jogador j WHERE j.partida = :partida_id ORDER BY pontuacao DESC")
    public List<Jogador> findByPontuacao( @Param("partida_id") Partida partida_id, Pageable pageable );

}