package com.example.boat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.boat.repository")
public class BoatApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoatApplication.class, args);
	}

}
