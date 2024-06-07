package com.example.tgbot.interfaces;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

public interface CommandWithoutCallback {
    void execute(long chatId);
}
