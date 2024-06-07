package com.example.tgbot.interfaces;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

public interface CommandWithCallback {
    void execute(long chatId, CallbackQuery callbackQuery);
}
