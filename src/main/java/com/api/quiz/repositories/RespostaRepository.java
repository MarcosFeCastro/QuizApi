package com.api.quiz.repositories;

import com.api.quiz.models.Jogador;
import com.api.quiz.models.Partida;
import com.api.quiz.models.Questao;
import com.api.quiz.models.Resposta;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta, Long> {

    //BUSCAR RANK PARCIAR DA PARTIDA
    
    
    //BUSCAR RANK PARCIAL PARA RELATORIO
    //@Query("SELECT r FROM Resposta r WHERE r.partida = :partida_id AND r.questao = :questao_id AND r.certo = true ORDER BY pontos DESC")
    @Query("SELECT r FROM Resposta r WHERE (r.partida = :partida_id) AND (r.questao = :questao_id) AND (r.certo = true) ORDER BY pontos DESC")
    public List<Resposta> findByPontuacaoParcial( @Param("partida_id") Partida partida_id, @Param("questao_id") Questao questao_id, Pageable pageable );

    
    @Query("SELECT r FROM Resposta r WHERE (r.jogador = :jogador_id) AND (r.partida = :partida_id) AND (r.questao = :questaoAtual)")
    public Optional<List<Resposta>> findQuestaoRespondida(@Param("jogador_id") Jogador id, @Param("partida_id") Partida partida_id, @Param("questaoAtual") Questao questaoAtual);

}