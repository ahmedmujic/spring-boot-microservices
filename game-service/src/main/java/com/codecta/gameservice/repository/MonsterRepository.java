package com.codecta.gameservice.repository;

import com.codecta.gameservice.entity.Dungeon;
import com.codecta.gameservice.entity.Monster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonsterRepository extends JpaRepository<Monster, Integer> {

    List<Monster> findAllByAliveTrueAndDungeon(Dungeon dungeon);
}
