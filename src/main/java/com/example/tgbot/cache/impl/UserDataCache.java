package com.example.tgbot.cache.impl;

import com.example.tgbot.cache.DataCache;
import com.example.tgbot.enums.BotState;
import java.util.HashMap;
import java.util.Map;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class UserDataCache implements DataCache {
    private Map<@NonNull Long, BotState> usersBotStates = new HashMap<>();

    @Override
    public void setUsersCurrentBotState(@NonNull Long userId, BotState botState) {
        usersBotStates.put(userId, botState);
    }

    @Override
    public BotState getUsersCurrentBotState(@lombok.NonNull Long userId) {
        BotState botState = usersBotStates.get(userId);
        if (botState == null) {
            botState = BotState.MAIN_MENU;
        }

        return botState;
    }
}
