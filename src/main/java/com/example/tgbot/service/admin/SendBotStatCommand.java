package com.example.tgbot.service.admin;

import com.example.tgbot.exception.AccessDeniedException;
import com.example.tgbot.interfaces.BotActions;
import com.example.tgbot.interfaces.CommandWithChatId;
import com.example.tgbot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("/application.properties")
public class SendBotStatCommand implements CommandWithChatId {

    private final UserService userService;

    private final BotActions botActions;



    @Autowired
    public SendBotStatCommand(BotActions botActions, UserService userService) {
        this.botActions = botActions;
        this.userService = userService;
    }
    @Override
    public void execute(long chatId) {
        if(!userService.getInfoAboutUserByChatID(chatId).getIsAdministrator()){
            botActions.sendMessageWithoutCallbackQuery(chatId,"У вас нету прав на выполнение этой команды");
            throw new AccessDeniedException("У вас нету прав на выполнение этой команды");
        }

        String answer = "Статистика пользователей в боте:\n\n";

        // Количество пользователей
        long totalUsers = userService.getAmountOfUsersInBot();
        answer += "👥 Всего пользователей: " + totalUsers + "\n";

        // Количество новых пользователей сегодня
        long newUsersToday = userService.getAmountOfNewUsersInBotToday();
        answer += "🆕 Новые пользователи сегодня: " + newUsersToday + "\n";

        // Количество новых пользователей вчера
        long newUsersYesterday = userService.getAmountOfNewUsersInBotYesterday();
        answer += "📅 Новые пользователи вчера: " + newUsersYesterday + "\n";

        // Количество пользователей за текущий месяц
        long usersCurrentMonth = userService.getNumberOfUsersForCurrentMonth();
        answer += "📆 Пользователей за текущий месяц: " + usersCurrentMonth + "\n";

        // Количество пользователей за прошлый месяц
        long usersPreviousMonth = userService.getNumberOfUsersForPreviousMonth();
        answer += "🗓️ Пользователей за прошлый месяц: " + usersPreviousMonth + "\n";

        botActions.sendMessageWithoutCallbackQuery(chatId, answer);
    }

}