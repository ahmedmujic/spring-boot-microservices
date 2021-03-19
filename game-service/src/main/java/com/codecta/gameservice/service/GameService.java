package com.codecta.gameservice.service;

import com.codecta.gameservice.dto.GameDto;
import com.codecta.gameservice.dto.PlayerDto;
import com.codecta.gameservice.entity.*;
import com.codecta.gameservice.repository.*;
import com.codecta.gameservice.shared.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
@Slf4j
public class GameService {

    public static final String ORDER_CREATED_ROUTING_KEY = "player.created";
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private DungeonRepository dungeonRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private Exchange eventExchange;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private WeaponRepository weaponRepository;

    public GameDto createGame(PlayerDto player) {



        Player playerEntity = Mapper.modelMapper.map(player, Player.class);
        playerEntity.setCurrentDungeon(dungeonRepository.getDungeonById(1));
        playerEntity.setHealth(1000.0);
        playerEntity.setPowerBoost(1.0);

        Inventory inventory = new Inventory();
        inventory.getPlayers().add(playerEntity);
        inventoryRepository.save(inventory);
        playerEntity.setPlayerInventory(inventory);
        Player savedPlayer = playerRepository.save(playerEntity);
        Game game1 =  new Game();
        game1.setScore(0.0);
        game1.setPlayer(savedPlayer);
        gameRepository.save(game1);

        EmailResponse emailResponse = new EmailResponse();
        emailResponse.setEmail(player.getEmail());
        emailResponse.setScore("Your current score is: 0.0");
        emailResponse.setContent("Welcome to QoQ");

       rabbitTemplate.convertAndSend(eventExchange.getName(),ORDER_CREATED_ROUTING_KEY, emailResponse);
        return Mapper.modelMapper.map(game1, GameDto.class);
    }

    public String sendGameResult(Integer gameId, String email){
        Game game = gameRepository.findGameByGameId(gameId);
        EmailResponse emailResponse = new EmailResponse();
        emailResponse.setEmail(email);
        emailResponse.setScore(game.getScore().toString());
        try{
            rabbitTemplate.convertAndSend(eventExchange.getName(),ORDER_CREATED_ROUTING_KEY, emailResponse);
        }catch (Exception e ){
            System.out.println(e);
        }
        return "uspjeh";


    }
    public Game findGameByID(Integer id) {

        log.info("inside find player service");
        return gameRepository.findGameByGameId(id);
    }
}
