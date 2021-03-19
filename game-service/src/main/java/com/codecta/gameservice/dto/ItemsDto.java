package com.codecta.gameservice.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemsDto {
    @JsonIgnore
    private Integer id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private HealingPotionDto healingPotion;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PowerUpsDto powerUps;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer quantity;
}
