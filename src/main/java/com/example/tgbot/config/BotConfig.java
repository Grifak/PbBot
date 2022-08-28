package com.example.tgbot.config;

import com.example.tgbot.PbBot;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("telegram")
public class BotConfig {
    private String webHookPath;
    private String botUserName;
    private String botToken;

    @Bean
    public PbBot pbBot() {

        PbBot bot = new PbBot();
        bot.setBotUserName(botUserName);
        bot.setBotToken(botToken);
        bot.setWebHookPath(webHookPath);

        return bot;
    }
}
