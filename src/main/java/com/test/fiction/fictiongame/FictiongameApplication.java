package com.test.fiction.fictiongame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({"classpath:application.properties", 
	"classpath:persistence.properties" })
public class FictiongameApplication {

	public static void main(String[] args) {
		SpringApplication.run(FictiongameApplication.class, args);
	}
}
