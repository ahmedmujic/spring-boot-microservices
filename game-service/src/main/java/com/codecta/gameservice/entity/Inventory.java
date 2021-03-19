package com.codecta.gameservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(mappedBy = "inventory")
    private List<InventoryItems> itemsAsoc;

    @OneToMany(mappedBy = "playerInventory", fetch = FetchType.LAZY)
    private List<Player> players = new ArrayList<>();

    @ManyToMany

    private List<Weapon> weapons = new ArrayList<>();
}
