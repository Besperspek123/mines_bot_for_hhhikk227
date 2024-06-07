package com.example.tgbot.interfaces;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

public interface CommandWithFirstNameAndCallBack {
    void execute(long chatId, String firstName, CallbackQuery callbackQuery);
}
