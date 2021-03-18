package com.codecta.gameservice.service;

import com.codecta.gameservice.dto.PlayerDto;
import com.codecta.gameservice.entity.EmailResponse;
import com.codecta.gameservice.entity.Game;
import com.codecta.gameservice.entity.Player;
import com.codecta.gameservice.repository.GameRepository;
import com.codecta.gameservice.repository.PlayerRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GameService {

    public static final String ORDER_CREATED_ROUTING_KEY = "player.created";
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private Exchange eventExchange;

    private ModelMapper modelMapper = new ModelMapper();

    public Game createGame(PlayerDto player) {
        log.info("inside save player service");
        Player savedPlayer = playerRepository.save(modelMapper.map(player, Player.class));
        Game game1 =  new Game();
        game1.setScore(0.0);
        game1.setPlayer(savedPlayer);
        gameRepository.save(game1);
        EmailResponse emailResponse = new EmailResponse();
        emailResponse.setEmail(player.getEmail());
        emailResponse.setScore("0.0");
        emailResponse.setContent("Welcome to QoQ");
        rabbitTemplate.convertAndSend(eventExchange.getName(),ORDER_CREATED_ROUTING_KEY, emailResponse);
        return game1;
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
