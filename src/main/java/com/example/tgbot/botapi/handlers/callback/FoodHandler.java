package com.example.tgbot.botapi.handlers.callback;

import com.example.tgbot.botapi.handlers.callback.CallbackQueryHandler;
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

@RequiredArgsConstructor
@Component
public class FoodHandler implements CallbackQueryHandler {
    private final ReplyMessageService replyMessageService;
    private final UserDataCache userDataCache;

    @Override
    public BotApiMethod<?> handle(Message message) {
        Long chatId = message.getChatId();
        Long userId = message.getFrom().getId();
        userDataCache.setUsersCurrentBotState(userId, BotState.FOOD_PLACES);

        SendMessage replyMessage = replyMessageService.getReplyMessage(chatId, ReplyMessage.PLACES_COST.getName());
        replyMessage.setReplyMarkup(getInlineMessageButtons());

        return replyMessage;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.FOOD_PLACES;
    }

    private InlineKeyboardMarkup getInlineMessageButtons() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton threeHundred = InlineKeyboardButton.builder()
                .text("до 300")
                .callbackData("Food300")
                .build();
        InlineKeyboardButton fiveHundred = InlineKeyboardButton.builder()
                .text("до 500")
                .callbackData("Food500")
                .build();
        InlineKeyboardButton thousand = InlineKeyboardButton.builder()
                .text("до 1000")
                .callbackData("Food1000")
                .build();

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(threeHundred);
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow1.add(fiveHundred);
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        keyboardButtonsRow1.add(thousand);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }
}
