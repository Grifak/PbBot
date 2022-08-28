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
public class LifeHacksHandler implements InputMessageHandler {
    private final ReplyMessageService replyMessageService;
    private final UserDataCache userDataCache;

    @Override
    public BotApiMethod<?> handle(Message message) {
        Long chatId = message.getChatId();
        Long userId = message.getFrom().getId();
        userDataCache.setUsersCurrentBotState(userId, BotState.LIFE_HACKS);

        SendMessage sendMessage = replyMessageService.getReplyMessage(chatId, ReplyMessage.LIFE_HACKS.getName());
        sendMessage.setReplyMarkup(getInlineMessageButtons());

        return sendMessage;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.LIFE_HACKS;
    }

    private InlineKeyboardMarkup getInlineMessageButtons() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton lf1 = InlineKeyboardButton.builder()
                .text("1")
                .callbackData("LifeHacks1")
                .build();
        InlineKeyboardButton lf2 = InlineKeyboardButton.builder()
                .text("2")
                .callbackData("LifeHacks2")
                .build();
        InlineKeyboardButton lf3 = InlineKeyboardButton.builder()
                .text("3")
                .callbackData("LifeHacks3")
                .build();
        InlineKeyboardButton lf4 = InlineKeyboardButton.builder()
                .text("4")
                .callbackData("LifeHacks4")
                .build();
        InlineKeyboardButton lf5 = InlineKeyboardButton.builder()
                .text("5")
                .callbackData("LifeHacks5")
                .build();
        InlineKeyboardButton lf6 = InlineKeyboardButton.builder()
                .text("6")
                .callbackData("LifeHacks6")
                .build();

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(lf1);
        keyboardButtonsRow1.add(lf2);
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(lf3);
        keyboardButtonsRow2.add(lf4);
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        keyboardButtonsRow3.add(lf5);
        keyboardButtonsRow3.add(lf6);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }
}
