package com.codecta.gameservice.repository;

import com.codecta.gameservice.entity.Items;
import com.codecta.gameservice.entity.Monster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository<Items, Integer> {

}
