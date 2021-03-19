package com.codecta.gameservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(
        name = "parent_id",
        foreignKey = @ForeignKey(
                name = "ID"
        )
)
@Table(name = "HEALING_POTION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HealingPotions extends Items{


    private Double healthAddition;
}
