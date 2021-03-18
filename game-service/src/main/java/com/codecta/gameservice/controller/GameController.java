package com.codecta.gameservice.controller;

import com.codecta.gameservice.dto.PlayerDto;
import com.codecta.gameservice.entity.Game;
import com.codecta.gameservice.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/game")
@Slf4j
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping("/start")
    public Game createGame(@RequestBody PlayerDto player){
        log.info("Inside add user");
        return gameService.createGame(player);
    }


    @PostMapping("/get/{id}")
    public Game findGameByID(@PathParam("id") Integer id){
        log.info("find player by id controller");
        return gameService.findGameByID(id);
    }
}
