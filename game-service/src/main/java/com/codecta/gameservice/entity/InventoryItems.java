package com.codecta.gameservice.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(InventoryItemsPk.class)
public class InventoryItems {



    @Id
    @ManyToOne
    private Inventory inventory;


    @Id
    @ManyToOne
    private Items items;

    @Column(name = "quantity")
    private Integer quantity;
}
