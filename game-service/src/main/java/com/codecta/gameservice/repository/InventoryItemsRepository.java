package com.codecta.gameservice.repository;


import com.codecta.gameservice.entity.Inventory;
import com.codecta.gameservice.entity.InventoryItems;
import com.codecta.gameservice.entity.InventoryItemsPk;
import com.codecta.gameservice.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface InventoryItemsRepository extends JpaRepository<InventoryItems, Serializable> {



}
