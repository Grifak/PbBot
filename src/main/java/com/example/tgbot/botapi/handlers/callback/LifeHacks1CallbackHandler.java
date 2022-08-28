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

@RequiredArgsConstructor
@Component
public class LifeHacks1CallbackHandler implements CallbackQueryHandler{
    private final UserDataCache userDataCache;
    private final ReplyMessageService replyMessageService;

    @Override
    public BotApiMethod<?> handle(Message message) {
        Long chatId = message.getChatId();
        Long userId = message.getFrom().getId();
        userDataCache.setUsersCurrentBotState(userId, BotState.LIFE_HACKS_1);

        SendMessage replyMessage = replyMessageService.getReplyMessage(chatId, ReplyMessage.LIFE_HACKS_1.getName());

        return replyMessage;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.LIFE_HACKS_1;
    }
}
