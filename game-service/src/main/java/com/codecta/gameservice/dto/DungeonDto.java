package com.codecta.gameservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DungeonDto {
    private Integer id;
    private String name;
    private List<MonsterDto> monsters = new ArrayList<>();
    private List<ItemsDto> items = new ArrayList<>();
    private Boolean finished;
}
