package com.example.tgbot.service.main;

import com.example.tgbot.entity.User;
import com.example.tgbot.interfaces.BotActions;
import com.example.tgbot.interfaces.CommandWithCallback;
import com.example.tgbot.keyboard.StartKeyboard;
import com.example.tgbot.service.DepositService;
import com.example.tgbot.service.PromoService;
import com.example.tgbot.service.UrlService;
import com.example.tgbot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;


@Slf4j
public class ChooseGamesMenu implements CommandWithCallback {
    private UrlService urlService;
    private final BotActions botActions;
    private final UserService userService;
    private final PromoService promoService;
    private final DepositService depositService;

    public ChooseGamesMenu(BotActions botActions, UrlService urlService, UserService userService, PromoService promoService, DepositService depositService) {
        this.botActions = botActions;
        this.urlService = urlService;
        this.userService = userService;
        this.promoService = promoService;
        this.depositService= depositService;
    }
    @Override
    public void execute(long chatId, CallbackQuery callbackQuery) {
        User currentUser = userService.getInfoAboutUserByChatID(chatId);
        String message = "";
        if (userService.userIsRussian(currentUser))
        {
            message = "Выберите игру \uD83C\uDFAE";
        } else {
            message = "Choose a game \uD83C\uDFAE";
        }

        InlineKeyboardMarkup keyboardWithGameButtons = StartKeyboard.sendGameSelectionMenu();
        botActions.sendMessageWithPhotoAndKeyboard(chatId,message,"/images/games.jpg",keyboardWithGameButtons);
        botActions.handleCallbackQuery(callbackQuery);
    }

}