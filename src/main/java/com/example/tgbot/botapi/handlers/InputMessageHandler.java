package com.example.tgbot.botapi.handlers;

import com.example.tgbot.enums.BotState;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface InputMessageHandler {
    BotApiMethod<?> handle(Message message);

    BotState getHandlerName();
}
