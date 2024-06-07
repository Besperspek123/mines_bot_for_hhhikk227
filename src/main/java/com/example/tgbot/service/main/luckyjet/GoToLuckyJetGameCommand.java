package com.example.tgbot.service.main.luckyjet;

import com.example.tgbot.entity.User;
import com.example.tgbot.interfaces.BotActions;
import com.example.tgbot.interfaces.CommandWithCallback;
import com.example.tgbot.keyboard.games.LuckyJetKeyboard;
import com.example.tgbot.service.UrlService;
import com.example.tgbot.service.UserService;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;


public class GoToLuckyJetGameCommand implements CommandWithCallback {

    private final BotActions botActions;
    private final UserService userService;
    private final UrlService urlService;

    public GoToLuckyJetGameCommand(BotActions botActions, UserService userService, UrlService urlService) {
        this.botActions = botActions;
        this.userService = userService;
        this.urlService = urlService;
    }
    @Override
    public void execute(long chatId, CallbackQuery callbackQuery) {
        User currentUser = userService.getInfoAboutUserByChatID(chatId);
        if(currentUser.getFirstName().contains("_")){
            if(currentUser.getLanguage().equals("russian") || currentUser.getLanguage().equals("uzbek") || currentUser.getLanguage().equals("azerbaijani")){
                currentUser.setFirstName("Пользователь");
            }
            else currentUser.setFirstName("User");
        }
        String answer = "";

        switch (currentUser.getLanguage()) {
            case "russian":
                answer = "👋🏻" + currentUser.getFirstName() + ", Добро пожаловать в 🔸LuckyJet BitsGap🔸!\n\n" +
                        "✈️ LuckyJet - это захватывающая игра на удачу в букмекерской конторе 1win, где вам необходимо сделать ставку на растущий коэффициент, который может остановиться в любой момент.\n" +
                        "Ваша цель - выйти из игры до того, как коэффициент 'взорвется'.\n\n" +
                        "*Наш бот основан на нейросети от Bitsgap*\n" +
                        "*Он может предугадать, когда именно произойдет 'взрыв' с вероятностью 87%*";
                break;
            case "english":
                answer = "👋🏻" + currentUser.getFirstName() + ", Welcome to 🔸LuckyJet BitsGap🔸!\n\n" +
                        "✈️ LuckyJet is an exciting game of chance at 1WIN where you place bets on an increasing multiplier that can 'crash' at any moment.\n" +
                        "Your goal is to cash out before the multiplier crashes.\n\n" +
                        "🧠Our bot is based on a neural network from Bitsgap.\n" +
                        "*It has been developed to predict the crash point with an 87% accuracy rate.*";
                break;
            case "hindi":
                answer = "👋🏻" + currentUser.getFirstName() + ", 🔸LuckyJet BitsGap🔸 में आपका स्वागत है!\n\n" +
                        "✈️ LuckyJet एक रोमांचक खेल है 1WIN पर, जहाँ आप एक बढ़ते गुणक पर शर्त लगाते हैं जो किसी भी समय 'क्रैश' हो सकता है।\n" +
                        "आपका लक्ष्य है गुणक के 'क्रैश' होने से पहले नकदी निकालना।\n\n" +
                        "🧠 हमारा बॉट Bitsgap की एक न्यूरल नेटवर्क पर आधारित है।\n" +
                        "*यह 87% की सटीकता दर के साथ क्रैश पॉइंट की भविष्यवाणी करने के लिए विकसित किया गया है।*";
                break;
            case "brazilian":
                answer = "👋🏻" + currentUser.getFirstName() + ", Bem-vindo ao 🔸LuckyJet BitsGap🔸!\n\n" +
                        "✈️ LuckyJet é um jogo emocionante de sorte na 1WIN onde você faz apostas em um multiplicador crescente que pode 'crash' a qualquer momento.\n" +
                        "Seu objetivo é retirar antes que o multiplicador 'crash'.\n\n" +
                        "🧠 Nosso bot é baseado em uma rede neural da Bitsgap.\n" +
                        "*Ele foi desenvolvido para prever o ponto de crash com uma taxa de precisão de 87%.*";
                break;
            case "spanish":
                answer = "👋🏻" + currentUser.getFirstName() + ", ¡Bienvenido a 🔸LuckyJet BitsGap🔸!\n\n" +
                        "✈️ LuckyJet es un emocionante juego de azar en 1WIN donde apuestas en un multiplicador creciente que puede 'crash' en cualquier momento.\n" +
                        "Tu objetivo es retirar antes de que el multiplicador 'crash'.\n\n" +
                        "🧠 Nuestro bot está basado en una red neuronal de Bitsgap.\n" +
                        "*Se ha desarrollado para predecir el punto de crash con una tasa de precisión del 87%.*";
                break;
            case "uzbek":
                answer = "👋🏻" + currentUser.getFirstName() + ", 🔸LuckyJet BitsGap🔸 ga xush kelibsiz!\n\n" +
                        "✈️ LuckyJet 1WIN da omadli o'yin bo'lib, unda siz har qanday vaqtda 'portlashi' mumkin bo'lgan ko'paytuvchi ustiga pul tikasiz.\n" +
                        "Sizning maqsadingiz ko'paytuvchi 'portlashidan' oldin naqd qilishdir.\n\n" +
                        "🧠 Bizning bot Bitsgap ning neyron tarmog'iga asoslangan.\n" +
                        "*Bu 87% aniqlik darajasi bilan portlash nuqtasini bashorat qilish uchun ishlab chiqilgan.*";
                break;
            case "azerbaijani":
                answer = "👋🏻" + currentUser.getFirstName() + ", 🔸LuckyJet BitsGap🔸-a xoş gəldiniz!\n\n" +
                        "✈️ LuckyJet, 1WIN-də hər hansı bir anda 'partlaya' bilən artan multiplikatora mərc etdiyiniz həyəcanverici bir şans oyunudur.\n" +
                        "Sizin məqsədiniz multiplikator 'partlamadan' əvvəl nağd çıxarmaqdır.\n\n" +
                        "🧠 Bizim botumuz Bitsgap-ın neyron şəbəkəsinə əsaslanır.\n" +
                        "*O, 87% dəqiqlik dərəcəsi ilə partlayış nöqtəsini proqnozlaşdırmaq üçün inkişaf etdirilmişdir.*";
                break;
            case "turkish":
                answer = "👋🏻" + currentUser.getFirstName() + ", 🔸LuckyJet BitsGap🔸'a hoş geldiniz!\n\n" +
                        "✈️ LuckyJet, 1WIN'de artan bir çarpan üzerine bahis yaptığınız, herhangi bir anda 'çökebilecek' heyecan verici bir şans oyunudur.\n" +
                        "Amacınız çarpan 'çökmeden' önce nakit çıkış yapmaktır.\n\n" +
                        "🧠 Botumuz Bitsgap'ın bir sinir ağına dayanır.\n" +
                        "*Çarpan noktasını %87 doğruluk oranıyla tahmin etmek için geliştirilmiştir.*";
                break;
            case "portuguese":
                answer = "👋🏻" + currentUser.getFirstName() + ", Bem-vindo ao 🔸LuckyJet BitsGap🔸!\n\n" +
                        "✈️ LuckyJet é um jogo emocionante de sorte na 1WIN onde você faz apostas em um multiplicador crescente que pode 'crash' a qualquer momento.\n" +
                        "Seu objetivo é retirar antes que o multiplicador 'crash'.\n\n" +
                        "🧠 Nosso bot é baseado em uma rede neural da Bitsgap.\n" +
                        "*Ele foi desenvolvido para prever o ponto de crash com uma taxa de precisão de 87%.*";
                break;
            case "arabic":
                answer = "👋🏻" + currentUser.getFirstName() + ", مرحبًا بك في 🔸LuckyJet BitsGap🔸!\n\n" +
                        "✈️ LuckyJet هي لعبة حظ مثيرة في 1WIN حيث تراهن على مضاعف متزايد يمكن أن 'ينفجر' في أي لحظة.\n" +
                        "هدفك هو الانسحاب قبل أن 'ينفجر' المضاعف.\n\n" +
                        "🧠 يعتمد بوتنا على شبكة عصبية من Bitsgap.\n" +
                        "*لقد تم تطويره للتنبؤ بنقطة الانهيار بمعدل دقة 87٪.*";
                break;
            default:
                answer = "👋🏻" + currentUser.getFirstName() + ", Welcome to 🔸LuckyJet BitsGap🔸!\n\n" +
                        "✈️ LuckyJet is an exciting game of chance at 1WIN where you place bets on an increasing multiplier that can 'crash' at any moment.\n" +
                        "Your goal is to cash out before the multiplier crashes.\n\n" +
                        "🧠Our bot is based on a neural network from Bitsgap.\n" +
                        "*It has been developed to predict the crash point with an 87% accuracy rate.*";
                break;
        }


        if (userService.userIsRussian(currentUser)){
            if (currentUser.getIsVerify()){
                InlineKeyboardMarkup inlineKeyboardMarkup = LuckyJetKeyboard.mainMinesMenuIfUserAlreadyRegistered1(urlService,currentUser.getLanguage());
                botActions.sendMessageWithPhotoAndKeyboard(chatId, answer,"/imagesrussian/luckyjet.jpg",inlineKeyboardMarkup);
            }
            else {
                InlineKeyboardMarkup inlineKeyboardMarkup = LuckyJetKeyboard.mainMinesMenuIfUserNotRegistered1(urlService,currentUser.getLanguage());
                botActions.sendMessageWithPhotoAndKeyboard(chatId, answer,"/imagesrussian/luckyjet.jpg",inlineKeyboardMarkup);
            }
        }
        else {
            if (currentUser.getIsVerify()){
                InlineKeyboardMarkup inlineKeyboardMarkup = LuckyJetKeyboard.mainMinesMenuIfUserAlreadyRegistered1(urlService,currentUser.getLanguage());
                botActions.sendMessageWithPhotoAndKeyboard(chatId, answer,"/imagesenglish/luckyjet.jpg",inlineKeyboardMarkup);
            }
            else {
                InlineKeyboardMarkup inlineKeyboardMarkup = LuckyJetKeyboard.mainMinesMenuIfUserNotRegistered1(urlService,currentUser.getLanguage());
                botActions.sendMessageWithPhotoAndKeyboard(chatId, answer,"/imagesenglish/luckyjet.jpg",inlineKeyboardMarkup);
            }
        }
        if(callbackQuery != null && callbackQuery.getId()!= null){
            botActions.handleCallbackQuery(callbackQuery);
        }
    }
}