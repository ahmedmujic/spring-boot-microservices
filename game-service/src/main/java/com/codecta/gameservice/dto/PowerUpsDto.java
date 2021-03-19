package com.codecta.gameservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PowerUpsDto   extends ItemsDto{
    private Integer id;
    private Double powerValue;
    private String name;
}
