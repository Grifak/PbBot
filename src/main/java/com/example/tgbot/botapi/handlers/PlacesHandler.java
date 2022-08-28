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
public class PlacesHandler implements InputMessageHandler {
    private final ReplyMessageService replyMessageService;
    private final UserDataCache userDataCache;

    @Override
    public BotApiMethod<?> handle(Message message) {
        Long chatId = message.getChatId();
        @lombok.NonNull Long userId = message.getFrom().getId();
        userDataCache.setUsersCurrentBotState(userId, BotState.PLACES);

        SendMessage replyMessage = replyMessageService.getReplyMessage(chatId, ReplyMessage.PLACES.getName());
        replyMessage.setReplyMarkup(getInlineMessageButtons());

        return replyMessage;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.PLACES;
    }

    private InlineKeyboardMarkup getInlineMessageButtons() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton food = InlineKeyboardButton.builder()
                .text("Еда")
                .callbackData("Food")
                .build();
        InlineKeyboardButton entertainments = InlineKeyboardButton.builder()
                .text("Развлечения")
                .callbackData("Entertainments")
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
