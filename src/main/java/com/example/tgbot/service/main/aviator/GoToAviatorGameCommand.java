package com.example.tgbot.service.main.aviator;

import com.example.tgbot.entity.User;
import com.example.tgbot.interfaces.BotActions;
import com.example.tgbot.interfaces.CommandWithCallback;
import com.example.tgbot.keyboard.games.AviatorKeyboard;
import com.example.tgbot.service.UrlService;
import com.example.tgbot.service.UserService;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;


public class GoToAviatorGameCommand implements CommandWithCallback {

    private final BotActions botActions;
    private final UserService userService;
    private final UrlService urlService;

    public GoToAviatorGameCommand(BotActions botActions, UserService userService, UrlService urlService) {
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
                answer = "👋🏻" + currentUser.getFirstName() + ", Добро пожаловать в 🔸Aviator BitsGap🔸!\n\n" +
                        "✈️ Aviator - это захватывающая игра на удачу в букмекерской конторе 1win, где ваша задача - сделать ставку на увеличивающийся коэффициент, прежде чем игра автоматически завершится.\n" +
                        "Ваша цель - выйти в нужный момент, чтобы максимизировать ваш выигрыш.\n\n" +
                        "🧠*Наш бот основан на нейросети от Bitsgap*\n" +
                        "*Он может предугадать, когда игра закончится, с вероятностью 87%*";
                break;
            case "english":
                answer = "👋🏻" + currentUser.getFirstName() + ", Welcome to 🔸Aviator BitsGap🔸!\n\n" +
                        "✈️ Aviator is an exciting game of chance at 1WIN where your task is to place bets on an increasing multiplier before the game ends automatically.\n" +
                        "Your goal is to cash out at the right moment to maximize your winnings.\n\n" +
                        "🧠Our bot is based on a neural network from Bitsgap.\n" +
                        "*It has been developed to predict when the game will end with 87% accuracy.*";
                break;
            case "hindi":
                answer = "👋🏻" + currentUser.getFirstName() + ", Aviator BitsGap में आपका स्वागत है!\n\n" +
                        "✈️ Aviator 1WIN में एक रोमांचक खेल है जहां आपका कार्य खेल के स्वचालित रूप से समाप्त होने से पहले बढ़ते गुणांक पर दांव लगाना है।\n" +
                        "आपका लक्ष्य सही समय पर नकदी निकालना है ताकि आपकी जीत अधिकतम हो सके।\n\n" +
                        "🧠 हमारा बॉट Bitsgap की न्यूरल नेटवर्क पर आधारित है।\n" +
                        "*यह 87% सटीकता के साथ भविष्यवाणी करने के लिए विकसित किया गया है कि खेल कब समाप्त होगा।*";
                break;
            case "brazilian":
                answer = "👋🏻" + currentUser.getFirstName() + ", Bem-vindo ao Aviator BitsGap!\n\n" +
                        "✈️ Aviator é um jogo emocionante de azar na 1WIN, onde sua tarefa é fazer apostas em um multiplicador crescente antes que o jogo termine automaticamente.\n" +
                        "Seu objetivo é sacar no momento certo para maximizar seus ganhos.\n\n" +
                        "🧠 Nosso bot é baseado em uma rede neural da Bitsgap.\n" +
                        "*Foi desenvolvido para prever quando o jogo terminará com 87% de precisão.*";
                break;
            case "spanish":
                answer = "👋🏻" + currentUser.getFirstName() + ", ¡Bienvenido a Aviator BitsGap!\n\n" +
                        "✈️ Aviator es un emocionante juego de azar en 1WIN donde tu tarea es hacer apuestas en un multiplicador creciente antes de que el juego termine automáticamente.\n" +
                        "Tu objetivo es retirar en el momento adecuado para maximizar tus ganancias.\n\n" +
                        "🧠 Nuestro bot está basado en una red neuronal de Bitsgap.\n" +
                        "*Se ha desarrollado para predecir cuándo terminará el juego con un 87% de precisión.*";
                break;
            case "uzbek":
                answer = "👋🏻" + currentUser.getFirstName() + ", Aviator BitsGap ga xush kelibsiz!\n\n" +
                        "✈️ Aviator 1WIN da hayajonli o'yin bo'lib, vazifangiz o'yin avtomatik tugashidan oldin o'sib borayotgan koeffitsientga pul tikishdir.\n" +
                        "Maqsadingiz yutug'ingizni maksimal darajada oshirish uchun to'g'ri vaqtda pulni olish.\n\n" +
                        "🧠 Bizning botimiz Bitsgapning neyron tarmog'iga asoslangan.\n" +
                        "*O'yin qachon tugashini 87% aniqlik bilan taxmin qilish uchun ishlab chiqilgan.*";
                break;
            case "azerbaijani":
                answer = "👋🏻" + currentUser.getFirstName() + ", Aviator BitsGap-a xoş gəldiniz!\n\n" +
                        "✈️ Aviator 1WIN-də həyəcanverici bir şans oyunudur, burada tapşırığınız oyun avtomatik bitmədən əvvəl artan multiplikatora mərc qoymaqdır.\n" +
                        "Məqsədiniz maksimum qazancları əldə etmək üçün doğru anda nağdlaşdırmaqdır.\n\n" +
                        "🧠 Botumuz Bitsgap-ın neyron şəbəkəsinə əsaslanır.\n" +
                        "*Oyun nə zaman bitəcəyini 87% dəqiqliklə proqnozlaşdırmaq üçün inkişaf etdirilmişdir.*";
                break;
            case "turkish":
                answer = "👋🏻" + currentUser.getFirstName() + ", Aviator BitsGap'a Hoş Geldiniz!\n\n" +
                        "✈️ Aviator, 1WIN'de otomatik olarak sona ermeden önce artan bir çarpana bahis yapmanız gereken heyecan verici bir şans oyunudur.\n" +
                        "Amacınız, kazancınızı en üst düzeye çıkarmak için doğru zamanda nakit çekmektir.\n\n" +
                        "🧠 Botumuz, Bitsgap'in sinir ağına dayanmaktadır.\n" +
                        "*Oyun sona erdiğinde %87 doğrulukla tahmin etmek için geliştirilmiştir.*";
                break;
            case "portuguese":
                answer = "👋🏻" + currentUser.getFirstName() + ", Bem-vindo ao Aviator BitsGap!\n\n" +
                        "✈️ Aviator é um jogo emocionante de azar na 1WIN, onde sua tarefa é fazer apostas em um multiplicador crescente antes que o jogo termine automaticamente.\n" +
                        "Seu objetivo é sacar no momento certo para maximizar seus ganhos.\n\n" +
                        "🧠 Nosso bot é baseado em uma rede neural da Bitsgap.\n" +
                        "*Foi desenvolvido para prever quando o jogo terminará com 87% de precisão.*";
                break;
            case "arabic":
                answer = "👋🏻" + currentUser.getFirstName() + ", مرحبًا بك في Aviator BitsGap!\n\n" +
                        "✈️ Aviator هي لعبة مثيرة في 1WIN حيث تكون مهمتك هي وضع رهانات على مضاعف متزايد قبل أن تنتهي اللعبة تلقائيًا.\n" +
                        "هدفك هو صرف الأموال في الوقت المناسب لتحقيق أقصى قدر من الأرباح.\n\n" +
                        "🧠 يعتمد برنامجنا على شبكة عصبية من Bitsgap.\n" +
                        "*تم تطويره للتنبؤ بموعد انتهاء اللعبة بدقة تصل إلى 87٪.*";
                break;
            default:
                answer = "👋🏻" + currentUser.getFirstName() + ", Welcome to 🔸Aviator BitsGap🔸!\n\n" +
                        "✈️ Aviator is an exciting game of chance at 1WIN where your task is to place bets on an increasing multiplier before the game ends automatically.\n" +
                        "Your goal is to cash out at the right moment to maximize your winnings.\n\n" +
                        "🧠Our bot is based on a neural network from Bitsgap.\n" +
                        "*It has been developed to predict when the game will end with 87% accuracy.*";
                break;
        }


        if (userService.userIsRussian(currentUser)){
            if (currentUser.getIsVerify()){
                InlineKeyboardMarkup inlineKeyboardMarkup = AviatorKeyboard.mainMinesMenuIfUserAlreadyRegistered1(urlService,currentUser.getLanguage());
                botActions.sendMessageWithPhotoAndKeyboard(chatId, answer,"/imagesrussian/aviator.jpg",inlineKeyboardMarkup);
            }
            else {
                InlineKeyboardMarkup inlineKeyboardMarkup = AviatorKeyboard.mainMinesMenuIfUserNotRegistered1(urlService,currentUser.getLanguage());
                botActions.sendMessageWithPhotoAndKeyboard(chatId, answer,"/imagesrussian/aviator.jpg",inlineKeyboardMarkup);
            }
        }
        else {
            if (currentUser.getIsVerify()){
                InlineKeyboardMarkup inlineKeyboardMarkup = AviatorKeyboard.mainMinesMenuIfUserAlreadyRegistered1(urlService,currentUser.getLanguage());
                botActions.sendMessageWithPhotoAndKeyboard(chatId, answer,"/imagesenglish/aviator.jpg",inlineKeyboardMarkup);
            }
            else {
                InlineKeyboardMarkup inlineKeyboardMarkup = AviatorKeyboard.mainMinesMenuIfUserNotRegistered1(urlService,currentUser.getLanguage());
                botActions.sendMessageWithPhotoAndKeyboard(chatId, answer,"/imagesenglish/aviator.jpg",inlineKeyboardMarkup);
            }
        }

        if(callbackQuery != null && callbackQuery.getId()!= null){
            botActions.handleCallbackQuery(callbackQuery);
        }
    }
}