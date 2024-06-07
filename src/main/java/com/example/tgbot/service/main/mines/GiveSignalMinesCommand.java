package com.example.tgbot.service.main.mines;

import com.example.tgbot.entity.User;
import com.example.tgbot.interfaces.BotActions;
import com.example.tgbot.interfaces.CommandWithCallback;
import com.example.tgbot.keyboard.games.MinesKeyboard;
import com.example.tgbot.service.DepositService;
import com.example.tgbot.service.PromoService;
import com.example.tgbot.service.UrlService;
import com.example.tgbot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;


@Slf4j
public class GiveSignalMinesCommand implements CommandWithCallback {
    private UrlService urlService;
    private final BotActions botActions;
    private final UserService userService;
    private final PromoService promoService;
    private final DepositService depositService;

    public GiveSignalMinesCommand(BotActions botActions, UrlService urlService,UserService userService,PromoService promoService,DepositService depositService) {
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
            CommandWithCallback infoAboutRegistrationMenu = new InfoAboutMinesRegistrationMenu(botActions,urlService,userService,promoService);
            infoAboutRegistrationMenu.execute(chatId,callbackQuery);
        }
        else if (!currentUser.getIsDeposit()){
            if (currentUser.getOneWinId() != null){
                if(depositService.isPlayerDeposited(Long.toString(currentUser.getOneWinId()))){
                    userService.setIsDepositedIsTrue(Long.toString(currentUser.getOneWinId()));
                    execute(chatId,callbackQuery);
                }
                else {
                    CommandWithCallback infoAboutDepositMenu = new InfoAboutMinesDepositMenu(botActions,urlService,userService,promoService);
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
        InlineKeyboardMarkup inlineKeyboardMarkup = MinesKeyboard.sendWebAppButton(user,user.getLanguage(), urlService.getOneWinUrlWithMines(user), urlService);
        String answer = "";
        if (user.getLanguage().equals("russian")) {
            answer = "🚀 Для получения сигнала откройте приложение по кнопке ниже! ✔\n" +
                    "🎮 Для открытия игры в 1WIN на которую выдаётся сигнал, нажмите 📲 1WIN";
        }
        if (user.getLanguage().equals("english")) {
            answer = "🚀 To receive the signal, open the app using the button below! ✔\n" +
                    "🎮 To open the game on 1WIN where the signal is being issued, press 📲 1WIN";
        }
        if (user.getLanguage().equals("hindi")) {
            answer = "🚀 सिग्नल प्राप्त करने के लिए नीचे दिए गए बटन पर ऐप खोलें! ✔\n" +
                    "🎮 1WIN पर गेम खोलने के लिए जिस पर सिग्नल जारी किया जा रहा है, 📲 1WIN दबाएं";
        }
        if (user.getLanguage().equals("brazilian")) {
            answer = "🚀 Para receber o sinal, abra o aplicativo pelo botão abaixo! ✔\n" +
                    "🎮 Para abrir o jogo na 1WIN onde o sinal está sendo emitido, pressione 📲 1WIN";
        }
        if (user.getLanguage().equals("spanish")) {
            answer = "🚀 Para recibir la señal, abre la aplicación desde el botón de abajo! ✔\n" +
                    "🎮 Para abrir el juego en 1WIN donde se emite la señal, presiona 📲 1WIN";
        }
        if (user.getLanguage().equals("uzbek")) {
            answer = "🚀 Signal olish uchun ilovani quyidagi tugma orqali oching! ✔\n" +
                    "🎮 Signal berilayotgan 1WIN o'yinini ochish uchun 📲 1WIN tugmasini bosing";
        }
        if (user.getLanguage().equals("azerbaijani")) {
            answer = "🚀 Siqnal almaq üçün tətbiqi aşağıdakı düymə ilə açın! ✔\n" +
                    "🎮 Siqnalın verildiyi 1WIN oyununu açmaq üçün 📲 1WIN düyməsini basın";
        }
        if (user.getLanguage().equals("turkish")) {
            answer = "🚀 Sinyali almak için aşağıdaki düğmeye tıklayarak uygulamayı açın! ✔\n" +
                    "🎮 Sinyalin verildiği 1WIN oyununu açmak için 📲 1WIN düğmesine basın";
        }
        if (user.getLanguage().equals("portuguese")) {
            answer = "🚀 Para receber o sinal, abra a aplicação pelo botão abaixo! ✔\n" +
                    "🎮 Para abrir o jogo na 1WIN onde o sinal está sendo emitido, pressione 📲 1WIN";
        }
        if (user.getLanguage().equals("arabic")) {
            answer = "🚀 للحصول على الإشارة، افتح التطبيق من الزر أدناه! ✔\n" +
                    "🎮 لفتح اللعبة في 1WIN حيث يتم إصدار الإشارة، اضغط على 📲 1WIN";
        }


        botActions.sendMessageWithInlineKeyboard(chatId, answer, inlineKeyboardMarkup);
    }
}