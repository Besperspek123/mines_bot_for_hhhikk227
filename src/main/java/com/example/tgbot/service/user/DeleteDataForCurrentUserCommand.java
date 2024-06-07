package com.example.tgbot.service.user;

import com.example.tgbot.entity.User;
import com.example.tgbot.interfaces.BotActions;
import com.example.tgbot.interfaces.CommandWithUser;
import com.example.tgbot.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeleteDataForCurrentUserCommand implements CommandWithUser {

    private final BotActions botActions;
    private final UserService userService;



    public DeleteDataForCurrentUserCommand(BotActions botActions, UserService userService) {
        this.botActions = botActions;
        this.userService = userService;
    }

    @Override
    public void execute(long chatID, User user) {
        userService.deleteDataForUser(user);


        switch (user.getLanguage()) {
            case "russian":
                botActions.sendMessageWithoutCallbackQuery(chatID, "Ваши данные удалены");
                break;
            case "english":
                botActions.sendMessageWithoutCallbackQuery(chatID, "Your data has been deleted");
                break;
            case "hindi":
                botActions.sendMessageWithoutCallbackQuery(chatID, "आपका डेटा हटा दिया गया है");
                break;
            case "brazilian":
                botActions.sendMessageWithoutCallbackQuery(chatID, "Seus dados foram excluídos");
                break;
            case "spanish":
                botActions.sendMessageWithoutCallbackQuery(chatID, "Sus datos han sido eliminados");
                break;
            case "uzbek":
                botActions.sendMessageWithoutCallbackQuery(chatID, "Ma'lumotlaringiz o'chirildi");
                break;
            case "azerbaijani":
                botActions.sendMessageWithoutCallbackQuery(chatID, "Məlumatlarınız silindi");
                break;
            case "turkish":
                botActions.sendMessageWithoutCallbackQuery(chatID, "Verileriniz silindi");
                break;
            case "portuguese":
                botActions.sendMessageWithoutCallbackQuery(chatID, "Os seus dados foram excluídos");
                break;
            case "arabic":
                botActions.sendMessageWithoutCallbackQuery(chatID, "تم حذف بياناتك");
                break;
            default:
                botActions.sendMessageWithoutCallbackQuery(chatID, "Your data has been deleted");
                break;
        }

    }

}
