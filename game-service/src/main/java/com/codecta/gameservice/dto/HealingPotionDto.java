package com.codecta.gameservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealingPotionDto extends ItemsDto {
    private Integer id;
    private Double healthAddition;
    private String name;
}
