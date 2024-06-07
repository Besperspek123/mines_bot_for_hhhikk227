package com.example.tgbot.service.main.mines;

import com.example.tgbot.entity.User;
import com.example.tgbot.interfaces.BotActions;
import com.example.tgbot.interfaces.CommandWithCallback;
import com.example.tgbot.keyboard.games.MinesKeyboard;
import com.example.tgbot.service.UrlService;
import com.example.tgbot.service.UserService;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;


public class GoToMinesGameCommand implements CommandWithCallback {

    private final BotActions botActions;
    private final UserService userService;
    private final UrlService urlService;

    public GoToMinesGameCommand(BotActions botActions, UserService userService, UrlService urlService) {
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
        String answer ="";

        if(userService.userIsRussian(currentUser)){
            answer = "👋🏻"+ currentUser.getFirstName() +", Добро пожаловать в 🔸MINES BitsGap🔸!\n\n" +
                    "💣Mines - это гэмблинг игра в букмекерской конторе 1win, которая основывается на классическом 'Сапёре'.\n" +
                    "Ваша цель - открывать безопасные ячейки и не попадаться в ловушки.\n\n" +
                    "*Наш бот основан на нейросети от Bitsgap*\n" +
                    "*Он может предугадать расположение звёзд с вероятностью 87%*";
        }
        if(userService.userIsEnglish(currentUser)){
            answer = "👋🏻"+ currentUser.getFirstName() +", Welcome to MINES HACK BOT v4.0!\n\n" +
                    "This is the fourth, perfect version of our bot!\n\n" +
                    "💣*Mines* is a game of 1WIN's best betting game based on the classic \"Sapper\".\n" +
                    "*Your goal is to open safe cells and avoid falling into traps*.\n\n" +
                    "🧠Our bot is based on neural network *from OpenAI*.\n" +
                    "*It took us a long time to get the bot to be able to predict the location of mines with 95% probability by mathematical calculations.*";
        }
        if (userService.userIsHindi(currentUser)) {
            answer = "👋🏻" + currentUser.getFirstName() + ", 🔸MINES BitsGap🔸 में आपका स्वागत है!\n\n" +
                    "💣Mines - 1win बुकमेकर में एक जुआ खेलने वाला खेल है, जो क्लासिक 'सैपर' पर आधारित है।\n" +
                    "आपका लक्ष्य सुरक्षित कोशिकाओं को खोलना और जाल में न फंसना है।\n\n" +
                    "*हमारा बॉट Bitsgap की न्यूरल नेटवर्क पर आधारित है*\n" +
                    "*यह 87% संभावना के साथ सितारों की स्थिति का अनुमान लगा सकता है*";
        }
        if (userService.userIsBrazilian(currentUser)) {
            answer = "👋🏻" + currentUser.getFirstName() + ", Bem-vindo ao 🔸MINES BitsGap🔸!\n\n" +
                    "💣Mines é um jogo de apostas no bookmaker 1win, baseado no clássico 'Sapper'.\n" +
                    "Seu objetivo é abrir células seguras e evitar armadilhas.\n\n" +
                    "*Nosso bot é baseado na rede neural do Bitsgap*\n" +
                    "*Ele pode prever a localização das minas com uma probabilidade de 87%*";
        }
        if (userService.userIsSpanish(currentUser)) {
            answer = "👋🏻" + currentUser.getFirstName() + ", ¡Bienvenido a 🔸MINES BitsGap🔸!\n\n" +
                    "💣Mines es un juego de apuestas en el bookmaker 1win, basado en el clásico 'Sapper'.\n" +
                    "Tu objetivo es abrir celdas seguras y evitar las trampas.\n\n" +
                    "*Nuestro bot está basado en la red neuronal de Bitsgap*\n" +
                    "*Puede predecir la ubicación de las minas con una probabilidad del 87%*";
        }
        if (userService.userIsUzbek(currentUser)) {
            answer = "👋🏻" + currentUser.getFirstName() + ", 🔸MINES BitsGap🔸 ga xush kelibsiz!\n\n" +
                    "💣Mines - bu 1win bukmeykeridagi qimor o'yini, klassik 'Sapper' ga asoslangan.\n" +
                    "Sizning maqsadingiz xavfsiz hujayralarni ochish va tuzoqlarga tushmaslik.\n\n" +
                    "*Bizning botimiz Bitsgap ning neyron tarmog'iga asoslangan*\n" +
                    "*U yulduzlar joylashishini 87% ehtimol bilan bashorat qilishi mumkin*";
        }
        if (userService.userIsAzerbaijani(currentUser)) {
            answer = "👋🏻" + currentUser.getFirstName() + ", 🔸MINES BitsGap🔸-ə xoş gəlmisiniz!\n\n" +
                    "💣Mines 1win bukmekerində klassik 'Sapper' əsasında qurulmuş bir qumar oyunudur.\n" +
                    "Sizin məqsədiniz təhlükəsiz hüceyrələri açmaq və tələlərdən qaçmaqdır.\n\n" +
                    "*Bizim botumuz Bitsgap-in neyron şəbəkəsinə əsaslanır*\n" +
                    "*O, ulduzların yerini 87% ehtimalla proqnozlaşdıra bilər*";
        }
        if (userService.userIsTurkish(currentUser)) {
            answer = "👋🏻" + currentUser.getFirstName() + ", 🔸MINES BitsGap🔸'a hoş geldiniz!\n\n" +
                    "💣Mines, 1win bahis sitesinde klasik 'Sapper' oyununa dayanan bir kumar oyunudur.\n" +
                    "Amacınız güvenli hücreleri açmak ve tuzaklardan kaçınmaktır.\n\n" +
                    "*Botumuz Bitsgap'in sinir ağına dayanmaktadır*\n" +
                    "*Yıldızların yerini %87 olasılıkla tahmin edebilir*";
        }
        if (userService.userIsPortuguese(currentUser)) {
            answer = "👋🏻" + currentUser.getFirstName() + ", Bem-vindo ao 🔸MINES BitsGap🔸!\n\n" +
                    "💣Mines é um jogo de apostas no bookmaker 1win, baseado no clássico 'Sapper'.\n" +
                    "O seu objetivo é abrir células seguras e evitar armadilhas.\n\n" +
                    "*O nosso bot é baseado na rede neural do Bitsgap*\n" +
                    "*Ele pode prever a localização das minas com uma probabilidade de 87%*";
        }
        if (userService.userIsArabic(currentUser)) {
            answer = "👋🏻" + currentUser.getFirstName() + ", مرحبًا بك في 🔸MINES BitsGap🔸!\n\n" +
                    "💣Mines هي لعبة قمار في 1win bookmaker، تعتمد على لعبة 'Sapper' الكلاسيكية.\n" +
                    "هدفك هو فتح الخلايا الآمنة وتجنب الفخاخ.\n\n" +
                    "*بوتنا مبني على شبكة عصبية من Bitsgap*\n" +
                    "*يمكنه التنبؤ بمواقع النجوم بنسبة دقة 87%*";
        }



        if (userService.userIsRussian(currentUser)){
            if (currentUser.getIsVerify()){
                InlineKeyboardMarkup inlineKeyboardMarkup = MinesKeyboard.mainMinesMenuIfUserAlreadyRegistered1(urlService,currentUser.getLanguage());
                botActions.sendMessageWithPhotoAndKeyboard(chatId, answer,"/imagesrussian/mines.jpg",inlineKeyboardMarkup);
            }
            else {
                InlineKeyboardMarkup inlineKeyboardMarkup = MinesKeyboard.mainMinesMenuIfUserNotRegistered1(urlService,currentUser.getLanguage());
                botActions.sendMessageWithPhotoAndKeyboard(chatId, answer,"/imagesrussian/mines.jpg",inlineKeyboardMarkup);
            }
        }
        else {
            if (currentUser.getIsVerify()){
                InlineKeyboardMarkup inlineKeyboardMarkup = MinesKeyboard.mainMinesMenuIfUserAlreadyRegistered1(urlService,currentUser.getLanguage());
                botActions.sendMessageWithPhotoAndKeyboard(chatId, answer,"/imagesenglish/mines.jpg",inlineKeyboardMarkup);
            }
            else {
                InlineKeyboardMarkup inlineKeyboardMarkup = MinesKeyboard.mainMinesMenuIfUserNotRegistered1(urlService,currentUser.getLanguage());
                botActions.sendMessageWithPhotoAndKeyboard(chatId, answer,"/imagesenglish/mines.jpg",inlineKeyboardMarkup);
            }
        }

        if(callbackQuery != null && callbackQuery.getId()!= null){
            botActions.handleCallbackQuery(callbackQuery);
        }
    }
}