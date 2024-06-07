package com.example.tgbot.keyboard;

import com.example.tgbot.service.UrlService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@PropertySource("/application.properties")
public class StartKeyboard {

    public static InlineKeyboardMarkup MenuWithGoToMainButton(String language) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> secondRow = new ArrayList<>();
        InlineKeyboardButton goToMainButton = new InlineKeyboardButton();
        if (language.equals("russian")){
            goToMainButton.setText("\uD83D\uDD1DНа главную");
        }
        else {
            goToMainButton.setText("\uD83D\uDD1DTo main");
        }
        goToMainButton.setCallbackData("go_to_main");
        secondRow.add(goToMainButton);
        keyboard.add(secondRow);

        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }

//    public static InlineKeyboardMarkup showLotteryMenu() {
//        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
//        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
//
//        List<InlineKeyboardButton> firstRow = new ArrayList<>();
//        InlineKeyboardButton HowToEarnButton = new InlineKeyboardButton();
//        HowToEarnButton.setText("Участвовать \uD83C\uDF89\uD83C\uDF89\uD83C\uDF89");
//        HowToEarnButton.setCallbackData("take_to_part_in_lottery");
//        firstRow.add(HowToEarnButton);
//        keyboard.add(firstRow);
//
//        List<InlineKeyboardButton> secondRow = new ArrayList<>();
//        InlineKeyboardButton goToMainButton = new InlineKeyboardButton();
//        goToMainButton.setText("\uD83D\uDD1DНа главную");
//        goToMainButton.setCallbackData("go_to_main");
//        secondRow.add(goToMainButton);
//        keyboard.add(secondRow);
//
//        inlineKeyboardMarkup.setKeyboard(keyboard);
//        return inlineKeyboardMarkup;
//    }

    public static InlineKeyboardMarkup showLanguageMenu() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        InlineKeyboardButton russianLanguageButton = new InlineKeyboardButton();
        russianLanguageButton.setText("\uD83C\uDDF7\uD83C\uDDFA Русский");
        russianLanguageButton.setCallbackData("russian");

        InlineKeyboardButton englishLanguageButton = new InlineKeyboardButton();
        englishLanguageButton.setText("\uD83C\uDDEC\uD83C\uDDE7 English");
        englishLanguageButton.setCallbackData("english");

        firstRow.add(russianLanguageButton);
        firstRow.add(englishLanguageButton);
        keyboard.add(firstRow);

        List<InlineKeyboardButton> secondRow = new ArrayList<>();
        InlineKeyboardButton hindiLanguageButton = new InlineKeyboardButton();
        hindiLanguageButton.setText("\uD83C\uDDEE\uD83C\uDDF3 हिंदी");
        hindiLanguageButton.setCallbackData("hindi");

        InlineKeyboardButton brazilianLanguageButton = new InlineKeyboardButton();
        brazilianLanguageButton.setText("\uD83C\uDDE7\uD83C\uDDF7 Brazilian");
        brazilianLanguageButton.setCallbackData("brazilian");

        secondRow.add(hindiLanguageButton);
        secondRow.add(brazilianLanguageButton);
        keyboard.add(secondRow);

        List<InlineKeyboardButton> thirdRow = new ArrayList<>();
        InlineKeyboardButton spanishLanguageButton = new InlineKeyboardButton();
        spanishLanguageButton.setText("\uD83C\uDDEA\uD83C\uDDF8 Español");
        spanishLanguageButton.setCallbackData("spanish");

        InlineKeyboardButton uzbekLanguageButton = new InlineKeyboardButton();
        uzbekLanguageButton.setText("\uD83C\uDDFA\uD83C\uDDFF O'zbek");
        uzbekLanguageButton.setCallbackData("uzbek");

        thirdRow.add(spanishLanguageButton);
        thirdRow.add(uzbekLanguageButton);
        keyboard.add(thirdRow);

        List<InlineKeyboardButton> fourthRow = new ArrayList<>();
        InlineKeyboardButton azerbaijaniLanguageButton = new InlineKeyboardButton();
        azerbaijaniLanguageButton.setText("\uD83C\uDDFA\uD83C\uDDFF Azərbaycanca");
        azerbaijaniLanguageButton.setCallbackData("azerbaijani");

        InlineKeyboardButton turkishLanguageButton = new InlineKeyboardButton();
        turkishLanguageButton.setText("\uD83C\uDDF9\uD83C\uDDF7 Türkçe");
        turkishLanguageButton.setCallbackData("turkish");

        fourthRow.add(azerbaijaniLanguageButton);
        fourthRow.add(turkishLanguageButton);
        keyboard.add(fourthRow);

        List<InlineKeyboardButton> fifthRow = new ArrayList<>();
        InlineKeyboardButton portugueseLanguageButton = new InlineKeyboardButton();
        portugueseLanguageButton.setText("\uD83C\uDDF5\uD83C\uDDF9 Português");
        portugueseLanguageButton.setCallbackData("portuguese");

        InlineKeyboardButton arabicLanguageButton = new InlineKeyboardButton();
        arabicLanguageButton.setText("\uD83C\uDDE6\uD83C\uDDEA العربية");
        arabicLanguageButton.setCallbackData("arabic");

        fifthRow.add(portugueseLanguageButton);
        fifthRow.add(arabicLanguageButton);
        keyboard.add(fifthRow);

        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }





    public static InlineKeyboardMarkup startMenu(String channelUrl, UrlService urlService,String language) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> seventhRow = new ArrayList<>();
        InlineKeyboardButton channelButton = new InlineKeyboardButton();
        if(language.equals("russian")){
            channelButton.setText("Перейти в канал");
        }
        else {
            channelButton.setText("To channel");
        }
        channelButton.setUrl(channelUrl);
        seventhRow.add(channelButton);
        keyboard.add(seventhRow);

        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        InlineKeyboardButton checkTheSubsctiptionButton = new InlineKeyboardButton();

        if(language.equals("russian")){
            checkTheSubsctiptionButton.setText("Проверить подписку");
        }
        else {
            checkTheSubsctiptionButton.setText("Check subscription");
        }
        checkTheSubsctiptionButton.setCallbackData("check_the_subscription");
        firstRow.add(checkTheSubsctiptionButton);
        keyboard.add(firstRow);

        List<InlineKeyboardButton> thirdRow = new ArrayList<>();
        InlineKeyboardButton sendMessageToSupportButton = new InlineKeyboardButton();
        if(language.equals("russian")){
            sendMessageToSupportButton.setText("\uD83C\uDD98 Написать в Support \uD83C\uDD98");
        }
        else {
            sendMessageToSupportButton.setText("\uD83C\uDD98 Write to Support \uD83C\uDD98");
        }

        String deepLink = urlService.getSupportLink();
        sendMessageToSupportButton.setUrl(deepLink);
        thirdRow.add(sendMessageToSupportButton);
        keyboard.add(thirdRow);


        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup sendGameSelectionMenu() {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        // Создание кнопок для каждой игры с смайликами
        // Добавление первой строки кнопок
        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        firstRow.add(createButton("⭐\uFE0F Mines", "mines"));
        firstRow.add(createButton("\uD83D\uDE80 LuckyJet", "luckyjet"));
        rowsInline.add(firstRow);

        // Добавление второй строки кнопок
        List<InlineKeyboardButton> secondRow = new ArrayList<>();
        secondRow.add(createButton("\u2708 Aviator", "aviator"));
        secondRow.add(createButton("\uD83E\uDE99 Coin", "coin"));
        rowsInline.add(secondRow);

        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }

    private static InlineKeyboardButton createButton(String buttonText, String callBackName) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(buttonText);
        button.setCallbackData(callBackName);
        return button;
    }


}
