package com.example.tgbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
@RequiredArgsConstructor
public class ReplyMessageService {
    public SendMessage getReplyMessage(Long chatId, String replyMessage) {
        return new SendMessage(chatId.toString(), replyMessage);
    }
}
