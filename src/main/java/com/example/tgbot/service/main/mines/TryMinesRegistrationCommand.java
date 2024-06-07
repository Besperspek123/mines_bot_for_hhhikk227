package com.example.tgbot.service.main.mines;

import com.example.tgbot.entity.User;
import com.example.tgbot.interfaces.BotActions;
import com.example.tgbot.interfaces.CommandWithCallback;
import com.example.tgbot.interfaces.CommandWithoutCallback;
import com.example.tgbot.keyboard.games.MinesKeyboard;
import com.example.tgbot.service.RegistrationService;
import com.example.tgbot.service.UrlService;
import com.example.tgbot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;


@Slf4j
public class TryMinesRegistrationCommand implements CommandWithoutCallback {
    private final BotActions botActions;
    private final UserService userService;
    private final String oneWinId;

    private final UrlService urlService;

    private final RegistrationService registrationService;

    public TryMinesRegistrationCommand(BotActions botActions, UserService userService, String oneWinId, RegistrationService registrationService, UrlService urlService) {
        this.botActions = botActions;
        this.userService = userService;
        this.oneWinId = oneWinId;
        this.registrationService = registrationService;
        this.urlService = urlService;
    }
    @Override
    public void execute(long chatId) {
        User user = userService.getInfoAboutUserByChatID(chatId);

        if (oneWinId.matches("^\\d{8}$") && user.getAwaitingMode() && registrationService.isPlayerRegistered(oneWinId)) {
            if (userService.isUserExistByOneWinId(Long.parseLong(oneWinId))) {
                InlineKeyboardMarkup inlineKeyboardMarkup = MinesKeyboard.menuWithCancelLinkForCheckID(user.getLanguage(), urlService);

                if (userService.userIsRussian(user)) {
                    botActions.sendMessageWithInlineKeyboard(chatId, "Вы ввели чужой id!\nПожалуйста, попробуйте еще раз.", inlineKeyboardMarkup);
                } else if (userService.userIsEnglish(user)) {
                    botActions.sendMessageWithInlineKeyboard(chatId, "You've entered someone else's ID!\nPlease, try again.", inlineKeyboardMarkup);
                } else if (userService.userIsHindi(user)) {
                    botActions.sendMessageWithInlineKeyboard(chatId, "आपने किसी और का आईडी डाला है!\nकृपया पुनः प्रयास करें।", inlineKeyboardMarkup);
                } else if (userService.userIsBrazilian(user)) {
                    botActions.sendMessageWithInlineKeyboard(chatId, "Você inseriu o ID de outra pessoa!\nPor favor, tente novamente.", inlineKeyboardMarkup);
                } else if (userService.userIsSpanish(user)) {
                    botActions.sendMessageWithInlineKeyboard(chatId, "¡Has introducido el ID de otra persona!\nPor favor, inténtalo de nuevo.", inlineKeyboardMarkup);
                } else if (userService.userIsUzbek(user)) {
                    botActions.sendMessageWithInlineKeyboard(chatId, "Siz begona ID kiritdingiz!\nIltimos, yana bir bor urinib ko'ring.", inlineKeyboardMarkup);
                } else if (userService.userIsAzerbaijani(user)) {
                    botActions.sendMessageWithInlineKeyboard(chatId, "Siz başqa birinin ID-sini daxil etdiniz!\nZəhmət olmasa, bir daha cəhd edin.", inlineKeyboardMarkup);
                } else if (userService.userIsTurkish(user)) {
                    botActions.sendMessageWithInlineKeyboard(chatId, "Başkasının kimliğini girdiniz!\nLütfen tekrar deneyin.", inlineKeyboardMarkup);
                } else if (userService.userIsPortuguese(user)) {
                    botActions.sendMessageWithInlineKeyboard(chatId, "Você inseriu o ID de outra pessoa!\nPor favor, tente novamente.", inlineKeyboardMarkup);
                } else if (userService.userIsArabic(user)) {
                    botActions.sendMessageWithInlineKeyboard(chatId, "لقد أدخلت معرف شخص آخر!\nيرجى المحاولة مرة أخرى.", inlineKeyboardMarkup);
                } else {
                    // Default to English if language is not recognized
                    botActions.sendMessageWithInlineKeyboard(chatId, "You've entered someone else's ID!\nPlease, try again.", inlineKeyboardMarkup);
                }
            } else {
                user.setIsVerify(true);
                user.setAwaitingMode(false);
                user.setOneWinId(Long.valueOf(oneWinId));
                userService.saveUser(user);
                userService.setVerifyIsTrue(chatId);
                userService.setDefaultAwaitingMode(chatId);

                if (userService.userIsRussian(user)) {
                    botActions.sendMessageWithoutCallbackQuery(chatId, "✅ Успешная регистрация!");
                } else if (userService.userIsEnglish(user)) {
                    botActions.sendMessageWithoutCallbackQuery(chatId, "✅ Registration successful!");
                } else if (userService.userIsHindi(user)) {
                    botActions.sendMessageWithoutCallbackQuery(chatId, "✅ सफल पंजीकरण!");
                } else if (userService.userIsBrazilian(user)) {
                    botActions.sendMessageWithoutCallbackQuery(chatId, "✅ Registro bem-sucedido!");
                } else if (userService.userIsSpanish(user)) {
                    botActions.sendMessageWithoutCallbackQuery(chatId, "✅ Registro exitoso!");
                } else if (userService.userIsUzbek(user)) {
                    botActions.sendMessageWithoutCallbackQuery(chatId, "✅ Muvaffaqiyatli ro'yxatdan o'tish!");
                } else if (userService.userIsAzerbaijani(user)) {
                    botActions.sendMessageWithoutCallbackQuery(chatId, "✅ Uğurlu qeydiyyat!");
                } else if (userService.userIsTurkish(user)) {
                    botActions.sendMessageWithoutCallbackQuery(chatId, "✅ Başarılı kayıt!");
                } else if (userService.userIsPortuguese(user)) {
                    botActions.sendMessageWithoutCallbackQuery(chatId, "✅ Registro bem-sucedido!");
                } else if (userService.userIsArabic(user)) {
                    botActions.sendMessageWithoutCallbackQuery(chatId, "✅ تسجيل ناجح!");
                } else {
                    // Default to English if language is not recognized
                    botActions.sendMessageWithoutCallbackQuery(chatId, "✅ Registration successful!");
                }

                CommandWithCallback goToMinesGameCommand = new GoToMinesGameCommand(botActions, userService, urlService);
                goToMinesGameCommand.execute(chatId, new CallbackQuery());
            }
        } else {
            InlineKeyboardMarkup inlineKeyboardMarkup = MinesKeyboard.menuWithCancelLinkForCheckID(user.getLanguage(), urlService);

            if (userService.userIsRussian(user)) {
                botActions.sendMessageWithInlineKeyboard(chatId, "Вы ввели неправильный id!\nПожалуйста, попробуйте еще раз.", inlineKeyboardMarkup);
            } else if (userService.userIsEnglish(user)) {
                botActions.sendMessageWithInlineKeyboard(chatId, "You've entered an incorrect ID!\nPlease, try again.", inlineKeyboardMarkup);
            } else if (userService.userIsHindi(user)) {
                botActions.sendMessageWithInlineKeyboard(chatId, "आपने गलत आईडी डाली है!\nकृपया पुनः प्रयास करें।", inlineKeyboardMarkup);
            } else if (userService.userIsBrazilian(user)) {
                botActions.sendMessageWithInlineKeyboard(chatId, "Você inseriu um ID incorreto!\nPor favor, tente novamente.", inlineKeyboardMarkup);
            } else if (userService.userIsSpanish(user)) {
                botActions.sendMessageWithInlineKeyboard(chatId, "¡Has introducido un ID incorrecto!\nPor favor, inténtalo de nuevo.", inlineKeyboardMarkup);
            } else if (userService.userIsUzbek(user)) {
                botActions.sendMessageWithInlineKeyboard(chatId, "Siz noto'g'ri ID kiritdingiz!\nIltimos, yana bir bor urinib ko'ring.", inlineKeyboardMarkup);
            } else if (userService.userIsAzerbaijani(user)) {
                botActions.sendMessageWithInlineKeyboard(chatId, "Siz yanlış ID daxil etdiniz!\nZəhmət olmasa, bir daha cəhd edin.", inlineKeyboardMarkup);
            } else if (userService.userIsTurkish(user)) {
                botActions.sendMessageWithInlineKeyboard(chatId, "Yanlış kimlik numarası girdiniz!\nLütfen tekrar deneyin.", inlineKeyboardMarkup);
            } else if (userService.userIsPortuguese(user)) {
                botActions.sendMessageWithInlineKeyboard(chatId, "Você inseriu um ID incorreto!\nPor favor, tente novamente.", inlineKeyboardMarkup);
            } else if (userService.userIsArabic(user)) {
                botActions.sendMessageWithInlineKeyboard(chatId, "لقد أدخلت معرفًا غير صحيح!\nيرجى المحاولة مرة أخرى.", inlineKeyboardMarkup);
            } else {
                // Default to English if language is not recognized
                botActions.sendMessageWithInlineKeyboard(chatId, "You've entered an incorrect ID!\nPlease, try again.", inlineKeyboardMarkup);
            }
        }
    }

}