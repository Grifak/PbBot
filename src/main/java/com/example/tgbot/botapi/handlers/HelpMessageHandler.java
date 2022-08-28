package com.example.tgbot.botapi.handlers;

import com.example.tgbot.cache.impl.UserDataCache;
import com.example.tgbot.enums.BotState;
import com.example.tgbot.enums.ReplyMessage;
import com.example.tgbot.service.ReplyMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@RequiredArgsConstructor
public class HelpMessageHandler implements InputMessageHandler {
    private final ReplyMessageService replyMessageService;
    private final UserDataCache userDataCache;

    @Override
    public BotApiMethod<?> handle(Message message) {
        Long chatId = message.getChatId();
        @lombok.NonNull Long userId = message.getFrom().getId();
        userDataCache.setUsersCurrentBotState(userId, BotState.HELP_MENU);

        return replyMessageService.getReplyMessage(chatId, ReplyMessage.HELP.getName());
    }

    @Override
    public BotState getHandlerName() {
        return BotState.HELP_MENU;
    }
}
