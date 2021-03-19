package com.codecta.gameservice.service;


import com.codecta.gameservice.dto.*;
import com.codecta.gameservice.entity.*;
import com.codecta.gameservice.repository.*;
import com.codecta.gameservice.shared.Fight;
import com.codecta.gameservice.shared.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PlayerService {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private DungeonRepository dungeonRepository;
    @Autowired
    private MonsterRepository monsterRepository;
    @Autowired
    private WeaponRepository weaponRepository;
    @Autowired
    private InventoryItemsRepository inventoryItemsRepository;
    @Autowired
    private ItemsRepository itemsRepository;


    public MovedPlayerDto movePlayer(Integer id) {
        MovedPlayerDto movePlayerDto = new MovedPlayerDto();
        Game game = gameRepository.findGameByGameId(id);
        Dungeon currentDungeon = game.getPlayer().getCurrentDungeon();
        System.out.println("Dungeon id");
        System.out.println(currentDungeon.getId());
        // cant move if the player is on the last dungeon
        if (currentDungeon.getId().equals(10)) return null;
        List<Monster> monsters = monsterRepository.findAllByAliveTrueAndDungeon(currentDungeon);

        if (monsters.size() == 0) {

            //get next dungeon
            currentDungeon = dungeonRepository.getDungeonById(currentDungeon.getId() + 1);
            DungeonDto dungeonDto = Mapper.modelMapper.map(currentDungeon, DungeonDto.class);
            System.out.println(dungeonDto.getId());
            movePlayerDto.setMonsters(dungeonDto.getMonsters());
            movePlayerDto.setItems(dungeonDto.getItems());
            movePlayerDto.setDungeonId(dungeonDto.getId());
            game.getPlayer().setCurrentDungeon(currentDungeon);
            playerRepository.save(game.getPlayer());
            return movePlayerDto;

        } else return null;

    }

    public FightDto fightWithMonster(Integer gameId, AttackDto attackDto) {

        Double score = 0.0;
        //get dungeon by id
        System.out.println("kaskdkaskdskakas");
        Game currentGame = gameRepository.findGameByGameId(gameId);

        Dungeon currentDungeon = currentGame.getPlayer().getCurrentDungeon();

        //
        Monster attackMonster = monsterRepository.getOne(attackDto.getMonsterID());


        Player currentPlayer = gameRepository.findGameByGameId(gameId).getPlayer();
        Weapon currentWeapon = weaponRepository.getOne(attackDto.getWeaponID());
        currentPlayer.setWeapon(currentWeapon);


        Integer weaponHealth = currentPlayer.getWeapon().getWeaponHealth();
        //fight
        System.out.println(currentPlayer.getName());
        while (currentPlayer.getHealth() > 0.0 && attackMonster.getHealth() > 0.0) {
            currentGame.setScore(currentGame.getScore() + attackMonster.getHealth());
            attackMonster.setHealth((attackMonster.getHealth() - currentPlayer.getWeapon().getDamage() * randomDmgForAttack()) * currentPlayer.getPowerBoost());
            weaponHealth--;
            if (attackMonster.getHealth() > 0)
                currentPlayer.setHealth(currentPlayer.getHealth() - attackMonster.getDamage() * randomDmgForAttack());

        }
        // set score rounded to 2 decimals
        currentGame.setScore(Math.round(currentGame.getScore() * 100d) / 100d);
        currentPlayer.setHealth(Math.round(currentPlayer.getHealth() * 100d) / 100d);
        currentWeapon.setWeaponHealth(weaponHealth);
        weaponRepository.save(currentWeapon);
        FightDto fightResponseDto = new FightDto();

        //player win
        if (currentPlayer.getHealth() > 0) {
            attackMonster.setAlive(false);

            //generate fight response
            fightResponseDto = new FightDto(
                    fightResponseDto.setMessage(Fight.FIGHT_WIN),
                    Math.round(currentPlayer.getHealth() * 100d) / 100d,
                    attackMonster.getName(),
                    true);


            //if there is not any monster dungeon is finished
            if (monsterRepository.findAllByAliveTrueAndDungeon(currentDungeon) == null) {
                System.out.println("-----------ZAVRSENO---------------");
                currentDungeon.setFinished(true);
            }
            dungeonRepository.save(currentDungeon);
            monsterRepository.save(attackMonster);
            playerRepository.save(currentPlayer);
        }
        // player lose
        else if (attackMonster.getHealth() > 0 && currentPlayer.getHealth() < 0) {
            fightResponseDto = new FightDto(
                    fightResponseDto.setMessage(Fight.FIGHT_LOSE),
                    0.0,

                    null,
                    false);
        }
        //last dungeon
        if (currentDungeon.getId().equals(10) && currentPlayer.getHealth() > 0) {
            boolean finished = true;
            for (int i = 0; i < currentDungeon.getMonsters().size(); i++) {
                Monster monster = currentDungeon.getMonsters().get(i);
                if (monster.getAlive()) finished = false;
            }
            if (finished == true) {
                fightResponseDto.setMessage(Fight.FIGHT_WIN);
            }
        }
        return fightResponseDto;


    }

    public Double randomDmgForAttack() {
        Double maxDmg = 6.0;
        Double minDmg = 1.0;
        return (Math.random() * maxDmg + minDmg) / 5.0;
    }

    public PlayerDto getPlayerByGameId(Integer id) {
        Game game = gameRepository.findGameByGameId(id);
        return Mapper.modelMapper.map(game.getPlayer(), PlayerDto.class);
    }

    public List<ItemsDto> collectItemsFromDungeon(Integer id) {

        Game game = gameRepository.getOne(id);

        List<ItemsDto> itemsDtos = new ArrayList<>();
        if (game.getPlayer().getCurrentDungeon().getFinished()) {
            for (Items item : game.getPlayer().getCurrentDungeon().getItems()) {
                InventoryItemsPk inventoryItemsPk = new InventoryItemsPk();
                inventoryItemsPk.setInventory(game.getPlayer().getPlayerInventory().getId());
                inventoryItemsPk.setItems(item.getId());
                Optional<InventoryItems> inventoryItems = inventoryItemsRepository.findById(inventoryItemsPk);
                if (inventoryItems.isPresent()) {
                    inventoryItems.get().setQuantity(inventoryItems.get().getQuantity() + 1);

                    inventoryItemsRepository.save(inventoryItems.get());
                } else {
                    InventoryItems newInventoryItems = new InventoryItems();
                    newInventoryItems.setItems(item);
                    newInventoryItems.setInventory(game.getPlayer().getPlayerInventory());
                    newInventoryItems.setQuantity(1);

                    inventoryItemsRepository.save(newInventoryItems);
                }

                itemsDtos.add(Mapper.modelMapper.map(item, ItemsDto.class));
            }
            return itemsDtos;
        } else {
            return null;
        }


    }
}
