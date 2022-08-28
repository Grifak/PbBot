package com.example.tgbot;

import com.example.tgbot.config.BotConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties({BotConfig.class})
@EnableScheduling
public class TgBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(TgBotApplication.class, args);
    }

}
