package com.codecta.gameservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameServiceApplication.class, args);
	}

}

	//docker run --ulimit memlock=-1:-1 -it --rm=true --memory-swappiness=0 --name game-service -e POSTGRES_USER=ahmed -e POSTGRES_PASSWORD=123456 -e POSTGRES_DB=game-service -p 5433:5433 postgres:10.5