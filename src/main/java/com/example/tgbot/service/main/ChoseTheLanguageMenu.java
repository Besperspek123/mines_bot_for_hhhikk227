package com.example.tgbot.service.main;


import com.example.tgbot.interfaces.BotActions;
import com.example.tgbot.interfaces.CommandWithoutCallback;
import com.example.tgbot.keyboard.StartKeyboard;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;


@Slf4j
public class ChoseTheLanguageMenu implements CommandWithoutCallback {
    private final BotActions botActions;

    public ChoseTheLanguageMenu(BotActions botActions) {
        this.botActions = botActions;
    }
    @Override
    public void execute(long chatId) {
        String answer ="\uD83D\uDE80 Выберите язык / Choose a language \uD83D\uDDE8\uD83D\uDC65";
        InlineKeyboardMarkup inlineKeyboardMarkup = StartKeyboard.showLanguageMenu();
        botActions.sendMessageWithInlineKeyboardAndParseMARKDOWN(chatId,answer,inlineKeyboardMarkup);


    }
}