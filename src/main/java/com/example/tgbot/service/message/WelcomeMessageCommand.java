package com.example.tgbot.service.message;

import com.example.tgbot.entity.User;
import com.example.tgbot.keyboard.StartKeyboard;
import com.example.tgbot.interfaces.BotActions;
import com.example.tgbot.interfaces.CommandWithFirstName;
import com.example.tgbot.service.UrlService;
import com.example.tgbot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@Slf4j
public class WelcomeMessageCommand implements CommandWithFirstName {

    private final BotActions botActions;
    private final UrlService urlService;
    private final UserService userService;

    public WelcomeMessageCommand(BotActions botActions, UrlService urlService, UserService userService) {
        this.botActions = botActions;
        this.urlService = urlService;
        this.userService = userService;

    }
    @Override
    public void execute(long chatId, String firstName) {
        User user = userService.getInfoAboutUserByChatID(chatId);
        String welcomeText = "";
        switch (user.getLanguage()) {
            case "russian":
                welcomeText = "🌟 Добро пожаловать, " + firstName + "!\n\n" +
                        "Чтобы использовать бота, пожалуйста, подпишитесь на наш канал🤝\n\n";
                break;
            case "english":
                welcomeText = "🌟 Welcome, " + firstName + "!\n\n" +
                        "To use the bot, please subscribe to our channel🤝\n\n";
                break;
            case "hindi":
                welcomeText = "🌟 स्वागत है, " + firstName + "!\n\n" +
                        "बॉट का उपयोग करने के लिए, कृपया हमारे चैनल की सदस्यता लें🤝\n\n";
                break;
            case "brazilian":
                welcomeText = "🌟 Bem-vindo, " + firstName + "!\n\n" +
                        "Para usar o bot, por favor, inscreva-se no nosso canal🤝\n\n";
                break;
            case "spanish":
                welcomeText = "🌟 Bienvenido, " + firstName + "!\n\n" +
                        "Para usar el bot, por favor, suscríbete a nuestro canal🤝\n\n";
                break;
            case "uzbek":
                welcomeText = "🌟 Xush kelibsiz, " + firstName + "!\n\n" +
                        "Botdan foydalanish uchun iltimos, kanalimizga obuna bo'ling🤝\n\n";
                break;
            case "azerbaijani":
                welcomeText = "🌟 Xoş gəlmisiniz, " + firstName + "!\n\n" +
                        "Botdan istifadə etmək üçün, zəhmət olmasa, kanalımıza abunə olun🤝\n\n";
                break;
            case "turkish":
                welcomeText = "🌟 Hoş geldiniz, " + firstName + "!\n\n" +
                        "Botu kullanmak için lütfen kanalımıza abone olun🤝\n\n";
                break;
            case "portuguese":
                welcomeText = "🌟 Bem-vindo, " + firstName + "!\n\n" +
                        "Para usar o bot, por favor, subscreva o nosso canal🤝\n\n";
                break;
            case "arabic":
                welcomeText = "🌟 مرحبًا بك، " + firstName + "!\n\n" +
                        "لاستخدام الروبوت، يرجى الاشتراك في قناتنا🤝\n\n";
                break;
            default:
                welcomeText = "🌟 Welcome, " + firstName + "!\n\n" +
                        "To use the bot, please subscribe to our channel🤝\n\n";
                break;
        }
        InlineKeyboardMarkup inlineKeyboardMarkup = StartKeyboard.startMenu(urlService.getTelegramChannelUrl(user), urlService,user.getLanguage());
        botActions.sendMessageWithInlineKeyboard(chatId, welcomeText, inlineKeyboardMarkup);
        log.info("Пользователь " + firstName + " получил теплый приветственный сообщение");
    }


}