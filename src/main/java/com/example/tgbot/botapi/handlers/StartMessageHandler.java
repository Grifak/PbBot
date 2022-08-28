package com.example.tgbot.botapi.handlers;

import com.example.tgbot.cache.impl.UserDataCache;
import com.example.tgbot.enums.BotState;
import com.example.tgbot.enums.ReplyMessage;
import com.example.tgbot.service.MainMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@RequiredArgsConstructor
public class StartMessageHandler implements InputMessageHandler {
    private final UserDataCache userDataCache;
    private final MainMenuService mainMenuService;

    @Override
    public BotApiMethod<?> handle(Message message) {
        Long userId = message.getFrom().getId();
        Long chatId = message.getChatId();

        BotApiMethod<?> callBackAnswer = mainMenuService.getMainMenuMessage(chatId, ReplyMessage.MAIN_MENU.getName());
        userDataCache.setUsersCurrentBotState(userId, BotState.MAIN_MENU);

        return callBackAnswer;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.HELLO;
    }
}
