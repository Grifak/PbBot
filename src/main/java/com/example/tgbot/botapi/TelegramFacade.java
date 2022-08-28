package com.example.tgbot.botapi;

import com.example.tgbot.cache.impl.UserDataCache;
import com.example.tgbot.enums.BotState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Slf4j
@RequiredArgsConstructor
public class TelegramFacade {
    private final BotStateContext botStateContext;
    private final UserDataCache userDataCache;

    public BotApiMethod<?> handleUpdate(Update update) {
        BotApiMethod<?> replyMessage = null;
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            log.info("New callbackQuery from User: {}, userId: {}, with data: {}", update.getCallbackQuery().getFrom().getUserName(),
                    callbackQuery.getFrom().getId(), update.getCallbackQuery().getData());
            return handleCallbackQuery(callbackQuery);
        }

        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            log.info("New message from User:{}, chatId: {},  with text: {}",
                    message.getFrom().getUserName(), message.getChatId(), message.getText());
            replyMessage = handleInputMessage(message);
        }

        return replyMessage;
    }

    private BotApiMethod<?> handleInputMessage(Message message) {
        String inputMsg = message.getText();
        Long userId = message.getFrom().getId();
        BotState botState;
        BotApiMethod<?> replyMessage;

        switch (inputMsg) {
            case "/start":
                botState = BotState.HELLO;
                break;
            case "Помощь ❓":
                botState = BotState.HELP_MENU;
                break;
            case "\uD83D\uDCA1":
                botState = BotState.LIFE_HACKS;
                break;
            case "\uD83E\uDD13":
                botState = BotState.DOCUMENTS;
                break;
            case "\uD83E\uDD73":
                botState = BotState.PLACES;
                break;
//            case "\uD83C\uDFC0":
//                botState = BotState.SPORT;
//                break;
            case "\uD83C\uDFB2":
                botState = BotState.ANOTHER_ACTIVITIES;
                break;
            default:
                botState = userDataCache.getUsersCurrentBotState(userId);
                break;
        }

        userDataCache.setUsersCurrentBotState(userId, botState);

        replyMessage = botStateContext.processInputMessage(botState, message);

        return replyMessage;
    }

    private BotApiMethod<?> handleCallbackQuery(CallbackQuery callbackQuery) {
        Message message = callbackQuery.getMessage();
        Long userId = message.getFrom().getId();
        BotState botState;
        BotApiMethod<?> replyMessage;

        switch (callbackQuery.getData()){
            case "Food":
                botState = BotState.FOOD_PLACES;
                break;
            case "Entertainments":
                botState = BotState.ENTERTAINMENTS_PLACES;
                break;
            case "Food300":
                botState = BotState.FOOD_300;
                break;
            case "Food500":
                botState = BotState.FOOD_500;
                break;
            case "Food1000":
                botState = BotState.FOOD_1000;
                break;
            case "Entertainment500":
                botState = BotState.ENTERTAINMENTS_500;
                break;
            case "Entertainment1000":
                botState = BotState.ENTERTAINMENTS_1000;
                break;
            case "LifeHacks1":
                botState = BotState.LIFE_HACKS_1;
                break;
            case "LifeHacks2":
                botState = BotState.LIFE_HACKS_2;
                break;
            case "LifeHacks3":
                botState = BotState.LIFE_HACKS_3;
                break;
            case "LifeHacks4":
                botState = BotState.LIFE_HACKS_4;
                break;
            case "LifeHacks5":
                botState = BotState.LIFE_HACKS_5;
                break;
            case "LifeHacks6":
                botState = BotState.LIFE_HACKS_6;
                break;
            case "MatHelp":
                botState = BotState.DOC_MAT_HELP;
                break;
            case "GSS":
                botState = BotState.DOC_GSS;
                break;
            case "Studios":
                botState = BotState.STUDIOS;
                break;
            case "PROF":
                botState = BotState.PROF;
                break;
            default:
                botState = userDataCache.getUsersCurrentBotState(userId);
                break;
        }

        userDataCache.setUsersCurrentBotState(userId, botState);

        replyMessage = botStateContext.processCallbackQuery(botState, message);

        return replyMessage;
    }
}
