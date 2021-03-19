package com.codecta.gameservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@PrimaryKeyJoinColumn(
        name = "parent_id",
        foreignKey = @ForeignKey(
                name = "ID"
        )
)
@Table(name = "POWER_UPS")
@Inheritance(strategy = InheritanceType.JOINED)
public class PowerUps extends Items{


    private Double powerValue;

}
