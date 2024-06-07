package com.example.tgbot.service.main.coinflip;

import com.example.tgbot.entity.User;
import com.example.tgbot.interfaces.BotActions;
import com.example.tgbot.interfaces.CommandWithCallback;
import com.example.tgbot.keyboard.games.CoinFlipKeyboard;
import com.example.tgbot.service.DepositService;
import com.example.tgbot.service.PromoService;
import com.example.tgbot.service.UrlService;
import com.example.tgbot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;


@Slf4j
public class GiveSignalCoinCommand implements CommandWithCallback {
    private UrlService urlService;
    private final BotActions botActions;
    private final UserService userService;
    private final PromoService promoService;
    private final DepositService depositService;

    public GiveSignalCoinCommand(BotActions botActions, UrlService urlService, UserService userService, PromoService promoService, DepositService depositService) {
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
            CommandWithCallback infoAboutRegistrationMenu = new InfoAboutCoinRegistrationMenu(botActions,urlService,userService,promoService);
            infoAboutRegistrationMenu.execute(chatId,callbackQuery);
        }
        else if (!currentUser.getIsDeposit()){
            if (currentUser.getOneWinId() != null){
                if(depositService.isPlayerDeposited(Long.toString(currentUser.getOneWinId()))){
                    userService.setIsDepositedIsTrue(Long.toString(currentUser.getOneWinId()));
                    execute(chatId,callbackQuery);
                }
                else {
                    CommandWithCallback infoAboutDepositMenu = new InfoAboutCoinDepositMenu(botActions,urlService,userService,promoService);
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
        InlineKeyboardMarkup inlineKeyboardMarkup = CoinFlipKeyboard.sendWebAppButton(user,user.getLanguage(),urlService.getOneWinUrlWithCoin(user),urlService);
        String answer = "";
        switch (user.getLanguage()) {
            case "russian":
                answer = "🚀 Для получения сигнала откройте приложение по кнопке ниже! ✔️";
                break;
            case "english":
                answer = "🚀 To receive a signal, open the application using the button below! ✔️";
                break;
            case "hindi":
                answer = "🚀 सिग्नल प्राप्त करने के लिए, नीचे दिए गए बटन का उपयोग करके एप्लिकेशन खोलें! ✔️";
                break;
            case "brazilian":
                answer = "🚀 Para receber um sinal, abra o aplicativo usando o botão abaixo! ✔️";
                break;
            case "spanish":
                answer = "🚀 Para recibir una señal, abre la aplicación usando el botón de abajo! ✔️";
                break;
            case "uzbek":
                answer = "🚀 Signalni olish uchun, quyidagi tugmani bosib dasturga kiring! ✔️";
                break;
            case "azerbaijani":
                answer = "🚀 Siqnal almaq üçün, aşağıdakı düymədən istifadə edərək tətbiqi açın! ✔️";
                break;
            case "turkish":
                answer = "🚀 Sinyal almak için, aşağıdaki düğmeyi kullanarak uygulamayı açın! ✔️";
                break;
            case "portuguese":
                answer = "🚀 Para receber um sinal, abra o aplicativo usando o botão abaixo! ✔️";
                break;
            case "arabic":
                answer = "🚀 للحصول على إشارة، افتح التطبيق باستخدام الزر أدناه! ✔️";
                break;
            default:
                answer = "🚀 To receive a signal, open the application using the button below! ✔️";
                break;
        }


        botActions.sendMessageWithInlineKeyboard(chatId, answer, inlineKeyboardMarkup);
    }
}