package com.example.tgbot.interfaces;

import com.example.tgbot.entity.User;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

public interface CommandWithBukmekerInfo {
    void execute(long chatId, String bukmekerName, String bukmekerUrl, String bonusInfo, CallbackQuery callbackQuery);
}
