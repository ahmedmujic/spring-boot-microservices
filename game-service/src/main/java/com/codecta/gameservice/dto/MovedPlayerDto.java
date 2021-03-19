package com.codecta.gameservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovedPlayerDto {
    private Integer dungeonId;
    private List<MonsterDto> monsters;
    private List<ItemsDto> items;
}
