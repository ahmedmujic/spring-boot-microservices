package com.codecta.gameservice.dto;

import com.codecta.gameservice.entity.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {
    private Integer gameId;
    private Double score;
}
