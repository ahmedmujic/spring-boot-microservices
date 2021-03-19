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
@Inheritance(strategy = InheritanceType.JOINED)
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToMany(mappedBy = "items")
    private List<Dungeon> dungeons = new ArrayList<>();

    @OneToMany(mappedBy = "items")
    private List<InventoryItems> inventoryAsoc;

    @ManyToMany(mappedBy = "items")
    private List<Monster> monsters = new ArrayList<>();

    private String name;
}
