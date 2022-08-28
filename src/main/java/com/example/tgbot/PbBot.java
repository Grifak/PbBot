package com.example.tgbot;

import com.example.tgbot.botapi.TelegramFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;


public class PbBot extends TelegramWebhookBot {
    private String webHookPath;
    private String botUserName;
    private String botToken;

    @Autowired
    private TelegramFacade telegramFacade;

    public PbBot() {
        super();
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotPath() {
        return webHookPath;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        BotApiMethod<?> replyMessage = telegramFacade.handleUpdate(update);
        return replyMessage;
    }


    public void setWebHookPath(String webHookPath) {
        this.webHookPath = webHookPath;
    }

    public void setBotUserName(String botUserName) {
        this.botUserName = botUserName;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }
}
