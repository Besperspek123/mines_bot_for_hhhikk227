package com.example.tgbot.service.admin;

import com.example.tgbot.interfaces.BotActions;
import com.example.tgbot.interfaces.CommandWithChatId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("/application.properties")
public class SendHelpForAdminMessageCommand implements CommandWithChatId {

    private final BotActions botActions;


    @Autowired
    public SendHelpForAdminMessageCommand(BotActions botActions) {
        this.botActions = botActions;
    }
    @Override
    public void execute(long chatId) {

        String answer = "👮‍♂️ Команды администратора:\n\n"
                + "✨ /admin - Панель администратора.\n"
                + "📊 /stat - Вывести статистику по пользователям бота.\n\n"
                + "\uD83C\uDDF7\uD83C\uDDFA /ru - Переключиться на русский язык.\n"
                + "\uD83C\uDDEC\uD83C\uDDE7 /eng - Переключиться на английский язык.\n"
                + "\uD83C\uDDEE\uD83C\uDDF3 /hin - Переключиться на хинди.\n"
                + "\uD83C\uDDE7\uD83C\uDDF7 /bra - Переключиться на бразильский португальский.\n"
                + "\uD83C\uDDEA\uD83C\uDDF8 /spa - Переключиться на испанский язык.\n"
                + "\uD83C\uDDFA\uD83C\uDDFF /uzb - Переключиться на узбекский язык.\n"
                + "\uD83C\uDDFA\uD83C\uDDFF /aze - Переключиться на азербайджанский язык.\n"
                + "\uD83C\uDDF9\uD83C\uDDF7 /tur - Переключиться на турецкий язык.\n"
                + "\uD83C\uDDF5\uD83C\uDDF9 /por - Переключиться на европейский португальский.\n"
                + "\uD83C\uDDE6\uD83C\uDDEA /ara - Переключиться на арабский язык.\n\n"
                + "👤 Управление администраторами:\n"
                + "➕ /addAdministrator - Добавить администратора.\n"
                + "➖ /removeAdministrator - Удалить администратора.\n\n"
                + "🔐 /setSupport - Заменить ссылку на саппорт.\n\n"
                + "🌐 Настройка URL сайтов:\n"
                + "🔹 /setUrlOneWin - Установить URL для OneWin ссылки для СНГ.\n"
                + "🔹 /setUrlOneWinEng - Установить URL для OneWin ссылки для ENG.\n\n"
                + "🔗 /setUrlChannel - Установить ссылку на телеграм канал для проверки подписки. Бот должен быть администратором канала.\n"
                + "🔗 /setUrlChannelENG - Установить ссылку на телеграм канал ENG для проверки подписки. Бот должен быть администратором канала.\n\n"
                + "🔑 /settokenbotforpostback - Установить токен бота для отправки информации о регистрациях и депозитах.\n"
                + "🔑 /setchatidforpostback - Установить chatId пользователя, которому бот будет отправлять информацию о регистрациях и депозитах.\n\n"
                + "🎁 Настройка промокодов:\n"
                + "🔸 /setPromoOneWin - Установить промокод для OneWin ДЛЯ СНГ.\n"
                + "🔸 /setPromoOneWinEng - Установить промокод для OneWin ДЛЯ ENG.\n\n"
                + "💬 Рассылка сообщений:\n"
                + "💬 По всем пользователям:\n"
                + "    /sendall - По всем.\n"
                + "    /sendallru - По русским.\n"
                + "    /sendalleng - По англичанам.\n\n"
                + "💬 По пользователям, зарегистрировавшимся, но не сделавшим депозит:\n"
                + "    /sendallwhonotdeposit - По всем.\n"
                + "    /sendallruwhonotdeposit - По русским.\n"
                + "    /sendallengwhonotdeposit - По англичанам.\n\n"
                + "💬 По пользователям, которые не зарегистрировались:\n"
                + "    /sendallwhonotregistered - По всем.\n"
                + "    /sendallruwhonotregistered - По русским.\n"
                + "    /sendallengwhonotregistered - По англичанам.\n\n"
                + "💬 По пользователям, которые сделали депозит:\n"
                + "    /sendallwhoisdeposit - По всем.\n"
                + "    /sendallruwhoisdeposit - По русским.\n"
                + "    /sendallengwhoisdeposit - По англичанам.\n\n"
                + "👤 /getinfoaboutuser - Получить информацию о пользователе.\n"
                + "👤 /getinfoaboutuserbyonewinid - Получить информацию о пользователе по id из 1win.\n\n"
                + "🔓 /giveaccessforsignal - Дать доступ к сигналам.\n"
                + "🔒 /revokeaccessforsignal - Забрать доступ к сигналам.\n\n"
                + "📜 /getlogs - Получить логи действий пользователей.\n\n"
                + "⚠️ Для неадминистраторов при попытке использования административных команд выдаётся предупреждение о недоступности команды. 😊";



        botActions.sendMessageWithParseMARKDOWN(chatId, answer);
    }

}