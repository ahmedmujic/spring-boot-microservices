package com.codecta.gameservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weapon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String weaponName;
    private Double damage;
    private Integer weaponHealth;
    @OneToMany(mappedBy = "weapon", fetch = FetchType.LAZY)
    private List<Player> players = new ArrayList<>();

    @ManyToMany(mappedBy = "weapons")
    private List<Inventory> inventories = new ArrayList<>();
}
