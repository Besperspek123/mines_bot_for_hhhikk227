package com.example.tgbot.interfaces;

import com.example.tgbot.entity.User;

public interface CommandWithUser {
    void execute(long chatId, User user);
}
