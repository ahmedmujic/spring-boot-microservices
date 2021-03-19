package com.codecta.gameservice.controller;

import com.codecta.gameservice.dto.*;
import com.codecta.gameservice.entity.Game;
import com.codecta.gameservice.service.GameService;
import com.codecta.gameservice.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.*;
import java.util.List;

@RestController
@RequestMapping("/game")
@Slf4j
public class GameController {
    public class Error {
        public String code;
        public String description;

        public Error(String code, String description) {
            this.code = code;
            this.description = description;
        }
    }

    @Autowired
    private GameService gameService;
    @Autowired
    private PlayerService playerService;


    @PostMapping("/start")
    public GameDto createGame(@RequestBody PlayerDto player) {
        log.info("Inside add user");
        return gameService.createGame(player);
    }

    @PostMapping("/{id}/move")
    public ResponseEntity movePlayer(@PathVariable Integer id) {
        System.out.println("gameid: " + id);
        MovedPlayerDto currentDungeonDto = playerService.movePlayer(id);
        if (currentDungeonDto != null) {
            return ResponseEntity.ok().body(currentDungeonDto);
        }
        return ResponseEntity.badRequest().body("Bad request - kill all monsters or finish game");
    }

    @PostMapping("/{id}/fight")
    public ResponseEntity fightWithMonster(@PathVariable Integer id,@RequestBody AttackDto attack) {
        FightDto currentDungeonDto = playerService.fightWithMonster(id, attack);
        if (currentDungeonDto != null) {

            return ResponseEntity.ok().body(currentDungeonDto);
        }
        return ResponseEntity.badRequest().body("ashsah");
    }

    @GetMapping("player/{id}")
    public ResponseEntity getPlayer(@PathVariable Integer id){
        PlayerDto playerDto = playerService.getPlayerByGameId(id);
        if (playerDto != null) {

            return ResponseEntity.ok().body(playerDto);
        }
        return ResponseEntity.badRequest().body("Player can not be found");
    }
    @PostMapping("/get/{id}")
    public Game findGameByID(@PathVariable("id") Integer id) {
        log.info("find player by id controller");
        return gameService.findGameByID(id);
    }



    @PostMapping("/{id}/collect-items")
    public ResponseEntity collectDungeonItems(@PathVariable Integer id)
    {
        List<ItemsDto> itemsDto = playerService.collectItemsFromDungeon(id);
        if(itemsDto != null){

            return ResponseEntity.ok().body(itemsDto);
        }
        return ResponseEntity.badRequest().body("Please finish the dungeon first");
    }

}
