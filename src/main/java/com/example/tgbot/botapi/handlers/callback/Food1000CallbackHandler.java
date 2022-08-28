package com.example.tgbot.botapi.handlers.callback;

import com.example.tgbot.cache.impl.UserDataCache;
import com.example.tgbot.enums.BotState;
import com.example.tgbot.enums.ReplyMessage;
import com.example.tgbot.service.ReplyMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@RequiredArgsConstructor
public class Food1000CallbackHandler implements CallbackQueryHandler{
    private final ReplyMessageService replyMessageService;
    private final UserDataCache userDataCache;

    @Override
    public BotApiMethod<?> handle(Message message) {
        Long chatId = message.getChatId();
        Long userId = message.getFrom().getId();
        userDataCache.setUsersCurrentBotState(userId, BotState.FOOD_1000);

        SendMessage replyMessage = replyMessageService.getReplyMessage(chatId, ReplyMessage.FOOD_1000.getName());

        return replyMessage;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.FOOD_1000;
    }
}
