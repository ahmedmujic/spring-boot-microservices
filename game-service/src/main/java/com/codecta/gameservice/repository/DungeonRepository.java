package com.codecta.gameservice.repository;

import com.codecta.gameservice.entity.Dungeon;
import com.codecta.gameservice.entity.Monster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DungeonRepository extends JpaRepository<Dungeon, Integer> {
    Dungeon getDungeonById(Integer id);


}
