package com.codecta.gameservice.repository;

import com.codecta.gameservice.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface GameRepository extends JpaRepository<Game, Integer> {

        Game findGameByGameId(Integer id);
}
