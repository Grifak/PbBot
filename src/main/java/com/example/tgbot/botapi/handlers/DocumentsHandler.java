package com.example.tgbot.botapi.handlers;

import com.example.tgbot.cache.impl.UserDataCache;
import com.example.tgbot.enums.BotState;
import com.example.tgbot.enums.ReplyMessage;
import com.example.tgbot.service.ReplyMessageService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Component
@RequiredArgsConstructor
public class DocumentsHandler implements InputMessageHandler {
    private final ReplyMessageService replyMessageService;
    private final UserDataCache userDataCache;

    @Override
    public BotApiMethod<?> handle(Message message) {
        Long chatId = message.getChatId();
        @lombok.NonNull Long userId = message.getFrom().getId();
        userDataCache.setUsersCurrentBotState(userId, BotState.DOCUMENTS);

        SendMessage sendMessage = replyMessageService.getReplyMessage(chatId, ReplyMessage.DOCUMENTS.getName());
        sendMessage.setReplyMarkup(getInlineMessageButtons());

        return sendMessage;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.DOCUMENTS;
    }

    private InlineKeyboardMarkup getInlineMessageButtons() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton food = InlineKeyboardButton.builder()
                .text("Мат помощь")
                .callbackData("MatHelp")
                .build();
        InlineKeyboardButton entertainments = InlineKeyboardButton.builder()
                .text("Соц стипендия")
                .callbackData("GSS")
                .build();

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(food);
        keyboardButtonsRow1.add(entertainments);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }
}
