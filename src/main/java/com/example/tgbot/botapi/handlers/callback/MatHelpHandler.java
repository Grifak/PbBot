package com.example.tgbot.botapi.handlers.callback;

import com.example.tgbot.botapi.handlers.callback.CallbackQueryHandler;
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
public class MatHelpHandler implements CallbackQueryHandler {
    private final UserDataCache userDataCache;
    private final ReplyMessageService replyMessageService;

    @Override
    public BotApiMethod<?> handle(Message message) {
        Long chatId = message.getChatId();
        Long userId = message.getFrom().getId();
        userDataCache.setUsersCurrentBotState(userId, BotState.DOC_MAT_HELP);

        SendMessage replyMessage = replyMessageService.getReplyMessage(chatId, ReplyMessage.DOC_MAT_HELP.getName());

        return replyMessage;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.DOC_MAT_HELP;
    }
}
