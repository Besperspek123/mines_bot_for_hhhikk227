package com.example.tgbot.service;

import com.example.tgbot.config.BotConfig;
import com.example.tgbot.config.CustomBotConfig;
import com.example.tgbot.entity.User;
import com.example.tgbot.interfaces.*;
import com.example.tgbot.keyboard.games.MinesKeyboard;
import com.example.tgbot.service.admin.SendBotStatCommand;
import com.example.tgbot.service.admin.SendHelpForAdminMessageCommand;
import com.example.tgbot.service.admin.SendMessageToUsersCommand;
import com.example.tgbot.service.main.ChooseGamesMenu;
import com.example.tgbot.service.main.ChoseTheLanguageMenu;
import com.example.tgbot.service.main.aviator.AviatorInstructionCommand;
import com.example.tgbot.service.main.aviator.GiveSignalAviatorCommand;
import com.example.tgbot.service.main.aviator.GoToAviatorGameCommand;
import com.example.tgbot.service.main.aviator.InfoAboutAviatorRegistrationMenu;
import com.example.tgbot.service.main.coinflip.CoinInstructionCommand;
import com.example.tgbot.service.main.coinflip.GiveSignalCoinCommand;
import com.example.tgbot.service.main.coinflip.GoToCoinFlipGameCommand;
import com.example.tgbot.service.main.coinflip.InfoAboutCoinRegistrationMenu;
import com.example.tgbot.service.main.luckyjet.GiveSignalLuckyJetCommand;
import com.example.tgbot.service.main.luckyjet.GoToLuckyJetGameCommand;
import com.example.tgbot.service.main.luckyjet.InfoAboutLuckyJetRegistrationMenu;
import com.example.tgbot.service.main.luckyjet.LuckyJetInstructionCommand;
import com.example.tgbot.service.main.mines.*;
import com.example.tgbot.service.message.WelcomeMessageCommand;
import com.example.tgbot.service.user.DeleteDataForCurrentUserCommand;
import com.example.tgbot.service.user.SendMessageWithInfoAboutUserCommand;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMember;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.io.File;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Slf4j
@PropertySource("/application.properties")
public class TelegramBot extends TelegramLongPollingBot implements BotActions {
    private static  final String mainSupportUsername = "Besperspek99999";
    private static final int THREAD_POOL_SIZE = 10; // Задайте количество потоков в пуле
    private static final int MESSAGES_PER_SECOND_LIMIT = 30; // Лимит сообщений в секунду

    private final Map<String, String> photoCache = new HashMap<>();

    private Map<String, String> videoCache = new HashMap<>();

    private final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);


    @Autowired
    UserService userService;

    @Autowired
    CustomBotConfig customBotConfig;

    @Autowired
    UrlService urlService;

    @Autowired
    RegistrationService registrationService;

    @Autowired
    DepositService depositService;

    @Autowired
    PromoService promoService;

    @Autowired
    BotTokenService botTokenService;

    final BotConfig botConfig;

    public TelegramBot(BotConfig botConfig){
        this.botConfig = botConfig;

    }
    @Override
    public String getBotUsername() {
        return botConfig.getName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {


        if(update.hasMessage() && !update.getMessage().hasPhoto()){
            if (update.getMessage().getText().equals("/start")) {
                long chatId = update.getMessage().getChatId();
                User user = userService.getInfoAboutUserByChatID(chatId);
                CallbackQuery callbackQuery = update.getCallbackQuery();
                if (user != null) {
                    if (user.getLanguage() == null) {
                        userService.setRussianLanguageToUser(chatId);
                        user.setLanguage("russian");
                    }
                    if (user.getIsEnteredToTheChannel()) {
                        CommandWithCallback goToChooseGameMenu = new ChooseGamesMenu(this, urlService, userService,promoService,depositService);
                        goToChooseGameMenu.execute(chatId, callbackQuery);
                    } else {
                        CommandWithFirstName userNotSubscribeToTheChannel = new WelcomeMessageCommand(this, urlService, userService);
                        userNotSubscribeToTheChannel.execute(chatId, update.getMessage().getChat().getFirstName());
                        handleCallbackQuery(callbackQuery);
                    }
                } else {
                    CommandWithoutCallback chooseLanguageMenu = new ChoseTheLanguageMenu(this);
                    chooseLanguageMenu.execute(chatId);
                }
            }
        }

        if (update.hasCallbackQuery()){
            String data = update.getCallbackQuery().getData();
            if (data.equals("russian") || data.equals("english") || data.equals("hindi") || data.equals("brazilian") ||
                    data.equals("spanish") || data.equals("uzbek") || data.equals("azerbaijani") || data.equals("turkish") ||
                    data.equals("portuguese") || data.equals("arabic")) {
                User user = userService.getInfoAboutUserByChatID(update.getCallbackQuery().getMessage().getChatId());
                if(user == null){
                    System.out.println("Создание пользователя");
                    userService.saveUser(update.getCallbackQuery(),update.getCallbackQuery().getData());
                    handleCallbackQuery(update.getCallbackQuery());
                    if(isUserSubscribedToChannel(urlService.getTelegramChannelUrl(userService.getInfoAboutUserByChatID(update.getCallbackQuery().getMessage().getChatId())),update.getCallbackQuery().getFrom().getId())){
                        CommandWithCallback goToChooseGameMenu = new ChooseGamesMenu(this,urlService,userService,promoService,depositService);
                        goToChooseGameMenu.execute(update.getCallbackQuery().getMessage().getChatId(),update.getCallbackQuery());
                    }

                }
            }
        }



        if (update.hasMessage() && update.getMessage().hasText() && !update.getMessage().getText().equals("/start")) {
            long chatId = update.getMessage().getChatId();
            Long userIdForSubscribe = update.getMessage().getFrom().getId();
            User user = userService.getInfoAboutUserByChatID(chatId);
            String userUsername = update.getMessage().getFrom().getUserName();
            CallbackQuery callbackQuery = new CallbackQuery();

//            if (userUsername != null ){
//                if(user == null){
//
//                    try {
//                        userService.saveUser(update);
//                        user = userService.getInfoAboutUserByChatID(chatId);
//                        log.info("New user has been saved with username: " + update.getMessage().getFrom().getUserName());
//                    }
//                    catch (Exception e){
//                        log.error(e.getMessage());
//                    }
//                }
//
//            }

            if(user != null){
                if (user.getAwaitingMode() == null) {
                    userService.setDefaultAwaitingMode(chatId);
                    user.setAwaitingMode(false);
                }
                if (user.getIsVerify() == null) {
                    userService.setVerifyIsDefault(chatId);
                    user.setIsVerify(false);
                }
                if(user.getIsDeposit() == null){
                    userService.setDefaultIsDeposit(chatId);
                    user.setIsDeposit(false);
                }
                if (user.getIsParticipantInLottery() == null) {
                    userService.setIsParticipantIsDefault(chatId);
                    user.setIsParticipantInLottery(false);
                }
                if (user.getLanguage() == null){
                    userService.setRussianLanguageToUser(chatId);
                    user.setLanguage("russian");
                }
                if(user.getIsAdministrator() == null){
                    if (user.getUsername() != null){
                        if(user.getUsername().equals(mainSupportUsername)){
                            userService.addAdministrationRoleToUser(user.getUsername());
                            user.setIsAdministrator(true);
                        }
                        else {
                            userService.setIsAdministratorToDefault(chatId);
                            user.setIsAdministrator(false);
                        }
                    }
                    else {
                        userService.setIsAdministratorToDefault(chatId);
                        user.setIsAdministrator(false);
                    }
                }
                else {
                    if (userUsername != null ){
                        if(userUsername.equalsIgnoreCase(mainSupportUsername) && !user.getIsAdministrator()){
                            user.setIsAdministrator(true);
                            userService.addAdministrationRoleToUser(userUsername);
                        }
                    }
                }


                Boolean awaitingMode = user.getAwaitingMode();
                if(awaitingMode != null){
                    if(awaitingMode){
                        String userId = update.getMessage().getText();
                        CommandWithoutCallback registrationMinesCommand = new TryMinesRegistrationCommand(this,userService,userId,registrationService,urlService);
                        registrationMinesCommand.execute(chatId);
                    }
                }
            }



//            else if (user == null){
//                userService.saveUser(update);
//                user = userService.getInfoAboutUserByChatID(chatId);
//            }


            if(user!= null){
                if(isUserSubscribedToChannel(urlService.getTelegramChannelUrl(user),userIdForSubscribe)){
                    user.setIsEnteredToTheChannel(true);
                    userService.setIsEnteredToTheChannelIsTrue(chatId);
                }
                else {
                    user.setIsEnteredToTheChannel(false);
                    userService.setIsEnteredToTheChannelIsFalse(userIdForSubscribe);
                    CommandWithFirstName userNotSubscribeToTheChannel = new WelcomeMessageCommand(this,urlService,userService);
                    userNotSubscribeToTheChannel.execute(chatId,update.getMessage().getChat().getFirstName());
                }
            }





            if (update.hasCallbackQuery()) {
                callbackQuery = update.getCallbackQuery();
            }
            String messageText = update.getMessage().getText();



            if(user != null){
                if(user.getIsEnteredToTheChannel()){
                    switch (messageText) {
                        case "/mines":
                            CommandWithCallback toToMinesGameCommand = new GoToMinesGameCommand(this,userService,urlService);
                            toToMinesGameCommand.execute(chatId,callbackQuery);
                            break;

                    }
                }
            }

            switch (messageText){
                case "/mydata":
                    if(user != null){
                        CommandWithUser sendMessageWithInfoAboutUser = new SendMessageWithInfoAboutUserCommand(this);
                        sendMessageWithInfoAboutUser.execute(chatId,user);
                        break;
                    }
                    else {
                        if (user.getLanguage().equals("russian")){
                            sendMessageWithoutCallbackQuery(chatId,"В боте нету ваших данных");
                        }
                        else {
                            sendMessageWithoutCallbackQuery(chatId, "There is no data about you in the bot");
                        }
                    }

                    break;
                case "/stat":
                    if(user.getIsAdministrator()){
                        CommandWithChatId a = new SendBotStatCommand(this,userService);
                        a.execute(chatId);
                    }
                    else {
                        if (user.getLanguage().equals("russian")){
                            sendMessageWithoutCallbackQuery(chatId,"У вас нету доступа к этой команде");
                        }
                        else {
                            sendMessageWithoutCallbackQuery(chatId, "You do not have access to this command");
                        }
                    }
                    break;
                case "/deletedata":
                    if(user != null){
                        CommandWithUser deleteDataForCurrentUser = new DeleteDataForCurrentUserCommand(this,userService);
                        deleteDataForCurrentUser.execute(chatId,user);
                        break;
                    }
                    else {
                        if (user.getLanguage().equals("russian")){
                            sendMessageWithoutCallbackQuery(chatId,"В боте нету ваших данных");
                        }
                        else {

                            sendMessageWithoutCallbackQuery(chatId, "There is no data about you in the bot");
                        }

                    }
                    break;
                case "/ru":
                    userService.switchToRussianLanguage(userService.getInfoAboutUserByChatID(chatId));
                    sendMessageWithoutCallbackQuery(chatId, "\uD83C\uDDF7\uD83C\uDDFAЯзык был изменен на Русский\uD83C\uDDF7\uD83C\uDDFA");
                    CommandWithCallback goToChooseGamesMenu = new ChooseGamesMenu(this, urlService, userService, promoService, depositService);
                    goToChooseGamesMenu.execute(chatId, callbackQuery);
                    break;
                case "/eng":
                    userService.switchToEnglishLanguage(userService.getInfoAboutUserByChatID(chatId));
                    sendMessageWithoutCallbackQuery(chatId, "\uD83C\uDDEC\uD83C\uDDE7The language has been changed to English\uD83C\uDDEC\uD83C\uDDE7");
                    CommandWithCallback goToChooseGamesMenu1 = new ChooseGamesMenu(this, urlService, userService, promoService, depositService);
                    goToChooseGamesMenu1.execute(chatId, callbackQuery);
                    break;
                case "/hin":
                    userService.switchToHindiLanguage(userService.getInfoAboutUserByChatID(chatId));
                    sendMessageWithoutCallbackQuery(chatId, "\uD83C\uDDEE\uD83C\uDDF3 भाषा हिंदी में बदल दी गई है \uD83C\uDDEE\uD83C\uDDF3");
                    CommandWithCallback goToChooseGamesMenu2 = new ChooseGamesMenu(this, urlService, userService, promoService, depositService);
                    goToChooseGamesMenu2.execute(chatId, callbackQuery);
                    break;
                case "/bra":
                    userService.switchToBrazilianPortugueseLanguage(userService.getInfoAboutUserByChatID(chatId));
                    sendMessageWithoutCallbackQuery(chatId, "\uD83C\uDDE7\uD83C\uDDF7 O idioma foi alterado para Português Brasileiro \uD83C\uDDE7\uD83C\uDDF7");
                    CommandWithCallback goToChooseGamesMenu3 = new ChooseGamesMenu(this, urlService, userService, promoService, depositService);
                    goToChooseGamesMenu3.execute(chatId, callbackQuery);
                    break;
                case "/spa":
                    userService.switchToSpanishLanguage(userService.getInfoAboutUserByChatID(chatId));
                    sendMessageWithoutCallbackQuery(chatId, "\uD83C\uDDEA\uD83C\uDDF8 El idioma ha sido cambiado a Español \uD83C\uDDEA\uD83C\uDDF8");
                    CommandWithCallback goToChooseGamesMenu4 = new ChooseGamesMenu(this, urlService, userService, promoService, depositService);
                    goToChooseGamesMenu4.execute(chatId, callbackQuery);
                    break;
                case "/uzb":
                    userService.switchToUzbekLanguage(userService.getInfoAboutUserByChatID(chatId));
                    sendMessageWithoutCallbackQuery(chatId, "\uD83C\uDDFA\uD83C\uDDFF Til O'zbekchaga o'zgartirildi \uD83C\uDDFA\uD83C\uDDFF");
                    CommandWithCallback goToChooseGamesMenu5 = new ChooseGamesMenu(this, urlService, userService, promoService, depositService);
                    goToChooseGamesMenu5.execute(chatId, callbackQuery);
                    break;
                case "/aze":
                    userService.switchToAzerbaijaniLanguage(userService.getInfoAboutUserByChatID(chatId));
                    sendMessageWithoutCallbackQuery(chatId, "\uD83C\uDDFA\uD83C\uDDFF Dil Azərbaycan dilinə dəyişdirildi \uD83C\uDDFA\uD83C\uDDFF");
                    CommandWithCallback goToChooseGamesMenu6 = new ChooseGamesMenu(this, urlService, userService, promoService, depositService);
                    goToChooseGamesMenu6.execute(chatId, callbackQuery);
                    break;
                case "/tur":
                    userService.switchToTurkishLanguage(userService.getInfoAboutUserByChatID(chatId));
                    sendMessageWithoutCallbackQuery(chatId, "\uD83C\uDDF9\uD83C\uDDF7 Dil Türkçe olarak değiştirildi \uD83C\uDDF9\uD83C\uDDF7");
                    CommandWithCallback goToChooseGamesMenu7 = new ChooseGamesMenu(this, urlService, userService, promoService, depositService);
                    goToChooseGamesMenu7.execute(chatId, callbackQuery);
                    break;
                case "/por":
                    userService.switchToPortugueseLanguage(userService.getInfoAboutUserByChatID(chatId));
                    sendMessageWithoutCallbackQuery(chatId, "\uD83C\uDDF5\uD83C\uDDF9 A língua foi alterada para Português \uD83C\uDDF5\uD83C\uDDF9");
                    CommandWithCallback goToChooseGamesMenu8 = new ChooseGamesMenu(this, urlService, userService, promoService, depositService);
                    goToChooseGamesMenu8.execute(chatId, callbackQuery);
                    break;
                case "/ara":
                    userService.switchToArabicLanguage(userService.getInfoAboutUserByChatID(chatId));
                    sendMessageWithoutCallbackQuery(chatId, "\uD83C\uDDE6\uD83C\uDDEA تم تغيير اللغة إلى العربية \uD83C\uDDE6\uD83C\uDDEA");
                    CommandWithCallback goToChooseGamesMenu9 = new ChooseGamesMenu(this, urlService, userService, promoService, depositService);
                    goToChooseGamesMenu9.execute(chatId, callbackQuery);
                    break;

            }
            String messageForSendAll = "📢 Как использовать команду "+messageText+" для рассылки:\n\n" +
                    "📝 Введите текст для рассылки сразу после команды "+messageText+" \nПример:\n" +
                    messageText+ " ваш текст здесь\n\n" +
                    "🔗 Если вы рассылаете просто текст:\n" +
                    "- Включен предпросмотр ссылок.\n" +
                    "- Parsemode markdown включен.\n" +
                    "- Вставляйте ссылки в полном размере для подгрузки предпросмотра.\n\n" +
                    "🖼️ При рассылке фото с текстом:\n" +
                    "- Используйте parsemode markdown.\n" +
                    "- Скрытые ссылки: поместите слово в квадратные скобки и ссылку в круглые.\n" +
                    "Пример: [ваше слово](ваша ссылка)\n\n" +
                    "⚠️ Внимание: При использовании полных ссылок в рассылке с фото, рассылка может не сработать.";

            if (messageText.startsWith("/sendall") && user.getIsAdministrator()){
                if ((messageText.equalsIgnoreCase("/sendall")
                        || messageText.equalsIgnoreCase("/sendallru")
                        || messageText.equalsIgnoreCase("/sendalleng")
                        || messageText.equalsIgnoreCase("/sendallwhonotdeposit")
                        || messageText.equalsIgnoreCase("/sendallruwhonotdeposit")
                        || messageText.equalsIgnoreCase("/sendallengwhonotdeposit")
                        || messageText.equalsIgnoreCase("/sendallwhonotregistered")
                        || messageText.equalsIgnoreCase("/sendallruwhonotregistered")
                        || messageText.equalsIgnoreCase("/sendallengwhonotregistered")
                        || messageText.equalsIgnoreCase("/sendallwhoisdeposit")
                        || messageText.equalsIgnoreCase("/sendallruwhoisdeposit")
                        || messageText.equalsIgnoreCase("/sendallengwhoisdeposit")
                )) {
                    sendMessageWithoutCallbackQuery(chatId, messageForSendAll);
                }
            }

            if (messageText.startsWith("/sendall") && user.getIsAdministrator()) {
                String broadcastText = "";
                List<User> usersSupplier = new ArrayList<>(); // Пустой список по умолчанию

                if (messageText.startsWith("/sendall ")) {
                    broadcastText = messageText.substring("/sendall ".length());
                    usersSupplier = userService.getAllUsers();
                }
                else if (messageText.startsWith("/sendallru ")) {
                    broadcastText = messageText.substring("/sendallru ".length());
                    usersSupplier = userService.getRuAllUsers();
                }
                else if (messageText.startsWith("/sendalleng ")) {
                    broadcastText = messageText.substring("/sendalleng ".length());
                    usersSupplier = userService.getAllNotRuUsers();
                }
                else if (messageText.startsWith("/sendallwhonotregistered ")) {
                    broadcastText = messageText.substring("/sendallwhonotregistered ".length());
                    usersSupplier = userService.getAllUsersWhoIsNotRegistered();
                }
                else if (messageText.startsWith("/sendallruwhonotregistered ")) {
                    broadcastText = messageText.substring("/sendallruwhonotregistered ".length());
                    usersSupplier = userService.getRuAllUsersWhoIsNotRegistered();
                }
                else if (messageText.startsWith("/sendallengwhonotregistered ")) {
                    broadcastText = messageText.substring("/sendallengwhonotregistered ".length());
                    usersSupplier = userService.getNotRuAllUsersWhoIsNotRegistered();
                }
                else if (messageText.startsWith("/sendallwhonotdeposit ")) {
                    broadcastText = messageText.substring("/sendallwhonotdeposit ".length());
                    usersSupplier = userService.getAllUsersWhoIsRegisteredButNotDeposit();
                }
                else if (messageText.startsWith("/sendallruwhonotdeposit ")) {
                    broadcastText = messageText.substring("/sendallruwhonotdeposit ".length());
                    usersSupplier = userService.getAllRuUsersWhoIsRegisteredButNotDeposit();
                }
                else if (messageText.startsWith("/sendallengwhonotdeposit ")) {
                    broadcastText = messageText.substring("/sendallengwhonotdeposit ".length());
                    usersSupplier = userService.getAllNotRuUsersWhoIsRegisteredButNotDeposit();
                }
                else if (messageText.startsWith("/sendallwhoisdeposit ")) {
                    broadcastText = messageText.substring("/sendallwhoisdeposit ".length());
                    usersSupplier = userService.geAllUsersWhoIsDeposit();
                }
                else if (messageText.startsWith("/sendallruwhoisdeposit ")) {
                    broadcastText = messageText.substring("/sendallruwhoisdeposit ".length());
                    usersSupplier = userService.geAllRuUsersWhoIsDeposit();
                }
                else if (messageText.startsWith("/sendallengwhoisdeposit ")) {
                    broadcastText = messageText.substring("/sendallengwhoisdeposit ".length());
                    usersSupplier = userService.geAllNotRuUsersWhoIsDeposit();
                }

                if (!broadcastText.isEmpty()) {
                    CommandWithBroadcastTextAndBroadcasterChatId sendToAllUsers = new SendMessageToUsersCommand(this, userService, usersSupplier);
                    sendToAllUsers.execute(broadcastText, chatId);
                    sendMessageWithoutCallbackQuery(chatId, "Рассылка выполнена.");
                }
            }


            String command = messageText.toLowerCase();

            if (user.getIsAdministrator()) {
                switch (command) {
                    case "/admin":
                        CommandWithChatId helpForAdminCommand = new SendHelpForAdminMessageCommand(this);
                        helpForAdminCommand.execute(chatId);
                        break;
                    case "/addadministrator":
                        sendMessageWithoutCallbackQuery(chatId, "👤 Добавить администратора:\n"
                                + "Используйте /addAdministrator @username или /addAdministrator https://t.me/username");
                        break;
                    case "/removeadministrator":
                        sendMessageWithoutCallbackQuery(chatId, "🚫 Удалить администратора:\n"
                                + "Используйте /removeAdministrator @username или /removeAdministrator https://t.me/username");
                        break;
                    case "/seturlonewin":
                        sendMessageWithoutCallbackQuery(chatId, "🔗 Установить URL OneWin ДЛЯ СНГ:\n"
                                + "/setUrlOneWin www.site.ru");
                        break;
                    case "/seturlonewineng":
                        sendMessageWithoutCallbackQuery(chatId, "🔗 Установить URL OneWin ДЛЯ ENG:\n"
                                + "/setUrlOneWinEng www.site.ru");
                        break;
                    case "/seturlchannel":
                        sendMessageWithoutCallbackQuery(chatId, "🔗 Установить URL СНГ канала для проверки подписки:\n"
                                + "/setUrlChannel https://t.me/channel");
                        break;
                    case "/seturlchanneleng":
                        sendMessageWithoutCallbackQuery(chatId, "🔗 Установить URL ENG канала для проверки подписки:\n"
                                + "/setUrlChannelEng https://t.me/channel");
                        break;
                    case "/setpromoonewin":
                        sendMessageWithoutCallbackQuery(chatId, "💰 Установить промокод OneWin для СНГ:\n"
                                + "/setPromoOneWin PROMO");
                        break;
                    case "/setpromoonewineng":
                        sendMessageWithoutCallbackQuery(chatId, "💰 Установить промокод OneWin для ENG:\n"
                                + "/setPromoOneWinEng PROMO");
                        break;
                    case "/setsupport":
                        sendMessageWithoutCallbackQuery(chatId, "💰 Установить ссылка на поддержку:\n" +
                                "Ссылка должна быть в формате https://t.me/username \n" +
                                "/setsupport url");
                        break;
                    case "/getinfoaboutuser":
                        sendMessageWithoutCallbackQuery(chatId, "👤 Получить информацию о пользователе:\n"
                                + "/getinfoaboutuser chatId или /getinfoaboutuser username");
                        break;
                    case "/getinfoaboutuserbyonewinid":
                        sendMessageWithoutCallbackQuery(chatId, "👤 Получить информацию о пользователе по 1win ID:\n"
                                + "/getinfoaboutuserbyonewinid onewinid ");
                        break;

                    case "/giveaccessforsignal":
                        sendMessageWithoutCallbackQuery(chatId, "🔓 Дать доступ к сигналам:\n"
                                + "/giveaccessforsignal chatId или /giveaccessforsignal username");
                        break;
                    case "/revokeaccessforsignal":
                        sendMessageWithoutCallbackQuery(chatId, "🔓 Забрать доступ к сигналам:\n"
                                + "/revokeaccessforsignal chatId или /revokeaccessforsignal username");
                        break;
                    case "/settokenbotforpostback":
                        sendMessageWithoutCallbackQuery(chatId, "🔑  Установить токен для постбек бота:\n"
                                + "/setTokenBotForPostback token");
                        break;
                    case "/setchatidforpostback":
                        String answerFromPostbackBotForExample = "{\n" +
                                "  \"ok\": true,\n" +
                                "  \"result\": [\n" +
                                "    {\n" +
                                "      \"update_id\": 144124749,\n" +
                                "      \"message\": {\n" +
                                "        \"message_id\": 100,\n" +
                                "        \"from\": {\n" +
                                "          \"id\": 741164095,\n" +
                                "          \"is_bot\": false,\n" +
                                "          \"first_name\": \"Генадий\",\n" +
                                "          \"username\": \"GENA22\",\n" +
                                "          \"language_code\": \"ru\",\n" +
                                "          \"is_premium\": true\n" +
                                "        },\n" +
                                "        \"chat\": {\n" +
                                "          \"id\": 741164095,\n" +
                                "          \"first_name\": \"Генадий\",\n" +
                                "          \"username\": \"GENA22\",\n" +
                                "          \"type\": \"private\"\n" +
                                "        },\n" +
                                "        \"date\": 1712003516,\n" +
                                "        \"text\": \"привет\"\n" +
                                "      }\n" +
                                "    }\n" +
                                "  ]\n" +
                                "}";
                        sendMessageWithoutCallbackQuery(chatId, "🔑  Установить chatID пользователя, который будет получать " +
                                "уведомления о регистрациях и депозитах пользователя:\n\n" +
                                "Чтобы узнать chatID своего пользователя в боте, в который будут приходить уведомления, нужно " +
                                "в браузере ввести вот такую команду: https://api.telegram.org/botТОКЕНБОТА/getUpdates\n" +
                                "Предварительно заменив слово ТОКЕНБОТА на токен бота, в котором вам будут приходить уведомления.\n\n" +
                                "Теперь напиши в этого бота любые пару сообщений и перезагрузи страницу.\n" +
                                "И вы увидите примерно вот такое содержание: " + answerFromPostbackBotForExample +
                                "\n\nВам нужен вот этот ID  \"id\": 741164095.\n" +
                                "Соответственно, вы пишите команду с этим ID. Например: /setchatidforpostback 741164095 \n\n" +
                                "/setchatidforpostback chatid");
                        break;
                    case "/getlogs":
                        sendLogFiles(chatId);
                        break;
                    default:
                        handleCommandWithParameter(messageText, chatId);
                        handleCommandWithParameterAndBotActions(messageText,chatId,this);
                        break;
                }
            }

        }


        if (update.hasMessage() && update.getMessage().hasPhoto()) {
            User user = userService.getInfoAboutUserByChatID(update.getMessage().getChatId());
            if (user != null && user.getIsAdministrator()) {
                long chatId = update.getMessage().getChatId();
                String messageText = update.getMessage().getCaption();

                // Определение типа команды и выбор нужной группы пользователей
                List<User> usersSupplier = null; // Для выбора группы пользователей
                String broadcastText = ""; // Текст для рассылки

                if (messageText.startsWith("/sendall ")) {
                    broadcastText = messageText.substring("/sendall ".length());
                    usersSupplier = userService.getAllUsers();
                }
                else if (messageText.startsWith("/sendallru ")) {
                    broadcastText = messageText.substring("/sendallru ".length());
                    usersSupplier = userService.getRuAllUsers();
                }
                else if (messageText.startsWith("/sendalleng ")) {
                    broadcastText = messageText.substring("/sendalleng ".length());
                    usersSupplier = userService.getAllNotRuUsers();
                }
                else if (messageText.startsWith("/sendallwhonotdeposit ")) {
                    broadcastText = messageText.substring("/sendallwhonotdeposit ".length());
                    usersSupplier = userService.getAllUsersWhoIsRegisteredButNotDeposit();
                }
                else if (messageText.startsWith("/sendallruwhonotdeposit ")) {
                    broadcastText = messageText.substring("/sendallruwhonotdeposit ".length());
                    usersSupplier = userService.getAllRuUsersWhoIsRegisteredButNotDeposit();
                }
                else if (messageText.startsWith("/sendallengwhonotdeposit ")) {
                    broadcastText = messageText.substring("/sendallengwhonotdeposit ".length());
                    usersSupplier = userService.getAllNotRuUsersWhoIsRegisteredButNotDeposit();
                }
                else if (messageText.startsWith("/sendallwhonotregistered ")) {
                    broadcastText = messageText.substring("/sendallwhonotregistered ".length());
                    usersSupplier = userService.getAllUsersWhoIsNotRegistered();
                }
                else if (messageText.startsWith("/sendallruwhonotregistered ")) {
                    broadcastText = messageText.substring("/sendallruwhonotregistered ".length());
                    usersSupplier = userService.getRuAllUsersWhoIsNotRegistered();
                }
                else if (messageText.startsWith("/sendallengwhonotregistered ")) {
                    broadcastText = messageText.substring("/sendallengwhonotregistered ".length());
                    usersSupplier = userService.getNotRuAllUsersWhoIsNotRegistered();
                }
                else if (messageText.startsWith("/sendallwhoisdeposit ")) {
                    broadcastText = messageText.substring("/sendallwhoisdeposit ".length());
                    usersSupplier = userService.geAllUsersWhoIsDeposit();
                }
                else if (messageText.startsWith("/sendallruwhoisdeposit ")) {
                    broadcastText = messageText.substring("/sendallruwhoisdeposit ".length());
                    usersSupplier = userService.geAllRuUsersWhoIsDeposit();
                }
                else if (messageText.startsWith("/sendallengwhoisdeposit ")) {
                    broadcastText = messageText.substring("/sendallengwhoisdeposit ".length());
                    usersSupplier = userService.geAllNotRuUsersWhoIsDeposit();
                }

                // Добавьте здесь другие условия для разных типов команд

                if (usersSupplier != null) {
                    List<PhotoSize> photos = update.getMessage().getPhoto();
                    PhotoSize photo = photos.stream().max(Comparator.comparing(PhotoSize::getFileSize)).orElse(null);
                    if (photo != null) {
                        String fileId = photo.getFileId();
                        GetFile getFileRequest = new GetFile();
                        getFileRequest.setFileId(fileId);
                        try {
                            org.telegram.telegrambots.meta.api.objects.File file = execute(getFileRequest);
                            InputStream photoStream = downloadFileAsStream(file);
                            byte[] photoBytes = IOUtils.toByteArray(photoStream);

                            // Используйте выбранный список пользователей для рассылки
                            List<User> users = usersSupplier;
                            // Здесь ваш метод рассылки, который использует список пользователей `users`, текст `broadcastText` и фото `photoBytes`
                            SendMessageToUsersCommand sendMessageToUsersCommand = new SendMessageToUsersCommand(this,userService,usersSupplier);
                            sendMessageToUsersCommand.execute(chatId, broadcastText, photoBytes);

                            sendMessageWithoutCallbackQuery(chatId, "Рассылка выполнена.");
                        } catch (TelegramApiException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    // Сообщение о том, что пользователь не имеет прав на использование команды или команда не распознана
                    String noPermissionMessage = user.getLanguage().equals("russian") ? "У вас нету прав на эту команду" : "You do not have permission for this command";
                    sendMessageWithoutCallbackQuery(chatId, noPermissionMessage);
                }
            }
        }

        if (update.hasCallbackQuery()) {

            System.out.println("Callback was ejected");

            CallbackQuery callbackQuery = update.getCallbackQuery();
            String data = callbackQuery.getData();
            String oneWinId = null;
            long chatId = callbackQuery.getMessage().getChat().getId();
            Long userIdForSubscribe = callbackQuery.getMessage().getFrom().getId();

            User user = userService.getInfoAboutUserByChatID(callbackQuery.getMessage().getChatId());
            Long userId = update.getCallbackQuery().getFrom().getId();

            if (user == null) {
                sendMessageWithoutCallbackQuery(chatId, "You are not registered. Press /start to register\n"
                        + "This is necessary for the bot to function properly");
            }

            String firstUsername = callbackQuery.getMessage().getChat().getFirstName();



            if(user != null) {
                if (callbackQuery.getFrom().getUserName() != null) {
                    if (!callbackQuery.getFrom().getUserName().equals(userService.getInfoAboutUserByChatID(chatId).getUsername())) {
                        user.setUsername(callbackQuery.getFrom().getUserName());
                        userService.saveUser(user);
                    }
                }


                if (callbackQuery.getFrom().getFirstName() != null && !callbackQuery.getFrom().getFirstName().contains("_")){
                    if (!callbackQuery.getFrom().getFirstName().equals(userService.getInfoAboutUserByChatID(chatId).getFirstName())){
                        user.setFirstName(callbackQuery.getFrom().getFirstName());
                        userService.saveUser(user);
                    }
                }
                if (user.getAwaitingMode() == null) {
                    userService.setDefaultAwaitingMode(chatId);
                    user.setAwaitingMode(false);
                }
                if (user.getIsVerify() == null) {
                    userService.setVerifyIsDefault(chatId);
                    user.setIsVerify(false);
                }
                if (user.getIsDeposit() == null) {
                    userService.setDefaultIsDeposit(chatId);
                    user.setIsDeposit(false);
                }
                if (user.getLanguage() == null){
                    userService.setRussianLanguageToUser(chatId);
                    user.setLanguage("russian");
                }
                if (user.getIsParticipantInLottery() == null) {
                    userService.setIsParticipantIsDefault(chatId);
                    user.setIsParticipantInLottery(false);
                }
            }

            if(isUserSubscribedToChannel(urlService.getTelegramChannelUrl(user),userId)){
                if(user!= null){
                    System.out.println("User subscribe set is true");
                    user.setIsEnteredToTheChannel(true);
                }
                System.out.println("send command to set subscribe true for user to the service");
                userService.setIsEnteredToTheChannelIsTrue(chatId);
            }
            else {
                if(user!= null){
                    user.setIsEnteredToTheChannel(false);
                    System.out.println("User subscribe set is false");
                }
                userService.setIsEnteredToTheChannelIsFalse(chatId);
                CommandWithFirstName userNotSubscribeToTheChannel = new WelcomeMessageCommand(this,urlService,userService);
                userNotSubscribeToTheChannel.execute(chatId,callbackQuery.getMessage().getChat().getFirstName());
                handleCallbackQuery(callbackQuery);
            }

            switch (data){

                case "check_the_subscription":
                        Long userId1 = update.getCallbackQuery().getFrom().getId();

                            boolean isSubscribed = isUserSubscribedToChannel(urlService.getTelegramChannelUrl(user), userId1);
                            if(isSubscribed){
                                user.setIsEnteredToTheChannel(true);
                                userService.setIsEnteredToTheChannelIsTrue(chatId);
                                CommandWithCallback toToMinesGameCommand = new GoToMinesGameCommand(this,userService,urlService);
                                toToMinesGameCommand.execute(chatId,callbackQuery);

                            }
                            handleCallbackQuery(callbackQuery);
                    break;
            }

            boolean isUserEnterToTelegramChannel =isUserSubscribedToChannel(urlService.getTelegramChannelUrl(user),userId);

            //MAIN
            if(isUserEnterToTelegramChannel) {
                switch (data) {
                    case "choose_games_menu":
                        CommandWithCallback gamesMenu = new ChooseGamesMenu(this, urlService, userService, promoService, depositService);
                        gamesMenu.execute(chatId, callbackQuery);
                        break;
                }
            }

            //Mines
            if(isUserEnterToTelegramChannel){
                switch (data){
                    case "mines_instruction":
                        CommandWithCallback minesInstructionCommand = new MinesInstructionCommand(this,urlService,promoService,userService);
                        minesInstructionCommand.execute(chatId,callbackQuery);
                        break;
                    case "mines_video_instruction":
                        InlineKeyboardMarkup inlineKeyboardMarkup = MinesKeyboard.mainForVideoInstruction(urlService.getOneWinUrl(user),user.getLanguage());
                        if(user.getLanguage().equals("russian")){
                            sendMessageWithVideoAndKeyboard(chatId,
                                    "Видеоинструкция по тому как использовать бота",
                                    "/video"+user.getLanguage()+"/1.mp4",
                                    "/video"+user.getLanguage()+"/1.jpeg",inlineKeyboardMarkup);
                        }
                        else {
                            sendMessageWithVideoAndKeyboard(chatId,
                                    "Video tutorial on how to use the bot",
                                    "/video"+user.getLanguage()+"/1.mp4",
                                    "/video"+user.getLanguage()+"/1.jpeg",inlineKeyboardMarkup);
                        }
                        handleCallbackQuery(callbackQuery);
                        break;
                    case "mines_registration":
                        CommandWithCallback minesRegistrationCommand = new InfoAboutMinesRegistrationMenu(this,urlService,userService,promoService );
                        minesRegistrationCommand.execute(chatId,callbackQuery);
                        break;

                    case "cancel_registration_in_mines":
                        userService.setDefaultAwaitingMode(chatId);
                        if (user.getLanguage().equals("russian")){
                            sendMessageWithCallbackQuery(chatId, "Регистрация была отменена", callbackQuery);
                        }
                        else{
                            sendMessageWithCallbackQuery(chatId, "Registration has been cancelled", callbackQuery);
                        }
                        CommandWithCallback goToMainMenuMines = new ChooseGamesMenu(this, urlService,userService,promoService,depositService);
                        goToMainMenuMines.execute(chatId, callbackQuery);
                        handleCallbackQuery(callbackQuery);
                        break;
                    case "mines_give_signal":
                        CommandWithCallback giveSignalMinesCommand = new GiveSignalMinesCommand(this,urlService,userService,promoService,depositService);
                        giveSignalMinesCommand.execute(chatId,callbackQuery);
                        break;
                    case "mines":
                        CommandWithCallback goToMinesGameCommand = new GoToMinesGameCommand(this,userService,urlService);
                        goToMinesGameCommand.execute(chatId,callbackQuery);
                        break;
                    case "mines_try_to_registration":
                        String answer = "";
                        if(user.getIsVerify()){
                            if (userService.userIsRussian(user)){
                                answer = "Вы уже зарегистрированы";
                            }
                            else {
                                answer = "You are already registered";
                            }
                            sendMessageWithoutCallbackQuery(chatId,answer);
                            CommandWithCallback giveSignalMinesCommand1 = new GiveSignalMinesCommand(this,urlService,userService,promoService,depositService);
                            giveSignalMinesCommand1.execute(chatId,callbackQuery);
                            handleCallbackQuery(callbackQuery);
                        }
                        else {
                            userService.enableAwaitingMode(chatId);
                            if (userService.userIsRussian(user)){
                                answer = "Введите ваш 1WIN ID";
                            }
                            else {
                                answer = "Write your 1WIN ID";
                            }
                            InlineKeyboardMarkup inlineKeyboardMarkup1 = MinesKeyboard.menuWithCancelLinkForCheckID(user.getLanguage(),urlService);
                            sendMessageWithPhotoAndKeyboard(chatId,answer,"/images/onewinid.jpg",inlineKeyboardMarkup1);
                            handleCallbackQuery(callbackQuery);
                        }

                        break;

                }
            }

            //Luckyjet
            if(isUserEnterToTelegramChannel){
                switch (data) {
                    case "luckyjet":
                        CommandWithCallback goToLuckyJetGameCommand = new GoToLuckyJetGameCommand(this, userService, urlService);
                        goToLuckyJetGameCommand.execute(chatId, callbackQuery);
                        break;
                    case "luckyjet_give_signal":
                        CommandWithCallback giveSignalLuckyJetCommand = new GiveSignalLuckyJetCommand(this,urlService,userService,promoService,depositService);
                        giveSignalLuckyJetCommand.execute(chatId,callbackQuery);
                        break;
                    case "luckyjet_instruction":
                        CommandWithCallback luckyJetInstructionCommand = new LuckyJetInstructionCommand(this,urlService,promoService,userService);
                        luckyJetInstructionCommand.execute(chatId,callbackQuery);
                        break;
                    case "cancel_registration_in_luckyjet":
                        userService.setDefaultAwaitingMode(chatId);
                        if (user.getLanguage().equals("russian")){
                            sendMessageWithCallbackQuery(chatId, "Регистрация была отменена", callbackQuery);
                        }
                        else{
                            sendMessageWithCallbackQuery(chatId, "Registration has been cancelled", callbackQuery);
                        }
                        CommandWithCallback goToMainMenuLuckyJet = new ChooseGamesMenu(this, urlService,userService,promoService,depositService);
                        goToMainMenuLuckyJet.execute(chatId, callbackQuery);
                        handleCallbackQuery(callbackQuery);
                        break;
                    case "luckyjet_registration":
                        CommandWithCallback luckyJetRegistrationCommand = new InfoAboutLuckyJetRegistrationMenu(this,urlService,userService,promoService );
                        luckyJetRegistrationCommand.execute(chatId,callbackQuery);
                        break;
                    case "luckyjet_try_to_registration":
                        String answer = "";
                        if(user.getIsVerify()){
                            if (userService.userIsRussian(user)){
                                answer = "Вы уже зарегистрированы";
                            }
                            else {
                                answer = "You are already registered";
                            }
                            sendMessageWithoutCallbackQuery(chatId,answer);
                            CommandWithCallback giveSignalMinesCommand1 = new GiveSignalLuckyJetCommand(this,urlService,userService,promoService,depositService);
                            giveSignalMinesCommand1.execute(chatId,callbackQuery);
                            handleCallbackQuery(callbackQuery);
                        }
                        else {
                            userService.enableAwaitingMode(chatId);
                            if (userService.userIsRussian(user)){
                                answer = "Введите ваш 1WIN ID";
                            }
                            else {
                                answer = "Write your 1WIN ID";
                            }
                            InlineKeyboardMarkup inlineKeyboardMarkup1 = MinesKeyboard.menuWithCancelLinkForCheckID(user.getLanguage(),urlService);
                            sendMessageWithPhotoAndKeyboard(chatId,answer,"/images/onewinid.jpg",inlineKeyboardMarkup1);
                            handleCallbackQuery(callbackQuery);
                        }

                        break;

                }

            }

            //Aviator
            if(isUserEnterToTelegramChannel){
                switch (data) {
                    case "aviator":
                        CommandWithCallback goToLuckyJetGameCommand = new GoToAviatorGameCommand(this, userService, urlService);
                        goToLuckyJetGameCommand.execute(chatId, callbackQuery);
                        break;
                    case "aviator_give_signal":
                        CommandWithCallback giveSignalLuckyJetCommand = new GiveSignalAviatorCommand(this,urlService,userService,promoService,depositService);
                        giveSignalLuckyJetCommand.execute(chatId,callbackQuery);
                        break;
                    case "aviator_instruction":
                        CommandWithCallback luckyJetInstructionCommand = new AviatorInstructionCommand(this,urlService,promoService,userService);
                        luckyJetInstructionCommand.execute(chatId,callbackQuery);
                        break;
                    case "cancel_registration_in_aviator":
                        userService.setDefaultAwaitingMode(chatId);
                        if (user.getLanguage().equals("russian")){
                            sendMessageWithCallbackQuery(chatId, "Регистрация была отменена", callbackQuery);
                        }
                        else{
                            sendMessageWithCallbackQuery(chatId, "Registration has been cancelled", callbackQuery);
                        }
                        CommandWithCallback goToMainMenuLuckyJet = new ChooseGamesMenu(this, urlService,userService,promoService,depositService);
                        goToMainMenuLuckyJet.execute(chatId, callbackQuery);
                        handleCallbackQuery(callbackQuery);
                        break;
                    case "aviator_registration":
                        CommandWithCallback luckyJetRegistrationCommand = new InfoAboutAviatorRegistrationMenu(this,urlService,userService,promoService );
                        luckyJetRegistrationCommand.execute(chatId,callbackQuery);
                        break;
                    case "aviator_try_to_registration":
                        String answer = "";
                        if(user.getIsVerify()){
                            if (userService.userIsRussian(user)){
                                answer = "Вы уже зарегистрированы";
                            }
                            else {
                                answer = "You are already registered";
                            }
                            sendMessageWithoutCallbackQuery(chatId,answer);
                            CommandWithCallback giveSignalMinesCommand1 = new GiveSignalAviatorCommand(this,urlService,userService,promoService,depositService);
                            giveSignalMinesCommand1.execute(chatId,callbackQuery);
                            handleCallbackQuery(callbackQuery);
                        }
                        else {
                            userService.enableAwaitingMode(chatId);
                            if (userService.userIsRussian(user)){
                                answer = "Введите ваш 1WIN ID";
                            }
                            else {
                                answer = "Write your 1WIN ID";
                            }
                            InlineKeyboardMarkup inlineKeyboardMarkup1 = MinesKeyboard.menuWithCancelLinkForCheckID(user.getLanguage(),urlService);
                            sendMessageWithPhotoAndKeyboard(chatId,answer,"/images/onewinid.jpg",inlineKeyboardMarkup1);
                            handleCallbackQuery(callbackQuery);
                        }

                        break;

                }
            }

            //Coinflip
            if(isUserEnterToTelegramChannel){
                switch (data) {
                    case "coin":
                        CommandWithCallback goToLuckyJetGameCommand = new GoToCoinFlipGameCommand(this, userService, urlService);
                        goToLuckyJetGameCommand.execute(chatId, callbackQuery);
                        break;
                    case "coin_give_signal":
                        CommandWithCallback giveSignalLuckyJetCommand = new GiveSignalCoinCommand(this,urlService,userService,promoService,depositService);
                        giveSignalLuckyJetCommand.execute(chatId,callbackQuery);
                        break;
                    case "coin_instruction":
                        CommandWithCallback luckyJetInstructionCommand = new CoinInstructionCommand(this,urlService,promoService,userService);
                        luckyJetInstructionCommand.execute(chatId,callbackQuery);
                        break;
                    case "cancel_registration_in_coin":
                        userService.setDefaultAwaitingMode(chatId);
                        if (user.getLanguage().equals("russian")){
                            sendMessageWithCallbackQuery(chatId, "Регистрация была отменена", callbackQuery);
                        }
                        else{
                            sendMessageWithCallbackQuery(chatId, "Registration has been cancelled", callbackQuery);
                        }
                        CommandWithCallback goToMainMenuLuckyJet = new ChooseGamesMenu(this, urlService,userService,promoService,depositService);
                        goToMainMenuLuckyJet.execute(chatId, callbackQuery);
                        handleCallbackQuery(callbackQuery);
                        break;
                    case "coin_registration":
                        CommandWithCallback luckyJetRegistrationCommand = new InfoAboutCoinRegistrationMenu(this,urlService,userService,promoService );
                        luckyJetRegistrationCommand.execute(chatId,callbackQuery);
                        break;
                    case "coin_try_to_registration":
                        String answer = "";
                        if(user.getIsVerify()){
                            if (userService.userIsRussian(user)){
                                answer = "Вы уже зарегистрированы";
                            }
                            else {
                                answer = "You are already registered";
                            }
                            sendMessageWithoutCallbackQuery(chatId,answer);
                            CommandWithCallback giveSignalMinesCommand1 = new GiveSignalCoinCommand(this,urlService,userService,promoService,depositService);
                            giveSignalMinesCommand1.execute(chatId,callbackQuery);
                            handleCallbackQuery(callbackQuery);
                        }
                        else {
                            userService.enableAwaitingMode(chatId);
                            if (userService.userIsRussian(user)){
                                answer = "Введите ваш 1WIN ID";
                            }
                            else {
                                answer = "Write your 1WIN ID";
                            }
                            InlineKeyboardMarkup inlineKeyboardMarkup1 = MinesKeyboard.menuWithCancelLinkForCheckID(user.getLanguage(),urlService);
                            sendMessageWithPhotoAndKeyboard(chatId,answer,"/images/onewinid.jpg",inlineKeyboardMarkup1);
                            handleCallbackQuery(callbackQuery);
                        }

                        break;

                }
            }

            //Ятожеротебалэтогометода
                if(user != null){
                    Boolean awaitingMode = user.getAwaitingMode();
                    if (awaitingMode != null && awaitingMode) { // Добавлена проверка на null
                        if(update.getMessage()!= null){
                            oneWinId = update.getMessage().getText();
                            if(oneWinId != null){
                                CommandWithoutCallback registrationMinesCommand = new TryMinesRegistrationCommand(this, userService, oneWinId,registrationService,urlService);
                                registrationMinesCommand.execute(chatId);
                            }
                        }

                    }
                }



            }

            }
















    public boolean isUserSubscribedToChannel(String chatId, Long userId) {
        if(customBotConfig.isCheckSubscriptionToTelegram()){
            try {
                if (chatId.startsWith("https://t.me/")) {
                    chatId = "@" + chatId.substring("https://t.me/".length());
                }

                GetChatMember getChatMember = new GetChatMember();
                getChatMember.setChatId(chatId);
                getChatMember.setUserId(userId);
                ChatMember chatMember = execute(getChatMember);
                String status = chatMember.getStatus();
                System.out.println(status);
                return "member".equals(status) || "administrator".equals(status) || "creator".equals(status);
            } catch (TelegramApiException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }




    @Override
    public void sendMessageWithCallbackQuery(long chatId, String textToSend, CallbackQuery callbackQuery) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        message.disableWebPagePreview();

        try {
            execute(message);
            handleCallbackQuery(callbackQuery);
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
        }
    }

    @Override
    public Integer sendMessageWithReturnIdMessage(long chatId, String textToSend, CallbackQuery callbackQuery) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        message.disableWebPagePreview();

        try {
            Message sentMessage = execute(message); // Этот вызов возвращает объект Message, который содержит информацию о сообщении
            handleCallbackQuery(callbackQuery);
            return sentMessage.getMessageId(); // Возвращаем ID сообщения
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
            return null; // Возвращаем null или выбрасываем исключение, если сообщение не было отправлено
        }
    }

    @Override
    public void editMessage(long chatId, int messageId, String newText) {
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(String.valueOf(chatId));
        editMessageText.setMessageId(messageId);
        editMessageText.setText(newText);

        try {
            execute(editMessageText); // Вызываем метод execute с нашим объектом EditMessageText
        } catch (TelegramApiException e) {
            log.error("Error occurred while editing message: " + e.getMessage());
        }
    }

    @Override
    public void deleteMessage(long chatId, int messageId) {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(String.valueOf(chatId));
        deleteMessage.setMessageId(messageId);

        try {
            execute(deleteMessage); // Вызываем метод execute с нашим объектом DeleteMessage
        } catch (TelegramApiException e) {
            log.error("Error occurred while deleting message: " + e.getMessage());
        }
    }

    @Override
    public void sendMessageWithoutCallbackQuery (long chatId, String textToSend){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        message.disableWebPagePreview();
        try {
            execute(message);

        }
        catch (TelegramApiException e){
            log.error("Error occurred: " + e.getMessage());
        }
    }

    @Override
    public void sendMessageWithInlineKeyboardAndParseMARKDOWN(long chatId, String textToSend, InlineKeyboardMarkup inlineKeyboardMarkup) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        message.setReplyMarkup(inlineKeyboardMarkup);
        message.disableWebPagePreview();
        message.setParseMode(ParseMode.MARKDOWN);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessageWithPhotoAndKeyboard(long chatId,String answer,String path,InlineKeyboardMarkup inlineKeyboardMarkup) {
        try {
            InputStream photoStream = getClass().getResourceAsStream(path);

            if (photoStream != null) {
                SendPhoto sendPhoto = new SendPhoto();
                sendPhoto.setChatId(Long.toString(chatId));
                sendPhoto.setPhoto(new InputFile(photoStream, "photo.jpg"));
                sendPhoto.setCaption(answer); // Установка текста сообщения как подписи к фотографии
                sendPhoto.setReplyMarkup(inlineKeyboardMarkup); // Установка клавиатуры
                sendPhoto.setParseMode(ParseMode.MARKDOWN);

                execute(sendPhoto); // Отправка фотографии с подписью и клавиатурой
            } else {
                log.error("Фотография не найдена: " + path);
            }
        } catch (TelegramApiException e) {
            // Обработка ошибок Telegram API
            e.printStackTrace();
        }
    }



    @Override
    public void sendMessageWithParseMarkdownAndCallback(long chatId, String text, CallbackQuery callbackQuery) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        message.disableWebPagePreview();
        message.setParseMode(ParseMode.MARKDOWN);

        try {
            execute(message);
            handleCallbackQuery(callbackQuery);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessageWithParseMARKDOWN(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        message.disableWebPagePreview();
        message.setParseMode(ParseMode.MARKDOWN);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessageWithPhoto(long chatId, String answer, String photoPath) {
        try {
            InputStream photoStream = getClass().getResourceAsStream(photoPath);

            if (photoStream != null) {
                SendPhoto sendPhoto = new SendPhoto();
                sendPhoto.setChatId(Long.toString(chatId));
                sendPhoto.setPhoto(new InputFile(photoStream, "photo.jpg"));

                Message message = execute(sendPhoto);
                message.setText(answer);
                // Обработка успешной отправки фото
            } else {
                log.error("Фотография не найдена: " + photoPath);
            }
        } catch (TelegramApiException e) {
            // Обработка ошибок Telegram API
            e.printStackTrace();
        }
    }


    @Override
    public void sendMessageWithInlineKeyboard(long chatId, String textToSend, InlineKeyboardMarkup inlineKeyboardMarkup) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        message.setReplyMarkup(inlineKeyboardMarkup);
        message.disableWebPagePreview();

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void handleCallbackQuery(CallbackQuery callbackQuery) {
        if (callbackQuery != null) {
            AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
            answerCallbackQuery.setCallbackQueryId(callbackQuery.getId());
            try {
                execute(answerCallbackQuery);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else {
            log.error("callbackquery равен нулю");
        }
    }


    @Override
    public void sendPhoto(Long chatId, byte[] photoBytes, String broadcastText, String photoFileId) {
        try {
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(chatId.toString());
            if (photoFileId != null) {
                sendPhoto.setPhoto(new InputFile(photoFileId)); // Отправляем используя file_id
            } else {
                InputFile inputFile = new InputFile(new ByteArrayInputStream(photoBytes), "photo.jpg");
                sendPhoto.setPhoto(inputFile);
            }
            sendPhoto.setCaption(broadcastText);

            execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean sendBroadcastMessage(long chatId, String textToSend, String username) {
        CompletableFuture<Boolean> sendMessageFuture = CompletableFuture.supplyAsync(() -> {
            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(chatId));
            message.setText(textToSend);
            message.setParseMode(ParseMode.MARKDOWN);
            try {
                execute(message);
                log.info("Пользователю " + username + " была отправлена рассылка");
                return true; // Если успешно отправлено
            } catch (TelegramApiRequestException e) {
                if (e.getErrorCode() == 403) { // 403 Forbidden - бот забанен
                    log.warn("Пользователь {} заблокировал бота. Попытка удаления пользователя из базы данных.", username);
                    try {
                        userService.deleteDataForUser(userService.getInfoAboutUserByChatID(chatId)); // Удалить пользователя из базы данных
                        log.warn("Пользователь {} удален из базы данных, так как забанил бота", username);
                    } catch (Exception ex) {
                        log.error("Ошибка при удалении пользователя {} из базы данных", username, ex);
                    }
                } else {
                    log.error("Ошибка при отправке сообщения пользователю {}: {}", username, e.getMessage());
                }
                return false; // Если не удалось отправить
            } catch (TelegramApiException e) {
                log.error(e.getMessage() + " Пользователю " + username + " не была отправлена рассылка ");
                return false; // Если не удалось отправить
            }
        }, executorService);

        return sendMessageFuture.join();
    }



    @Override
    public boolean sendBroadcastMessageWithPhoto(long chatId, String broadcastText, byte[] photoBytes) {
        CompletableFuture<Boolean> sendMessageFuture = CompletableFuture.supplyAsync(() -> {
            try {
                SendPhoto sendPhoto = new SendPhoto();
                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setPhoto(new InputFile(new ByteArrayInputStream(photoBytes), "photo.jpg"));
                sendPhoto.setCaption(broadcastText); // Установка текста сообщения как подписи к фотографии
                sendPhoto.setParseMode(ParseMode.MARKDOWN);

                execute(sendPhoto);
                log.info("Отправлено сообщение с фото в чат с ID: " + chatId);
                return true; // Если успешно отправлено
            } catch (TelegramApiRequestException e) {
                if (e.getErrorCode() == 403) { // 403 Forbidden - бот забанен
                    log.warn("Пользователь {} заблокировал бота. Попытка удаления пользователя из базы данных.", chatId);
                    try {
                        userService.deleteDataForUser(userService.getInfoAboutUserByChatID(chatId)); // Удалить пользователя из базы данных
                        log.warn("Пользователь {} удален из базы данных, так как забанил бота", chatId);
                    } catch (Exception ex) {
                        log.error("Ошибка при удалении пользователя {} из базы данных", chatId, ex);
                    }
                } else {
                    log.error("Ошибка при отправке сообщения с фото в чат с ID: " + chatId, e.getMessage());
                }
                return false; // Если не удалось отправить
            } catch (TelegramApiException e) {
                log.error("Ошибка при отправке сообщения с фото в чат с ID: " + chatId, e);
                return false; // Если не удалось отправить
            }
        }, executorService);

        return sendMessageFuture.join();
    }

    @Override
    public void sendMultiplePhotos(Long chatId, List<String> photoPaths) {
        try {
            List<InputMedia> media = new ArrayList<>();

            for (String photoPath : photoPaths) {
                InputStream photoStream = getClass().getResourceAsStream(photoPath);

                if (photoStream != null) {
                    InputMediaPhoto mediaPhoto = new InputMediaPhoto();
                    // Измените это, чтобы использовать уникальное имя для каждой фотографии
                    mediaPhoto.setMedia(photoStream, photoPath.substring(photoPath.lastIndexOf("/") + 1));
                    media.add(mediaPhoto);
                } else {
                    log.error("Фотография не найдена: " + photoPath);
                    // Обработка ошибки отсутствия фотографии
                }
            }

            SendMediaGroup sendMediaGroup = new SendMediaGroup();
            sendMediaGroup.setChatId(chatId.toString());
            sendMediaGroup.setMedias(media);

            execute(sendMediaGroup);
            // Обработка успешной отправки группы фото

        } catch (TelegramApiException e) {
            // Обработка ошибок Telegram API
            e.printStackTrace();
        }
    }

    @Override
    public void sendPhoto(Long chatId, String photoPath) {
        try {
            InputStream photoStream = getClass().getResourceAsStream(photoPath);

            if (photoStream != null) {
                SendPhoto sendPhoto = new SendPhoto();
                sendPhoto.setChatId(chatId.toString());
                sendPhoto.setPhoto(new InputFile(photoStream, "photo.jpg"));

                Message message = execute(sendPhoto);
                // Обработка успешной отправки фото
            } else {
                log.error("Фотография не найдена: " + photoPath);
            }
        } catch (TelegramApiException e) {
            // Обработка ошибок Telegram API
            e.printStackTrace();
        }
    }

    void handleCommandWithParameter(String messageText, Long chatId) {
        if (messageText.toLowerCase().startsWith("/seturlonewin ")) {
            String newLink = messageText.substring("/seturlonewin ".length());
            urlService.setRuOneWinUrl(newLink);
            sendMessageWithoutCallbackQuery(chatId, "Ссылка была заменена.");
        }
        else if (messageText.toLowerCase().startsWith("/seturlonewineng ")) {
            String newLink = messageText.substring("/seturlonewineng ".length());
            urlService.setEngOneWinUrl(newLink);
            sendMessageWithoutCallbackQuery(chatId, "Ссылка была заменена.");
        }
        else if (messageText.toLowerCase().startsWith("/seturlchannel ")) {
            String newLink = messageText.substring("/seturlchannel ".length()).trim();

            // Добавляем префикс 'https://t.me/' если его нет
            if (newLink.startsWith("@")) {
                newLink = "https://t.me/" + newLink.substring(1);
            } else if (!newLink.startsWith("https://t.me/")) {
                newLink = "https://t.me/" + newLink;
            }

            urlService.setRuChannelUrl(newLink);
            sendMessageWithoutCallbackQuery(chatId, "Ссылка была заменена.");
        } else if (messageText.toLowerCase().startsWith("/seturlchanneleng ")) {
            String newLink = messageText.substring("/seturlchanneleng ".length()).trim();

            // Добавляем префикс 'https://t.me/' если его нет
            if (newLink.startsWith("@")) {
                newLink = "https://t.me/" + newLink.substring(1);
            } else if (!newLink.startsWith("https://t.me/")) {
                newLink = "https://t.me/" + newLink;
            }

            urlService.setEngChannelUrl(newLink);
            sendMessageWithoutCallbackQuery(chatId, "Ссылка была заменена.");
        }
        else if (messageText.toLowerCase().startsWith("/setpromoonewin ")) {
            String newPromo = messageText.substring("/setpromoonewin ".length());
            promoService.setRuOneWinPromo(newPromo);
            sendMessageWithoutCallbackQuery(chatId, "Промокод был заменен.");
        }
        else if (messageText.toLowerCase().startsWith("/setpromoonewineng ")) {
            String newPromo = messageText.substring("/setpromoonewineng ".length());
            promoService.setEngOneWinPromo(newPromo);
            sendMessageWithoutCallbackQuery(chatId, "Промокод был заменен.");
        }
        else if (messageText.toLowerCase().startsWith("/addadministrator ")) {
            String username = messageText.substring("/addadministrator ".length()).trim();

            // Удаляем префиксы '@' и 'https://t.me/'
            if (username.startsWith("@")) {
                username = username.substring(1);
            } else if (username.startsWith("https://t.me/")) {
                username = username.substring("https://t.me/".length());
            }

            userService.addAdministrationRoleToUser(username);
            sendMessageWithoutCallbackQuery(chatId, "Администратор был добавлен");
        }
        else if (messageText.toLowerCase().startsWith("/setsupport ")) {
            String support = messageText.substring("/setsupport ".length());

            if (!support.isEmpty()){
                if (!support.toLowerCase().startsWith("https://t.me/")) {
                    if (support.startsWith("@")) {
                        support = support.substring(1); // Удаляем @ в начале
                    }
                    support = "https://t.me/" + support;
                }

                urlService.setSupport(support);
                sendMessageWithoutCallbackQuery(chatId, "Саппорт был заменен.");
            }
        }
        else if (messageText.toLowerCase().startsWith("/removeadministrator ")) {
            String username = messageText.substring("/removeadministrator ".length()).trim();

            // Удаляем префиксы '@' и 'https://t.me/'
            if (username.startsWith("@")) {
                username = username.substring(1);
            } else if (username.startsWith("https://t.me/")) {
                username = username.substring("https://t.me/".length());
            }

            userService.setIsAdministratorToDefault(username);
            sendMessageWithoutCallbackQuery(chatId, "Администратор был удалён");
        }

        else {
            // Здесь можно добавить обработку других команд с параметрами, если таковые будут.
        }

    }


    void handleCommandWithParameterAndBotActions(String messageText, Long chatId, BotActions botActions) {
        if (messageText.toLowerCase().startsWith("/getinfoaboutuser ")) {
            String substring = messageText.substring("/getinfoaboutuser ".length()).trim();

            // Удаляем префиксы '@' и 'https://t.me/'
            if (substring.startsWith("@")) {
                substring = substring.substring(1);
            } else if (substring.startsWith("https://t.me/")) {
                substring = substring.substring("https://t.me/".length());
            }

            SendMessageWithInfoAboutUserCommand sendMessageWithInfoAboutUserCommand = new SendMessageWithInfoAboutUserCommand(botActions);
            if (substring.matches("^\\d+$")) {
                sendMessageWithInfoAboutUserCommand.execute(chatId, userService.getInfoAboutUserByChatID(Long.parseLong(substring)));
            } else {
                sendMessageWithInfoAboutUserCommand.execute(chatId, userService.getInfoAboutUserByUsername(substring));
            }
        }

        if (messageText.toLowerCase().startsWith("/getinfoaboutuserbyonewinid ")) {
            String oneWinId = messageText.substring("/getinfoaboutuserbyonewinid ".length()).trim();

            SendMessageWithInfoAboutUserCommand sendMessageWithInfoAboutUserCommand = new SendMessageWithInfoAboutUserCommand(botActions);
            sendMessageWithInfoAboutUserCommand.execute(chatId,userService.getInfoAboutUserByOneWinId(oneWinId));
        }

        else if (messageText.toLowerCase().startsWith("/settokenbotforpostback ")) {
            String substring = messageText.substring("/settokenbotforpostback ".length());
            if(substring != null){
                botTokenService.setTokenForPostbackBot(substring);
                sendMessageWithoutCallbackQuery(chatId,"Новый токен для бота postback был заменен на " + substring);
            }
        }
        else if (messageText.toLowerCase().startsWith("/setchatidforpostback ")) {
            String substring = messageText.substring("/setchatidforpostback ".length());
            if(substring != null){
                botTokenService.setChatIdForPostbackBot(substring);
                sendMessageWithoutCallbackQuery(chatId,"Новый chatId пользователя для postback был заменен на " + substring);
            }
        }


        else if (messageText.toLowerCase().startsWith("/giveaccessforsignal ")) {
            String substring = messageText.substring("/giveaccessforsignal ".length()).trim();

            // Удаляем префиксы '@' и 'https://t.me/'
            if (substring.startsWith("@")) {
                substring = substring.substring(1);
            } else if (substring.startsWith("https://t.me/")) {
                substring = substring.substring("https://t.me/".length());
            }

            System.out.println(substring);

            if (substring.matches("^\\d+$")) {
                User user = userService.getInfoAboutUserByChatID(Long.parseLong(substring));
                if (user != null) {
                    user.setIsDeposit(true);
                    user.setIsVerify(true);
                    userService.saveUser(user);
                    sendMessageWithoutCallbackQuery(chatId, "Доступ к сигналам был добавлен");
                } else {
                    sendMessageWithoutCallbackQuery(chatId, "Такого пользователя не существует");
                }
            } else {
                User user = userService.getInfoAboutUserByUsername(substring);
                if (user != null) {
                    user.setIsDeposit(true);
                    user.setIsVerify(true);
                    userService.saveUser(user);
                    sendMessageWithoutCallbackQuery(chatId, "Доступ к сигналам был добавлен");
                } else {
                    sendMessageWithoutCallbackQuery(chatId, "Такого пользователя не существует");
                }
            }
        }

        else if (messageText.toLowerCase().startsWith("/revokeaccessforsignal ")) {
            String substring = messageText.substring("/revokeaccessforsignal ".length()).trim();

            // Удаляем префиксы '@' и 'https://t.me/'
            if (substring.startsWith("@")) {
                substring = substring.substring(1);
            } else if (substring.startsWith("https://t.me/")) {
                substring = substring.substring("https://t.me/".length());
            }

            if (substring.matches("^\\d+$")) {
                User user = userService.getInfoAboutUserByChatID(Long.parseLong(substring));
                if (user != null) {
                    user.setIsDeposit(false);
                    user.setIsVerify(false);
                    userService.saveUser(user);
                    sendMessageWithoutCallbackQuery(chatId, "Доступ к сигналам был убран");
                } else {
                    sendMessageWithoutCallbackQuery(chatId, "Такого пользователя не существует");
                }
            } else {
                User user = userService.getInfoAboutUserByUsername(substring);
                if (user != null) {
                    user.setIsDeposit(false);
                    user.setIsVerify(false);
                    userService.saveUser(user);
                    sendMessageWithoutCallbackQuery(chatId, "Доступ к сигналам был убран");
                } else {
                    sendMessageWithoutCallbackQuery(chatId, "Такого пользователя не существует");
                }
            }
        }

    }

    public void sendLogFiles(Long chatId) {
        // Путь к файлу логов
        String logFilePath = "/var/log/proj1ct.io/tg-simple-bot/app.log";
        File logFile = new File(logFilePath);

        if (logFile.exists()) {
            // Создание объекта InputFile
            InputFile inputFile = new InputFile(logFile);

            // Создание запроса на отправку документа
            SendDocument sendDocumentRequest = new SendDocument();
            sendDocumentRequest.setChatId(String.valueOf(chatId));
            sendDocumentRequest.setDocument(inputFile);
            sendDocumentRequest.setCaption("Вот ваш файл логов");

            try {
                // Отправка файла
                execute(sendDocumentRequest);
            } catch (TelegramApiException e) {
                e.printStackTrace();
                // Обработка ошибок отправки файла
            }
        } else {
            // Сообщение об ошибке, если файл не найден
            sendMessageWithoutCallbackQuery(chatId, "Файл логов не найден.");
        }
    }

    @Override
    public void sendMessageWithVideoAndKeyboard(long chatId, String answer, String path, String thumbNailPath, InlineKeyboardMarkup inlineKeyboardMarkup) {
        try {
            if (videoCache.containsKey(path)) {
                String fileId = videoCache.get(path);
                sendVideoUsingFileId(chatId, fileId, answer, inlineKeyboardMarkup);
            } else {
                InputStream videoStream = getClass().getResourceAsStream(path);
                InputStream thumbStream = getClass().getResourceAsStream(thumbNailPath); // Загрузка потока миниатюры
                if (videoStream != null && thumbStream != null) { // Проверка, что поток видео и миниатюры доступны
                    SendVideo sendVideo = new SendVideo();
                    sendVideo.setChatId(Long.toString(chatId));
                    sendVideo.setVideo(new InputFile(videoStream, "video.mp4"));
                    if(!thumbNailPath.isEmpty()){
                        sendVideo.setThumb(new InputFile(thumbStream, "thumb.jpg"));
                    } // Установка миниатюры
                    sendVideo.setCaption(answer);
                    sendVideo.setReplyMarkup(inlineKeyboardMarkup);
                    sendVideo.setParseMode(ParseMode.MARKDOWN);

                    Message message = execute(sendVideo); // Отправка видео с подписью и клавиатурой
                    // Сохраняем file_id в кэше
                    videoCache.put(path, message.getVideo().getFileId());
                } else {
                    if (videoStream == null) {
                        System.err.println("Видеофайл не найден: " + path);
                    }
                    if (thumbStream == null) {
                        System.err.println("Файл миниатюры не найден: " + thumbNailPath);
                    }
                }
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void sendVideo(Long chatId, String videoPath) {
        try {
            if (videoCache.containsKey(videoPath)) {
                // Если videoPath есть в кэше, используем сохраненный file_id для отправки
                String fileId = videoCache.get(videoPath);
                SendVideo sendVideo = new SendVideo();
                sendVideo.setChatId(chatId.toString());
                sendVideo.setVideo(new InputFile(fileId)); // Отправляем используя file_id

                // Дополнительные параметры, если необходимы
                // sendVideo.setCaption("Здесь ваша подпись");

                execute(sendVideo);
            } else {
                // Если file_id нет в кэше, загружаем и отправляем файл как раньше
                InputStream videoStream = getClass().getResourceAsStream(videoPath);
                if (videoStream != null) {
                    SendVideo sendVideo = new SendVideo();
                    sendVideo.setChatId(chatId.toString());
                    sendVideo.setVideo(new InputFile(videoStream, "video.mp4"));

                    // Дополнительные параметры, если необходимы
                    // sendVideo.setCaption("Здесь ваша подпись");

                    Message message = execute(sendVideo); // Отправка видео
                    // Сохраняем file_id в кэше для будущего использования
                    videoCache.put(videoPath, message.getVideo().getFileId());
                } else {
                    System.err.println("Видеофайл не найден: " + videoPath);
                }
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendVideoUsingFileId(long chatId, String fileId, String answer, InlineKeyboardMarkup inlineKeyboardMarkup) throws TelegramApiException {
        SendVideo sendVideo = new SendVideo();
        sendVideo.setChatId(Long.toString(chatId));
        sendVideo.setVideo(new InputFile(fileId));
        sendVideo.setCaption(answer);
        sendVideo.setReplyMarkup(inlineKeyboardMarkup);
        sendVideo.setParseMode(ParseMode.MARKDOWN);

        execute(sendVideo);
    }

    @Override
    public String uploadPhoto(long chatId, byte[] photoBytes) {
        try {
            InputFile inputFile = new InputFile(new ByteArrayInputStream(photoBytes), "photo.jpg");
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(chatId);
            sendPhoto.setPhoto(inputFile);
            sendPhoto.setCaption("Uploaded photo");

            Message message = execute(sendPhoto);
            // Возвращаем первый объект фото
            return message.getPhoto().get(0).getFileId();
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean sendBroadcastMessageWithPhotoUrl(long chatId, String broadcastText, String photoUrl) {
        try {
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(chatId);
            sendPhoto.setPhoto(new InputFile(photoUrl));
            sendPhoto.setCaption(broadcastText);

            execute(sendPhoto);
            return true;
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void sendPhotoWithFileId(Long chatId, String fileId, String broadcastText) {
        try {
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(chatId.toString());
            sendPhoto.setPhoto(new InputFile(fileId));
            sendPhoto.setCaption(broadcastText);

            execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendPhotoWithBytes(Long chatId, byte[] photoBytes, String broadcastText) {
        try {
            InputFile inputFile = new InputFile(new ByteArrayInputStream(photoBytes), "photo.jpg");
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(chatId.toString());
            sendPhoto.setPhoto(inputFile);
            sendPhoto.setCaption(broadcastText);

            execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }







}
