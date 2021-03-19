insert into dungeon(id,finished,name) values (1,FALSE,'Dungeon1');
insert into dungeon(id,finished,name) values (2,FALSE,'Dungeon2');


insert into monster(id,alive,damage,health,name,dungeon_id) values (1,1,10.0,100.0,'Monster1',1);
insert into monster(id,alive,damage,health,name,dungeon_id) values (2,1,10.0,100.0,'Monster2',1);
insert into monster(id,alive,damage,health,name,dungeon_id) values (3,1,10.0,100.0,'Monster3',1);

insert into monster(id,alive,damage,health,name,dungeon_id) values (4,1,10.0,100.0,'Monster1',2);
insert into monster(id,alive,damage,health,name,dungeon_id) values (5,1,10.0,100.0,'Monster2',2);
insert into monster(id,alive,damage,health,name,dungeon_id) values (6,1,10.0,100.0,'Monster3',2);

insert into weapon(id, damage, weapon_health, weapon_name) values (1,10.0,100.0, 'Knife');


insert into items(id,name) values (1,'Potion of regeneration');
insert into power_ups(parent_id,power_value) values (1,1.1);

insert into items(id,name) values (2,'Potion of health regeneration');
insert into healing_potion(parent_id,health_addition) values (2,20.0);

insert into dungeon_items(dungeons_id, items_id) values (1,1);

insert into dungeon_items(dungeons_id, items_id) values (2,2);