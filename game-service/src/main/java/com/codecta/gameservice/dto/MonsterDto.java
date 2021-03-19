package com.codecta.gameservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonsterDto {
    private Integer id;
    private Double health;
    private Double damage;
    private String name;
    private Boolean alive;
}
