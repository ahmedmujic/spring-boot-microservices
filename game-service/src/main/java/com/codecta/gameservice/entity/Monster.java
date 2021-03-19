package com.codecta.gameservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Monster {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    private Double health = 100.0;


    private Double damage = 100.0;


    private String name;

    @Type(type = "numeric_boolean")
    @Column(name = "ALIVE", nullable = false)
    private Boolean alive = true;


    @ManyToOne
    private Dungeon dungeon ;

    @ManyToMany
    private List<Items> items = new ArrayList<>();
}
