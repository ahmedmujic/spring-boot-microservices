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
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String email;


    private Double health = 1000.0;

    private Double powerBoost = 1.0;

    @ManyToOne()
    private Weapon weapon;

    @ManyToOne
    private Dungeon currentDungeon;


    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY)
    private List<Game> games = new ArrayList<>();

    @ManyToOne
    private Inventory playerInventory;

}
