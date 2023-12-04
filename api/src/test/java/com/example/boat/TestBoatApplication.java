package com.example.boat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestBoatApplication {

	public static void main(String[] args) {
		SpringApplication.from(BoatApplication::main).with(TestBoatApplication.class).run(args);
	}

}
