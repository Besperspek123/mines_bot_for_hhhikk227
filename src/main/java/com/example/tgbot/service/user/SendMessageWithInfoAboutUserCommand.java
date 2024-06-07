package com.example.tgbot.service.user;

import com.example.tgbot.entity.User;
import com.example.tgbot.interfaces.BotActions;
import com.example.tgbot.interfaces.CommandWithUser;

public class SendMessageWithInfoAboutUserCommand implements CommandWithUser {
    private final BotActions botActions;

    public SendMessageWithInfoAboutUserCommand(BotActions botActions) {
        this.botActions = botActions;
    }

    @Override
    public void execute(long chatId, User user) {
        if (user == null){
            botActions.sendMessageWithoutCallbackQuery(chatId,"Такого пользователя не существует");
        }
        else {
            String answer = "";
            if (user.getLanguage().equals("russian")){
                answer = "🆔 ID= " + user.getId() +"\n" +
                        "💬 chatId= " + user.getChatId() + "\n" +
                        "👤 username= " + user.getUsername() + "\n" +
                        "📅 Дата регистрации= " + user.getRegistrationDate() + "\n" +
                        "👋 Имя= " + user.getFirstName()+ "\n" +
                        "🔑 ID OneWin= " + user.getOneWinId()+ "\n" +
                        "✅ Зарегистрировался в onewin? - " + user.getIsVerify() + "\n" +
                        "💰 Сделал депозит? - " + user.getIsDeposit() + "\n" +
                        "📢 Состоит в телеграм канале? - " + user.getIsEnteredToTheChannel()+ "\n" +
                        "🛡 Администратор? - " + user.getIsAdministrator()+ "\n" +
                        "\uD83D\uDCF2 Язык - " + user.getLanguage();
            }
            else {
                answer = "🆔 ID= " + user.getId() + "\n" +
                        "💬 chatId= " + user.getChatId() + "\n" +
                        "👤 username= " + user.getUsername() + "\n" +
                        "📅 Registration date= " + user.getRegistrationDate() + "\n" +
                        "👋 Name= " + user.getFirstName() + "\n" +
                        "🔑 ID OneWin= " + user.getOneWinId() + "\n" +
                        "✅ Registered in onewin? - " + user.getIsVerify() + "\n" +
                        "💰 Made a deposit? - " + user.getIsDeposit() + "\n" +
                        "📢 Member of the telegram channel? - " + user.getIsEnteredToTheChannel() + "\n" +
                        "🛡 Administrator? - " + user.getIsAdministrator() + "\n" +
                        "📱 Language - " + user.getLanguage();
            }

            botActions.sendMessageWithoutCallbackQuery(chatId,answer);
        }

    }

}
