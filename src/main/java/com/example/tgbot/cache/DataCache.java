package com.example.tgbot.cache;

import com.example.tgbot.enums.BotState;

public interface DataCache {
    void setUsersCurrentBotState(@lombok.NonNull Long userId, BotState botState);

    BotState getUsersCurrentBotState(@lombok.NonNull Long userId);
}
