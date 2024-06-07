package com.example.tgbot.service.main.coinflip;

import com.example.tgbot.entity.User;
import com.example.tgbot.interfaces.BotActions;
import com.example.tgbot.interfaces.CommandWithCallback;
import com.example.tgbot.keyboard.games.CoinFlipKeyboard;
import com.example.tgbot.service.UrlService;
import com.example.tgbot.service.UserService;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;


public class GoToCoinFlipGameCommand implements CommandWithCallback {

    private final BotActions botActions;
    private final UserService userService;
    private final UrlService urlService;

    public GoToCoinFlipGameCommand(BotActions botActions, UserService userService, UrlService urlService) {
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
                answer = "👋🏻" + currentUser.getFirstName() + ", Добро пожаловать в 🔸CoinFlip BitsGap🔸!\n\n" +
                        "💰 CoinFlip - это захватывающая азартная игра, где ваша задача - угадать, какой стороной упадет монета: орлом или решкой.\n\n" +
                        "\uD83E\uDDE0*Наш бот использует продвинутые алгоритмы для повышения ваших шансов на победу.*\n" +
                        "*Он способен предсказывать результаты с точностью до 85% благодаря искусственному интеллекту от Bitsgap.*";
                break;
            case "english":
                answer = "👋🏻" + currentUser.getFirstName() + ", Welcome to 🔸CoinFlip BitsGap🔸!\n\n" +
                        "💰 CoinFlip is an exciting gambling game where your task is to guess whether the coin will land heads or tails.\n\n" +
                        "🧠 Our bot uses advanced algorithms to improve your winning chances.\n" +
                        "*It's capable of predicting outcomes with an accuracy of up to 85% thanks to artificial intelligence from Bitsgap.*";
                break;
            case "hindi":
                answer = "👋🏻" + currentUser.getFirstName() + ", 🔸CoinFlip BitsGap🔸 में आपका स्वागत है!\n\n" +
                        "💰 CoinFlip एक रोमांचक जुआ खेल है जहाँ आपका कार्य यह अनुमान लगाना है कि सिक्का सिर या पूंछ पर गिरेगा।\n\n" +
                        "🧠 हमारा बॉट आपके जीतने की संभावनाओं को बढ़ाने के लिए उन्नत एल्गोरिदम का उपयोग करता है।\n" +
                        "*यह Bitsgap से कृत्रिम बुद्धिमत्ता के धन्यवाद से परिणामों की भविष्यवाणी 85% तक की सटीकता के साथ करने में सक्षम है।*";
                break;
            case "brazilian":
                answer = "👋🏻" + currentUser.getFirstName() + ", Bem-vindo ao 🔸CoinFlip BitsGap🔸!\n\n" +
                        "💰 CoinFlip é um emocionante jogo de azar onde sua tarefa é adivinhar se a moeda cairá com cara ou coroa.\n\n" +
                        "🧠 Nosso bot usa algoritmos avançados para melhorar suas chances de ganhar.\n" +
                        "*É capaz de prever os resultados com uma precisão de até 85% graças à inteligência artificial da Bitsgap.*";
                break;
            case "spanish":
                answer = "👋🏻" + currentUser.getFirstName() + ", ¡Bienvenido a 🔸CoinFlip BitsGap🔸!\n\n" +
                        "💰 CoinFlip es un emocionante juego de azar donde tu tarea es adivinar si la moneda caerá con cara o cruz.\n\n" +
                        "🧠 Nuestro bot usa algoritmos avanzados para mejorar tus posibilidades de ganar.\n" +
                        "*Es capaz de predecir los resultados con una precisión de hasta el 85% gracias a la inteligencia artificial de Bitsgap.*";
                break;
            case "uzbek":
                answer = "👋🏻" + currentUser.getFirstName() + ", 🔸CoinFlip BitsGap🔸 ga xush kelibsiz!\n\n" +
                        "💰 CoinFlip - bu qiziqarli qimor o'yini bo'lib, unda sizning vazifangiz tanga qaysi tomonga tushishini taxmin qilishdir: bosh yoki dumi.\n\n" +
                        "🧠 Bizning bot g'alaba qozonish imkoniyatlaringizni oshirish uchun ilg'or algoritmlardan foydalanadi.\n" +
                        "*Bitsgap'dan sun'iy intellekt tufayli natijalarni 85% aniqlik bilan bashorat qilishga qodir.*";
                break;
            case "azerbaijani":
                answer = "👋🏻" + currentUser.getFirstName() + ", 🔸CoinFlip BitsGap🔸-a xoş gəlmisiniz!\n\n" +
                        "💰 CoinFlip - bu həyəcanverici qumar oyunudur, burada məqsədiniz pulun hansı tərəfə düşəcəyini təxmin etməkdir: baş və ya quyruq.\n\n" +
                        "🧠 Bizim bot qələbə şanslarınızı artırmaq üçün inkişaf etmiş alqoritmlərdən istifadə edir.\n" +
                        "*Bitsgap'dan süni intellekt sayəsində nəticələri 85% dəqiqliklə təxmin etmək qabiliyyətinə malikdir.*";
                break;
            case "turkish":
                answer = "👋🏻" + currentUser.getFirstName() + ", 🔸CoinFlip BitsGap🔸'e hoş geldiniz!\n\n" +
                        "💰 CoinFlip, madeni paranın yazı mı tura mı geleceğini tahmin etmeye çalıştığınız heyecan verici bir kumar oyunudur.\n\n" +
                        "🧠 Botumuz kazanma şansınızı artırmak için gelişmiş algoritmalar kullanır.\n" +
                        "*Bitsgap'tan gelen yapay zeka sayesinde sonuçları %85'e varan doğrulukla tahmin edebilir.*";
                break;
            case "portuguese":
                answer = "👋🏻" + currentUser.getFirstName() + ", Bem-vindo ao 🔸CoinFlip BitsGap🔸!\n\n" +
                        "💰 CoinFlip é um emocionante jogo de azar onde sua tarefa é adivinhar se a moeda cairá com cara ou coroa.\n\n" +
                        "🧠 Nosso bot usa algoritmos avançados para melhorar suas chances de ganhar.\n" +
                        "*É capaz de prever os resultados com uma precisão de até 85% graças à inteligência artificial da Bitsgap.*";
                break;
            case "arabic":
                answer = "👋🏻" + currentUser.getFirstName() + ", مرحبًا بك في 🔸CoinFlip BitsGap🔸!\n\n" +
                        "💰 CoinFlip هي لعبة مقامرة مثيرة حيث مهمتك هي تخمين ما إذا كانت العملة ستسقط على الوجه أو الخلف.\n\n" +
                        "🧠 يستخدم الروبوت الخاص بنا خوارزميات متقدمة لتحسين فرصك في الفوز.\n" +
                        "*إنه قادر على التنبؤ بالنتائج بدقة تصل إلى 85% بفضل الذكاء الاصطناعي من Bitsgap.*";
                break;
            default:
                answer = "👋🏻" + currentUser.getFirstName() + ", Welcome to 🔸CoinFlip BitsGap🔸!\n\n" +
                        "💰 CoinFlip is an exciting gambling game where your task is to guess whether the coin will land heads or tails.\n\n" +
                        "🧠 Our bot uses advanced algorithms to improve your winning chances.\n" +
                        "*It's capable of predicting outcomes with an accuracy of up to 85% thanks to artificial intelligence from Bitsgap.*";
                break;
        }

        if (userService.userIsRussian(currentUser)){
            if (currentUser.getIsVerify()){
                InlineKeyboardMarkup inlineKeyboardMarkup = CoinFlipKeyboard.mainMinesMenuIfUserAlreadyRegistered1(urlService,currentUser.getLanguage());
                botActions.sendMessageWithPhotoAndKeyboard(chatId, answer,"/imagesrussian/coinflip.jpg",inlineKeyboardMarkup);
            }
            else {
                InlineKeyboardMarkup inlineKeyboardMarkup = CoinFlipKeyboard.mainMinesMenuIfUserNotRegistered1(urlService,currentUser.getLanguage());
                botActions.sendMessageWithPhotoAndKeyboard(chatId, answer,"/imagesrussian/coinflip.jpg",inlineKeyboardMarkup);
            }
        }
        else {
            if (currentUser.getIsVerify()){
                InlineKeyboardMarkup inlineKeyboardMarkup = CoinFlipKeyboard.mainMinesMenuIfUserAlreadyRegistered1(urlService,currentUser.getLanguage());
                botActions.sendMessageWithPhotoAndKeyboard(chatId, answer,"/imagesenglish/coinflip.jpg",inlineKeyboardMarkup);
            }
            else {
                InlineKeyboardMarkup inlineKeyboardMarkup = CoinFlipKeyboard.mainMinesMenuIfUserNotRegistered1(urlService,currentUser.getLanguage());
                botActions.sendMessageWithPhotoAndKeyboard(chatId, answer,"/imagesenglish/coinflip.jpg",inlineKeyboardMarkup);
            }
        }

        if(callbackQuery != null && callbackQuery.getId()!= null){
            botActions.handleCallbackQuery(callbackQuery);
        }
    }
}