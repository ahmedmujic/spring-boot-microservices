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
public class Dungeon {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Boolean finished = false;
    @ManyToMany
    private List<Items> items = new ArrayList<>();

    @OneToMany(mappedBy = "currentDungeon", fetch = FetchType.LAZY)
    private List<Player> players = new ArrayList<>();


    @OneToMany(mappedBy = "dungeon", fetch = FetchType.LAZY)
    private List<Monster> monsters = new ArrayList<>();
}
