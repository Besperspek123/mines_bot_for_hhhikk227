package com.example.tgbot.interfaces;

import com.example.tgbot.entity.User;

public interface CommandWithChatId {
    void execute(long chatId);
}
