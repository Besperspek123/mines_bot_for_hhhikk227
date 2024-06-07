package com.example.tgbot.service.main.luckyjet;

import com.example.tgbot.entity.User;
import com.example.tgbot.interfaces.BotActions;
import com.example.tgbot.interfaces.CommandWithCallback;
import com.example.tgbot.keyboard.games.LuckyJetKeyboard;
import com.example.tgbot.service.DepositService;
import com.example.tgbot.service.PromoService;
import com.example.tgbot.service.UrlService;
import com.example.tgbot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;


@Slf4j
public class GiveSignalLuckyJetCommand implements CommandWithCallback {
    private UrlService urlService;
    private final BotActions botActions;
    private final UserService userService;
    private final PromoService promoService;
    private final DepositService depositService;

    public GiveSignalLuckyJetCommand(BotActions botActions, UrlService urlService, UserService userService, PromoService promoService, DepositService depositService) {
        this.botActions = botActions;
        this.urlService = urlService;
        this.userService = userService;
        this.promoService = promoService;
        this.depositService= depositService;
    }
    @Override
    public void execute(long chatId, CallbackQuery callbackQuery) {
        User currentUser = userService.getInfoAboutUserByChatID(chatId);
        if(!currentUser.getIsVerify()){
            CommandWithCallback infoAboutRegistrationMenu = new InfoAboutLuckyJetRegistrationMenu(botActions,urlService,userService,promoService);
            infoAboutRegistrationMenu.execute(chatId,callbackQuery);
        }
        else if (!currentUser.getIsDeposit()){
            if (currentUser.getOneWinId() != null){
                if(depositService.isPlayerDeposited(Long.toString(currentUser.getOneWinId()))){
                    userService.setIsDepositedIsTrue(Long.toString(currentUser.getOneWinId()));
                    execute(chatId,callbackQuery);
                }
                else {
                    CommandWithCallback infoAboutDepositMenu = new InfoAboutLuckyJetDepositMenu(botActions,urlService,userService,promoService);
                    infoAboutDepositMenu.execute(chatId,callbackQuery);
                }
            }

        }

        if(currentUser.getIsDeposit() && currentUser.getIsVerify()){
            showWebApp(chatId);
        }
        botActions.handleCallbackQuery(callbackQuery);
    }

    public void showWebApp(long chatId) {
        User user = userService.getInfoAboutUserByChatID(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = LuckyJetKeyboard.sendWebAppButton(user,user.getLanguage(),urlService.getOneWinUrlWithLuckyJet(user),urlService);
        String answer = "";
        switch (user.getLanguage()) {
            case "russian":
                answer = "🚀 Для получения сигнала откройте приложение по кнопке ниже! ✔️";
                break;
            case "english":
                answer = "🚀 To receive a signal, open the application using the button below! ✔️";
                break;
            case "hindi":
                answer = "🚀 सिग्नल प्राप्त करने के लिए, नीचे दिए गए बटन का उपयोग करके आवेदन खोलें! ✔️";
                break;
            case "brazilian":
                answer = "🚀 Para receber um sinal, abra o aplicativo usando o botão abaixo! ✔️";
                break;
            case "spanish":
                answer = "🚀 Para recibir una señal, abre la aplicación usando el botón abajo! ✔️";
                break;
            case "uzbek":
                answer = "🚀 Signal olish uchun ilovani quyidagi tugma yordamida oching! ✔️";
                break;
            case "azerbaijani":
                answer = "🚀 Siqnal almaq üçün aşağıdakı düymə vasitəsilə tətbiqi açın! ✔️";
                break;
            case "turkish":
                answer = "🚀 Sinyal almak için aşağıdaki düğmeyi kullanarak uygulamayı açın! ✔️";
                break;
            case "portuguese":
                answer = "🚀 Para receber um sinal, abra o aplicativo usando o botão abaixo! ✔️";
                break;
            case "arabic":
                answer = "🚀 لاستلام الإشارة، افتح التطبيق باستخدام الزر أدناه! ✔️";
                break;
            default:
                answer = "🚀 To receive a signal, open the application using the button below! ✔️";
                break;
        }


        botActions.sendMessageWithInlineKeyboard(chatId, answer, inlineKeyboardMarkup);
    }
}