package com.example.boat.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration @EnableAutoConfiguration
@ComponentScan("com.example.boat.mapper")
public class AppConfig {
}
