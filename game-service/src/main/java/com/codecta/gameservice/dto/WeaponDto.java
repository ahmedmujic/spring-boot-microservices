package com.codecta.gameservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeaponDto {
    private Integer id;
    private String weaponName;
    private Double damage;
    private Integer weaponHealth;
}
