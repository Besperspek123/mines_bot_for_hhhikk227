package com.example.tgbot.service.main.luckyjet;

import com.example.tgbot.entity.User;
import com.example.tgbot.interfaces.BotActions;
import com.example.tgbot.interfaces.CommandWithCallback;
import com.example.tgbot.interfaces.CommandWithoutCallback;
import com.example.tgbot.keyboard.games.LuckyJetKeyboard;
import com.example.tgbot.service.RegistrationService;
import com.example.tgbot.service.UrlService;
import com.example.tgbot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;


@Slf4j
public class TryLuckyJetRegistrationCommand implements CommandWithoutCallback {
    private final BotActions botActions;
    private final UserService userService;
    private final String oneWinId;

    private final UrlService urlService;

    private final RegistrationService registrationService;

    public TryLuckyJetRegistrationCommand(BotActions botActions, UserService userService, String oneWinId, RegistrationService registrationService, UrlService urlService) {
        this.botActions = botActions;
        this.userService = userService;
        this.oneWinId = oneWinId;
        this.registrationService = registrationService;
        this.urlService = urlService;
    }
    @Override
    public void execute(long chatId) {
        User user = userService.getInfoAboutUserByChatID(chatId);

//        if (oneWinId == null){
//            log.error("У пользователя " + userService.getInfoAboutUserByChatID(chatId).getUsername() +
//                    " one win id оказался null");
//            throw new OneWinIdIsNullException("Ты долбаёб?");
//        }



        if(oneWinId.matches("^\\d{8}$") && user.getAwaitingMode() && registrationService.isPlayerRegistered(oneWinId)) {
            if (userService.isUserExistByOneWinId(Long.parseLong(oneWinId))){
                InlineKeyboardMarkup inlineKeyboardMarkup = LuckyJetKeyboard.menuWithCancelButtonAndLinkToOneWin(urlService.getOneWinUrl(user),user.getLanguage());

                switch (user.getLanguage()) {
                    case "russian":
                        botActions.sendMessageWithInlineKeyboard(chatId, "Вы ввели чужой id!\nПожалуйста, попробуйте еще раз.", inlineKeyboardMarkup);
                        break;
                    case "english":
                        botActions.sendMessageWithInlineKeyboard(chatId, "You've entered someone else's ID!\nPlease, try again.", inlineKeyboardMarkup);
                        break;
                    case "hindi":
                        botActions.sendMessageWithInlineKeyboard(chatId, "आपने किसी और का ID डाला है!\nकृपया, पुनः प्रयास करें।", inlineKeyboardMarkup);
                        break;
                    case "brazilian":
                        botActions.sendMessageWithInlineKeyboard(chatId, "Você digitou o ID de outra pessoa!\nPor favor, tente novamente.", inlineKeyboardMarkup);
                        break;
                    case "spanish":
                        botActions.sendMessageWithInlineKeyboard(chatId, "¡Has introducido la ID de otra persona!\nPor favor, inténtalo de nuevo.", inlineKeyboardMarkup);
                        break;
                    case "uzbek":
                        botActions.sendMessageWithInlineKeyboard(chatId, "Siz boshqa odamning ID-sini kiritdingiz!\nIltimos, yana bir bor urinib ko'ring.", inlineKeyboardMarkup);
                        break;
                    case "azerbaijani":
                        botActions.sendMessageWithInlineKeyboard(chatId, "Siz başqasının ID-sini daxil etdiniz!\nZəhmət olmasa, yenidən cəhd edin.", inlineKeyboardMarkup);
                        break;
                    case "turkish":
                        botActions.sendMessageWithInlineKeyboard(chatId, "Başkasının kimliğini girdiniz!\nLütfen tekrar deneyin.", inlineKeyboardMarkup);
                        break;
                    case "portuguese":
                        botActions.sendMessageWithInlineKeyboard(chatId, "Você inseriu o ID de outra pessoa!\nPor favor, tente novamente.", inlineKeyboardMarkup);
                        break;
                    case "arabic":
                        botActions.sendMessageWithInlineKeyboard(chatId, "لقد أدخلت معرف شخص آخر!\nيرجى المحاولة مرة أخرى.", inlineKeyboardMarkup);
                        break;
                    default:
                        botActions.sendMessageWithInlineKeyboard(chatId, "You've entered someone else's ID!\nPlease, try again.", inlineKeyboardMarkup);
                        break;
                }
            }
            else {
                user.setIsVerify(true);
                user.setAwaitingMode(false);
                user.setOneWinId(Long.valueOf(oneWinId));
                userService.saveUser(user);
//            userService.setVerifyIsTrue(chatId);
//            userService.setDefaultAwaitingMode(chatId);

                switch (user.getLanguage()) {
                    case "russian":
                        botActions.sendMessageWithoutCallbackQuery(chatId, "✅ Успешная регистрация!");
                        break;
                    case "english":
                        botActions.sendMessageWithoutCallbackQuery(chatId, "✅ Registration successful!");
                        break;
                    case "hindi":
                        botActions.sendMessageWithoutCallbackQuery(chatId, "✅ पंजीकरण सफल रहा!");
                        break;
                    case "brazilian":
                        botActions.sendMessageWithoutCallbackQuery(chatId, "✅ Registro bem-sucedido!");
                        break;
                    case "spanish":
                        botActions.sendMessageWithoutCallbackQuery(chatId, "✅ ¡Registro exitoso!");
                        break;
                    case "uzbek":
                        botActions.sendMessageWithoutCallbackQuery(chatId, "✅ Ro'yxatdan o'tish muvaffaqiyatli bo'ldi!");
                        break;
                    case "azerbaijani":
                        botActions.sendMessageWithoutCallbackQuery(chatId, "✅ Uğurlu qeydiyyat!");
                        break;
                    case "turkish":
                        botActions.sendMessageWithoutCallbackQuery(chatId, "✅ Kayıt başarılı!");
                        break;
                    case "portuguese":
                        botActions.sendMessageWithoutCallbackQuery(chatId, "✅ Registro bem-sucedido!");
                        break;
                    case "arabic":
                        botActions.sendMessageWithoutCallbackQuery(chatId, "✅ التسجيل ناجح!");
                        break;
                    default:
                        botActions.sendMessageWithoutCallbackQuery(chatId, "✅ Registration successful!");
                        break;
                }
                CommandWithCallback goToLuckyJetGameCommand = new GoToLuckyJetGameCommand(botActions,userService,urlService);
                goToLuckyJetGameCommand.execute(chatId, new CallbackQuery());
            }
        } else {
            InlineKeyboardMarkup inlineKeyboardMarkup = LuckyJetKeyboard.menuWithCancelButtonAndLinkToOneWin(urlService.getOneWinUrl(user),user.getLanguage());

            switch (user.getLanguage()) {
                case "russian":
                    botActions.sendMessageWithInlineKeyboard(chatId, "Вы ввели неправильный id!\nПожалуйста, попробуйте еще раз.", inlineKeyboardMarkup);
                    break;
                case "english":
                    botActions.sendMessageWithInlineKeyboard(chatId, "You've entered an incorrect ID!\nPlease, try again.", inlineKeyboardMarkup);
                    break;
                case "hindi":
                    botActions.sendMessageWithInlineKeyboard(chatId, "आपने गलत आईडी दर्ज की है!\nकृपया, पुन: प्रयास करें।", inlineKeyboardMarkup);
                    break;
                case "brazilian":
                    botActions.sendMessageWithInlineKeyboard(chatId, "Você digitou um ID incorreto!\nPor favor, tente novamente.", inlineKeyboardMarkup);
                    break;
                case "spanish":
                    botActions.sendMessageWithInlineKeyboard(chatId, "¡Has ingresado una ID incorrecta!\nPor favor, inténtalo de nuevo.", inlineKeyboardMarkup);
                    break;
                case "uzbek":
                    botActions.sendMessageWithInlineKeyboard(chatId, "Noto'g'ri ID kiritdingiz!\nIltimos, qayta urinib ko'ring.", inlineKeyboardMarkup);
                    break;
                case "azerbaijani":
                    botActions.sendMessageWithInlineKeyboard(chatId, "Siz səhv ID daxil etdiniz!\nXahiş edirik, yenidən cəhd edin.", inlineKeyboardMarkup);
                    break;
                case "turkish":
                    botActions.sendMessageWithInlineKeyboard(chatId, "Yanlış kimlik girdiniz!\nLütfen, tekrar deneyin.", inlineKeyboardMarkup);
                    break;
                case "portuguese":
                    botActions.sendMessageWithInlineKeyboard(chatId, "Você inseriu um ID incorreto!\nPor favor, tente novamente.", inlineKeyboardMarkup);
                    break;
                case "arabic":
                    botActions.sendMessageWithInlineKeyboard(chatId, "لقد أدخلت معرفًا غير صحيح!\nيرجى المحاولة مرة أخرى.", inlineKeyboardMarkup);
                    break;
                default:
                    botActions.sendMessageWithInlineKeyboard(chatId, "You've entered an incorrect ID!\nPlease, try again.", inlineKeyboardMarkup);
                    break;
            }

        }

    }
}