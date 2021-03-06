package com.codecta.gameservice.repository;

import com.codecta.gameservice.entity.Game;
import com.codecta.gameservice.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

}
