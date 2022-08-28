package com.example.tgbot.botapi;

import com.example.tgbot.botapi.handlers.InputMessageHandler;
import com.example.tgbot.botapi.handlers.callback.CallbackQueryHandler;
import com.example.tgbot.enums.BotState;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class BotStateContext {
    private Map<BotState, InputMessageHandler> messageHandlers = new HashMap<>();
    private Map<BotState, CallbackQueryHandler> callbackQueryHandlers = new HashMap<>();

    public BotStateContext(List<InputMessageHandler> messageHandlers, List<CallbackQueryHandler> callbackQueryHandlers) {
        messageHandlers.forEach(handler -> this.messageHandlers.put(handler.getHandlerName(), handler));
        callbackQueryHandlers.forEach(handler -> this.callbackQueryHandlers.put(handler.getHandlerName(), handler));
    }

    public BotApiMethod<?> processInputMessage(BotState currentState, Message message) {
        InputMessageHandler currentMessageHandler = findMessageHandler(currentState);
        return currentMessageHandler.handle(message);
    }

    public BotApiMethod<?> processCallbackQuery(BotState currentState, Message message){
        CallbackQueryHandler callbackQueryHandler = findCallbackHandler(currentState);
        return callbackQueryHandler.handle(message);
    }

    private CallbackQueryHandler findCallbackHandler(BotState currentState) {
        return callbackQueryHandlers.get(currentState);
    }

    private InputMessageHandler findMessageHandler(BotState currentState) {
        return messageHandlers.get(currentState);
    }
}
