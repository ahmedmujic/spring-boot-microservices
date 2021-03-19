package com.codecta.gameservice.dto;

import com.codecta.gameservice.shared.Fight;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FightDto {
    private String message;
    private Double currentHealth;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String killedMonsterName;


    private Boolean win;

    public String setMessage(Fight message) {
        switch (message) {
            case FIGHT_WIN:
                return "You won, good job";


            case FIGHT_LOSE:
                return
                        "You lost, good luck next time";
        }
        return null;
    }
}
