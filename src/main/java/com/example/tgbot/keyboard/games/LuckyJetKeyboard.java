package com.example.tgbot.keyboard.games;

import com.example.tgbot.entity.User;
import com.example.tgbot.service.UrlService;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;

import java.util.ArrayList;
import java.util.List;

public class LuckyJetKeyboard {


    public static InlineKeyboardMarkup mainForInstructionIfUserIsNotRegistered(String oneWinUrl, String language) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> firstRow = new ArrayList<>();

        InlineKeyboardButton registrationButton = new InlineKeyboardButton();
        switch (language) {
            case "russian":
                registrationButton.setText("🖥 Зарегистрироваться 🖥");
                break;
            case "english":
                registrationButton.setText("🖥 Register 🖥");
                break;
            case "hindi":
                registrationButton.setText("🖥 पंजीकरण करें 🖥");
                break;
            case "brazilian":
                registrationButton.setText("🖥 Registrar 🖥");
                break;
            case "spanish":
                registrationButton.setText("🖥 Registrarse 🖥");
                break;
            case "uzbek":
                registrationButton.setText("🖥 Ro'yxatdan o'tish 🖥");
                break;
            case "azerbaijani":
                registrationButton.setText("🖥 Qeydiyyatdan keçin 🖥");
                break;
            case "turkish":
                registrationButton.setText("🖥 Kayıt Ol 🖥");
                break;
            case "portuguese":
                registrationButton.setText("🖥 Registrar 🖥");
                break;
            case "arabic":
                registrationButton.setText("🖥 التسجيل 🖥");
                break;
            default:
                registrationButton.setText("🖥 Register 🖥");
                break;
        }
        registrationButton.setCallbackData("luckyjet_registration");
        firstRow.add(registrationButton);
        keyboard.add(firstRow);

        List<InlineKeyboardButton> videoRow = new ArrayList<>();
        InlineKeyboardButton getVideoInstructionButton = new InlineKeyboardButton();
        switch (language) {
            case "russian":
                getVideoInstructionButton.setText("\uD83D\uDCF9 Видеоинструкция \uD83D\uDCF9");
                break;
            case "english":
                getVideoInstructionButton.setText("📹 Video Instruction 📹");
                break;
            case "hindi":
                getVideoInstructionButton.setText("📹 वीडियो निर्देश 📹");
                break;
            case "brazilian":
                getVideoInstructionButton.setText("📹 Instrução em Vídeo 📹");
                break;
            case "spanish":
                getVideoInstructionButton.setText("📹 Instrucción en Video 📹");
                break;
            case "uzbek":
                getVideoInstructionButton.setText("📹 Video Ko'rsatma 📹");
                break;
            case "azerbaijani":
                getVideoInstructionButton.setText("📹 Video Təlimat 📹");
                break;
            case "turkish":
                getVideoInstructionButton.setText("📹 Video Talimat 📹");
                break;
            case "portuguese":
                getVideoInstructionButton.setText("📹 Instrução em Vídeo 📹");
                break;
            case "arabic":
                getVideoInstructionButton.setText("📹 التعليمات بالفيديو 📹");
                break;
            default:
                getVideoInstructionButton.setText("📹 Video Instruction 📹");
                break;
        }
        getVideoInstructionButton.setCallbackData("luckyjet_video_instruction");
//    videoRow.add(getVideoInstructionButton);
        keyboard.add(videoRow);

        List<InlineKeyboardButton> thirdRow = new ArrayList<>();
        InlineKeyboardButton alreadyRegisteredButton = new InlineKeyboardButton();
        switch (language) {
            case "russian":
                alreadyRegisteredButton.setText("❗ Получить сигнал ❗");
                break;
            case "english":
                alreadyRegisteredButton.setText("❗ Get Signal ❗");
                break;
            case "hindi":
                alreadyRegisteredButton.setText("❗ संकेत प्राप्त करें ❗");
                break;
            case "brazilian":
                alreadyRegisteredButton.setText("❗ Obter Sinal ❗");
                break;
            case "spanish":
                alreadyRegisteredButton.setText("❗ Obtener Señal ❗");
                break;
            case "uzbek":
                alreadyRegisteredButton.setText("❗ Signalni olish ❗");
                break;
            case "azerbaijani":
                alreadyRegisteredButton.setText("❗ Siqnal Al ❗");
                break;
            case "turkish":
                alreadyRegisteredButton.setText("❗ Sinyal Al ❗");
                break;
            case "portuguese":
                alreadyRegisteredButton.setText("❗ Obter Sinal ❗");
                break;
            case "arabic":
                alreadyRegisteredButton.setText("❗ احصل على الإشارة ❗");
                break;
            default:
                alreadyRegisteredButton.setText("❗ Get Signal ❗");
                break;
        }
        alreadyRegisteredButton.setCallbackData("luckyjet_give_signal");
        thirdRow.add(alreadyRegisteredButton);
        keyboard.add(thirdRow);

        List<InlineKeyboardButton> fourthRow = new ArrayList<>();
        InlineKeyboardButton goToMainButton = new InlineKeyboardButton();
        switch (language) {
            case "russian":
                goToMainButton.setText("🔙 Вернуться на главную 🔙");
                break;
            case "english":
                goToMainButton.setText("🔙 Back to main 🔙");
                break;
            case "hindi":
                goToMainButton.setText("🔙 मुख्य पर वापस 🔙");
                break;
            case "brazilian":
                goToMainButton.setText("🔙 Voltar para principal 🔙");
                break;
            case "spanish":
                goToMainButton.setText("🔙 Volver al inicio 🔙");
                break;
            case "uzbek":
                goToMainButton.setText("🔙 Asosiyga qaytish 🔙");
                break;
            case "azerbaijani":
                goToMainButton.setText("🔙 Əsas səhifəyə qayıt 🔙");
                break;
            case "turkish":
                goToMainButton.setText("🔙 Ana menüye dön 🔙");
                break;
            case "portuguese":
                goToMainButton.setText("🔙 Voltar para principal 🔙");
                break;
            case "arabic":
                goToMainButton.setText("🔙 العودة إلى الرئيسية 🔙");
                break;
            default:
                goToMainButton.setText("🔙 Back to main 🔙");
                break;
        }
        goToMainButton.setCallbackData("luckyjet");
        fourthRow.add(goToMainButton);
        keyboard.add(fourthRow);

        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup mainForInstructionIfUserIsRegistered(String oneWinUrl,String language){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> firstRow = new ArrayList<>();

        List<InlineKeyboardButton> videoRow = new ArrayList<>();
        InlineKeyboardButton getVideoInstructionButton = new InlineKeyboardButton();

        switch (language) {
            case "russian":
                getVideoInstructionButton.setText("\uD83D\uDCF9 Видеоинструкция \uD83D\uDCF9");
                break;
            case "english":
                getVideoInstructionButton.setText("📹 Video Instruction 📹");
                break;
            case "hindi":
                getVideoInstructionButton.setText("📹 वीडियो निर्देश 📹");
                break;
            case "brazilian":
                getVideoInstructionButton.setText("📹 Instrução em Vídeo 📹");
                break;
            case "spanish":
                getVideoInstructionButton.setText("📹 Instrucción en Video 📹");
                break;
            case "uzbek":
                getVideoInstructionButton.setText("📹 Видео Нусха 📹");
                break;
            case "azerbaijani":
                getVideoInstructionButton.setText("📹 Video Təlimat 📹");
                break;
            case "turkish":
                getVideoInstructionButton.setText("📹 Video Talimat 📹");
                break;
            case "portuguese":
                getVideoInstructionButton.setText("📹 Instrução em Vídeo 📹");
                break;
            case "arabic":
                getVideoInstructionButton.setText("📹 تعليمات الفيديو 📹");
                break;
            default:
                getVideoInstructionButton.setText("📹 Video Instruction 📹");
                break;
        }

        getVideoInstructionButton.setCallbackData("luckyjet_video_instruction");
//        videoRow.add(getVideoInstructionButton);
        keyboard.add(videoRow);
        keyboard.add(firstRow);

        List<InlineKeyboardButton> thirdRow = new ArrayList<>();
        InlineKeyboardButton alreadyRegisteredButton = new InlineKeyboardButton();

        switch (language) {
            case "russian":
                alreadyRegisteredButton.setText("❗ Получить сигнал ❗");
                break;
            case "english":
                alreadyRegisteredButton.setText("❗ Get Signal ❗");
                break;
            case "hindi":
                alreadyRegisteredButton.setText("❗ सिग्नल प्राप्त करें ❗");
                break;
            case "brazilian":
                alreadyRegisteredButton.setText("❗ Obter Sinal ❗");
                break;
            case "spanish":
                alreadyRegisteredButton.setText("❗ Obtener Señal ❗");
                break;
            case "uzbek":
                alreadyRegisteredButton.setText("❗ Signal oling ❗");
                break;
            case "azerbaijani":
                alreadyRegisteredButton.setText("❗ Siqnal al ❗");
                break;
            case "turkish":
                alreadyRegisteredButton.setText("❗ Sinyal Al ❗");
                break;
            case "portuguese":
                alreadyRegisteredButton.setText("❗ Obter Sinal ❗");
                break;
            case "arabic":
                alreadyRegisteredButton.setText("❗ الحصول على الإشارة ❗");
                break;
            default:
                alreadyRegisteredButton.setText("❗ Get Signal ❗");
                break;
        }

        alreadyRegisteredButton.setCallbackData("luckyjet_give_signal");
        thirdRow.add(alreadyRegisteredButton);
        keyboard.add(thirdRow);

        List<InlineKeyboardButton> fourthRow = new ArrayList<>();
        InlineKeyboardButton goToMainButton = new InlineKeyboardButton();

        switch (language) {
            case "russian":
                goToMainButton.setText("🔙 Вернуться на главную 🔙");
                break;
            case "english":
                goToMainButton.setText("🔙 Back to main 🔙");
                break;
            case "hindi":
                goToMainButton.setText("🔙 मुख्य पृष्ठ पर वापस जाएं 🔙");
                break;
            case "brazilian":
                goToMainButton.setText("🔙 Voltar para o início 🔙");
                break;
            case "spanish":
                goToMainButton.setText("🔙 Volver al inicio 🔙");
                break;
            case "uzbek":
                goToMainButton.setText("🔙 Asosiy sahifaga qaytish 🔙");
                break;
            case "azerbaijani":
                goToMainButton.setText("🔙 Ana səhifəyə qayıt 🔙");
                break;
            case "turkish":
                goToMainButton.setText("🔙 Ana sayfaya dön 🔙");
                break;
            case "portuguese":
                goToMainButton.setText("🔙 Voltar ao início 🔙");
                break;
            case "arabic":
                goToMainButton.setText("🔙 العودة إلى الصفحة الرئيسية 🔙");
                break;
            default:
                goToMainButton.setText("🔙 Back to main 🔙");
                break;
        }

        goToMainButton.setCallbackData("luckyjet");
        fourthRow.add(goToMainButton);
        keyboard.add(fourthRow);

        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup mainForVideoInstruction(String oneWinUrl,String language){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        InlineKeyboardButton oneWinButton = new InlineKeyboardButton();
        oneWinButton.setText("\uD83D\uDCBB 1WIN \uD83D\uDCBB");
        oneWinButton.setUrl(oneWinUrl);
        firstRow.add(oneWinButton);
        keyboard.add(firstRow);

        List<InlineKeyboardButton> instructionRow = new ArrayList<>();
        InlineKeyboardButton instructionButton = new InlineKeyboardButton();

        switch (language) {
            case "russian":
                instructionButton.setText("📖 Инструкция 📖");
                break;
            case "english":
                instructionButton.setText("📖 Instruction 📖");
                break;
            case "hindi":
                instructionButton.setText("📖 निर्देश 📖");
                break;
            case "brazilian":
                instructionButton.setText("📖 Instrução 📖");
                break;
            case "spanish":
                instructionButton.setText("📖 Instrucción 📖");
                break;
            case "uzbek":
                instructionButton.setText("📖 Ko'rsatma 📖");
                break;
            case "azerbaijani":
                instructionButton.setText("📖 Təlimat 📖");
                break;
            case "turkish":
                instructionButton.setText("📖 Talimat 📖");
                break;
            case "portuguese":
                instructionButton.setText("📖 Instrução 📖");
                break;
            case "arabic":
                instructionButton.setText("📖 تعليمات 📖");
                break;
            default:
                instructionButton.setText("📖 Instruction 📖");
                break;
        }

        instructionButton.setCallbackData("luckyjet_instruction");
        instructionRow.add(instructionButton);
        keyboard.add(instructionRow);

        List<InlineKeyboardButton> thirdRow = new ArrayList<>();
        InlineKeyboardButton alreadyRegisteredButton = new InlineKeyboardButton();

        switch (language) {
            case "russian":
                alreadyRegisteredButton.setText("❗ Получить сигнал ❗");
                break;
            case "english":
                alreadyRegisteredButton.setText("❗ Get Signal ❗");
                break;
            case "hindi":
                alreadyRegisteredButton.setText("❗ सिग्नल प्राप्त करें ❗");
                break;
            case "brazilian":
                alreadyRegisteredButton.setText("❗ Obter Sinal ❗");
                break;
            case "spanish":
                alreadyRegisteredButton.setText("❗ Obtener Señal ❗");
                break;
            case "uzbek":
                alreadyRegisteredButton.setText("❗ Signalni olish ❗");
                break;
            case "azerbaijani":
                alreadyRegisteredButton.setText("❗ Siqnalı Al ❗");
                break;
            case "turkish":
                alreadyRegisteredButton.setText("❗ Sinyal Al ❗");
                break;
            case "portuguese":
                alreadyRegisteredButton.setText("❗ Obter Sinal ❗");
                break;
            case "arabic":
                alreadyRegisteredButton.setText("❗ الحصول على الإشارة ❗");
                break;
            default:
                alreadyRegisteredButton.setText("❗ Get Signal ❗");
                break;
        }

        alreadyRegisteredButton.setCallbackData("luckyjet_give_signal");
        thirdRow.add(alreadyRegisteredButton);
        keyboard.add(thirdRow);

        List<InlineKeyboardButton> fourthRow = new ArrayList<>();
        InlineKeyboardButton goToMainButton = new InlineKeyboardButton();

        switch (language) {
            case "russian":
                goToMainButton.setText("🔙 Вернуться на главную 🔙");
                break;
            case "english":
                goToMainButton.setText("🔙 Back to main 🔙");
                break;
            case "hindi":
                goToMainButton.setText("🔙 मुख्य पृष्ठ पर वापस जाएं 🔙");
                break;
            case "brazilian":
                goToMainButton.setText("🔙 Voltar ao início 🔙");
                break;
            case "spanish":
                goToMainButton.setText("🔙 Volver al inicio 🔙");
                break;
            case "uzbek":
                goToMainButton.setText("🔙 Asosiyga qaytish 🔙");
                break;
            case "azerbaijani":
                goToMainButton.setText("🔙 Əsas səhifəyə qayıt 🔙");
                break;
            case "turkish":
                goToMainButton.setText("🔙 Ana sayfaya dön 🔙");
                break;
            case "portuguese":
                goToMainButton.setText("🔙 Voltar ao início 🔙");
                break;
            case "arabic":
                goToMainButton.setText("🔙 العودة إلى الرئيسية 🔙");
                break;
            default:
                goToMainButton.setText("🔙 Back to main 🔙");
                break;
        }

        goToMainButton.setCallbackData("luckyjet");
        fourthRow.add(goToMainButton);
        keyboard.add(fourthRow);

        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }


    public static InlineKeyboardMarkup mainMinesMenuIfUserAlreadyRegistered1(UrlService urlService, String language) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        // Создание первой строки кнопок
        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        InlineKeyboardButton instructionButton = new InlineKeyboardButton();
        switch (language) {
            case "russian":
                instructionButton.setText("📖 Инструкция 📖");
                break;
            case "english":
                instructionButton.setText("📖 Instruction 📖");
                break;
            case "hindi":
                instructionButton.setText("📖 निर्देश 📖");
                break;
            case "brazilian":
                instructionButton.setText("📖 Instrução 📖");
                break;
            case "spanish":
                instructionButton.setText("📖 Instrucción 📖");
                break;
            case "uzbek":
                instructionButton.setText("📖 Ko'rsatma 📖");
                break;
            case "azerbaijani":
                instructionButton.setText("📖 Təlimat 📖");
                break;
            case "turkish":
                instructionButton.setText("📖 Talimat 📖");
                break;
            case "portuguese":
                instructionButton.setText("📖 Instrução 📖");
                break;
            case "arabic":
                instructionButton.setText("📖 تعليمات 📖");
                break;
            default:
                instructionButton.setText("📖 Instruction 📖");
                break;
        }
        instructionButton.setCallbackData("luckyjet_instruction");
        firstRow.add(instructionButton);
        InlineKeyboardButton getVideoInstructionButton = new InlineKeyboardButton();
        switch (language) {
            case "russian":
                getVideoInstructionButton.setText("\uD83D\uDCF9 Видеоинструкция \uD83D\uDCF9");
                break;
            case "english":
                getVideoInstructionButton.setText("\uD83D\uDCF9 Video Instruction \uD83D\uDCF9");
                break;
            case "hindi":
                getVideoInstructionButton.setText("\uD83D\uDCF9 वीडियो निर्देश \uD83D\uDCF9");
                break;
            case "brazilian":
                getVideoInstructionButton.setText("\uD83D\uDCF9 Instrução em Vídeo \uD83D\uDCF9");
                break;
            case "spanish":
                getVideoInstructionButton.setText("\uD83D\uDCF9 Instrucción en Video \uD83D\uDCF9");
                break;
            case "uzbek":
                getVideoInstructionButton.setText("\uD83D\uDCF9 Video Ko'rsatma \uD83D\uDCF9");
                break;
            case "azerbaijani":
                getVideoInstructionButton.setText("\uD83D\uDCF9 Video Təlimat \uD83D\uDCF9");
                break;
            case "turkish":
                getVideoInstructionButton.setText("\uD83D\uDCF9 Video Talimatı \uD83D\uDCF9");
                break;
            case "portuguese":
                getVideoInstructionButton.setText("\uD83D\uDCF9 Instrução em Vídeo \uD83D\uDCF9");
                break;
            case "arabic":
                getVideoInstructionButton.setText("\uD83D\uDCF9 تعليمات الفيديو \uD83D\uDCF9");
                break;
            default:
                getVideoInstructionButton.setText("\uD83D\uDCF9 Video Instruction \uD83D\uDCF9");
                break;
        }
        getVideoInstructionButton.setCallbackData("luckyjet_video_instruction");
//        firstRow.add(getVideoInstructionButton);
        keyboard.add(firstRow);

        // Создание второй строки кнопок
        List<InlineKeyboardButton> secondRow = new ArrayList<>();
        InlineKeyboardButton alreadyRegisteredButton = new InlineKeyboardButton();
        switch (language) {
            case "russian":
                alreadyRegisteredButton.setText("❗ Получить сигнал ❗");
                break;
            case "english":
                alreadyRegisteredButton.setText("❗ Get Signal ❗");
                break;
            case "hindi":
                alreadyRegisteredButton.setText("❗ सिग्नल प्राप्त करें ❗");
                break;
            case "brazilian":
                alreadyRegisteredButton.setText("❗ Obter Sinal ❗");
                break;
            case "spanish":
                alreadyRegisteredButton.setText("❗ Obtener Señal ❗");
                break;
            case "uzbek":
                alreadyRegisteredButton.setText("❗ Signalni olish ❗");
                break;
            case "azerbaijani":
                alreadyRegisteredButton.setText("❗ Siqnal Al ❗");
                break;
            case "turkish":
                alreadyRegisteredButton.setText("❗ Sinyal Al ❗");
                break;
            case "portuguese":
                alreadyRegisteredButton.setText("❗ Obter Sinal ❗");
                break;
            case "arabic":
                alreadyRegisteredButton.setText("❗ الحصول على إشارة ❗");
                break;
            default:
                alreadyRegisteredButton.setText("❗ Get Signal ❗");
                break;
        }
        alreadyRegisteredButton.setCallbackData("luckyjet_give_signal");
        secondRow.add(alreadyRegisteredButton);
        keyboard.add(secondRow);

        // Создание третьей строки кнопок
        List<InlineKeyboardButton> thirdRow = new ArrayList<>();
        InlineKeyboardButton sendMessageToSupportButton = new InlineKeyboardButton();
        switch (language) {
            case "russian":
                sendMessageToSupportButton.setText("\uD83C\uDD98 Написать в Support \uD83C\uDD98");
                break;
            case "english":
                sendMessageToSupportButton.setText("\uD83C\uDD98 Write to Support \uD83C\uDD98");
                break;
            case "hindi":
                sendMessageToSupportButton.setText("\uD83C\uDD98 समर्थन में लिखें \uD83C\uDD98");
                break;
            case "brazilian":
                sendMessageToSupportButton.setText("\uD83C\uDD98 Escrever para o Suporte \uD83C\uDD98");
                break;
            case "spanish":
                sendMessageToSupportButton.setText("\uD83C\uDD98 Escribir al Soporte \uD83C\uDD98");
                break;
            case "uzbek":
                sendMessageToSupportButton.setText("\uD83C\uDD98 Qo'llab-quvvatlash uchun yozing \uD83C\uDD98");
                break;
            case "azerbaijani":
                sendMessageToSupportButton.setText("\uD83C\uDD98 Dəstək üçün yazın \uD83C\uDD98");
                break;
            case "turkish":
                sendMessageToSupportButton.setText("\uD83C\uDD98 Desteğe Yaz \uD83C\uDD98");
                break;
            case "portuguese":
                sendMessageToSupportButton.setText("\uD83C\uDD98 Escrever para o Suporte \uD83C\uDD98");
                break;
            case "arabic":
                sendMessageToSupportButton.setText("\uD83C\uDD98 اكتب إلى الدعم \uD83C\uDD98");
                break;
            default:
                sendMessageToSupportButton.setText("\uD83C\uDD98 Write to Support \uD83C\uDD98");
                break;
        }
        sendMessageToSupportButton.setUrl(urlService.getSupportLink());
        thirdRow.add(sendMessageToSupportButton);
        keyboard.add(thirdRow);

        // Добавление новой строки с кнопкой выбора игры
        List<InlineKeyboardButton> newGameSelectionRow = new ArrayList<>();
        InlineKeyboardButton gameSelectionButton = new InlineKeyboardButton();
        switch (language) {
            case "russian":
                gameSelectionButton.setText("🎮 Выбрать игру 🎮");
                break;
            case "english":
                gameSelectionButton.setText("🎮 Choose Game 🎮");
                break;
            case "hindi":
                gameSelectionButton.setText("🎮 खेल चुनें 🎮");
                break;
            case "brazilian":
                gameSelectionButton.setText("🎮 Escolher Jogo 🎮");
                break;
            case "spanish":
                gameSelectionButton.setText("🎮 Elegir Juego 🎮");
                break;
            case "uzbek":
                gameSelectionButton.setText("🎮 O'yinni Tanlash 🎮");
                break;
            case "azerbaijani":
                gameSelectionButton.setText("🎮 Oyunu Seç 🎮");
                break;
            case "turkish":
                gameSelectionButton.setText("🎮 Oyun Seç 🎮");
                break;
            case "portuguese":
                gameSelectionButton.setText("🎮 Escolher Jogo 🎮");
                break;
            case "arabic":
                gameSelectionButton.setText("🎮 اختر اللعبة 🎮");
                break;
            default:
                gameSelectionButton.setText("🎮 Choose Game 🎮");
                break;
        }
        gameSelectionButton.setCallbackData("choose_games_menu");
        newGameSelectionRow.add(gameSelectionButton);
        keyboard.add(newGameSelectionRow);

        // Установка всех строк клавиатуры
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup mainMinesMenuIfUserNotRegistered1(UrlService urlService, String language) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        // Существующие кнопки и строки
        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        InlineKeyboardButton registrationButton = new InlineKeyboardButton();
        switch (language) {
            case "russian":
                registrationButton.setText("🖥 Зарегистрироваться 🖥");
                break;
            case "english":
                registrationButton.setText("🖥 Register 🖥");
                break;
            case "hindi":
                registrationButton.setText("🖥 पंजीकरण करें 🖥");
                break;
            case "brazilian":
                registrationButton.setText("🖥 Registrar 🖥");
                break;
            case "spanish":
                registrationButton.setText("🖥 Registrarse 🖥");
                break;
            case "uzbek":
                registrationButton.setText("🖥 Ro'yxatdan o'tish 🖥");
                break;
            case "azerbaijani":
                registrationButton.setText("🖥 Qeydiyyatdan keçin 🖥");
                break;
            case "turkish":
                registrationButton.setText("🖥 Kayıt Ol 🖥");
                break;
            case "portuguese":
                registrationButton.setText("🖥 Registrar 🖥");
                break;
            case "arabic":
                registrationButton.setText("🖥 سجل 🖥");
                break;
            default:
                registrationButton.setText("🖥 Register 🖥");
                break;
        }
        registrationButton.setCallbackData("luckyjet_registration");
        firstRow.add(registrationButton);
        keyboard.add(firstRow);

        List<InlineKeyboardButton> secondRow = new ArrayList<>();
        InlineKeyboardButton instructionButton = new InlineKeyboardButton();
        switch (language) {
            case "russian":
                instructionButton.setText("📖 Инструкция 📖");
                break;
            case "english":
                instructionButton.setText("📖 Instruction 📖");
                break;
            case "hindi":
                instructionButton.setText("📖 निर्देश 📖");
                break;
            case "brazilian":
                instructionButton.setText("📖 Instrução 📖");
                break;
            case "spanish":
                instructionButton.setText("📖 Instrucción 📖");
                break;
            case "uzbek":
                instructionButton.setText("📖 Yo'riqnoma 📖");
                break;
            case "azerbaijani":
                instructionButton.setText("📖 Təlimat 📖");
                break;
            case "turkish":
                instructionButton.setText("📖 Talimat 📖");
                break;
            case "portuguese":
                instructionButton.setText("📖 Instrução 📖");
                break;
            case "arabic":
                instructionButton.setText("📖 تعليمات 📖");
                break;
            default:
                instructionButton.setText("📖 Instruction 📖");
                break;
        }
        instructionButton.setCallbackData("luckyjet_instruction");
        InlineKeyboardButton getVideoInstructionButton = new InlineKeyboardButton();
        switch (language) {
            case "russian":
                getVideoInstructionButton.setText("\uD83D\uDCF9 Видеоинструкция \uD83D\uDCF9");
                break;
            case "english":
                getVideoInstructionButton.setText("📹 Video Instruction 📹");
                break;
            case "hindi":
                getVideoInstructionButton.setText("📹 वीडियो निर्देश 📹");
                break;
            case "brazilian":
                getVideoInstructionButton.setText("📹 Instrução em Vídeo 📹");
                break;
            case "spanish":
                getVideoInstructionButton.setText("📹 Instrucción en Video 📹");
                break;
            case "uzbek":
                getVideoInstructionButton.setText("📹 Video Yo'riqnoma 📹");
                break;
            case "azerbaijani":
                getVideoInstructionButton.setText("📹 Video Təlimat 📹");
                break;
            case "turkish":
                getVideoInstructionButton.setText("📹 Video Talimat 📹");
                break;
            case "portuguese":
                getVideoInstructionButton.setText("📹 Instrução em Vídeo 📹");
                break;
            case "arabic":
                getVideoInstructionButton.setText("📹 تعليمات الفيديو 📹");
                break;
            default:
                getVideoInstructionButton.setText("📹 Video Instruction 📹");
                break;
        }
        getVideoInstructionButton.setCallbackData("luckyjet_video_instruction");
        secondRow.add(instructionButton);
//        secondRow.add(getVideoInstructionButton);
        keyboard.add(secondRow);

        List<InlineKeyboardButton> thirdRow = new ArrayList<>();
        InlineKeyboardButton alreadyRegisteredButton = new InlineKeyboardButton();
        switch (language) {
            case "russian":
                alreadyRegisteredButton.setText("❗ Получить сигнал ❗");
                break;
            case "english":
                alreadyRegisteredButton.setText("❗ Get Signal ❗");
                break;
            case "hindi":
                alreadyRegisteredButton.setText("❗ संकेत प्राप्त करें ❗");
                break;
            case "brazilian":
                alreadyRegisteredButton.setText("❗ Obter Sinal ❗");
                break;
            case "spanish":
                alreadyRegisteredButton.setText("❗ Obtener Señal ❗");
                break;
            case "uzbek":
                alreadyRegisteredButton.setText("❗ Signal Olish ❗");
                break;
            case "azerbaijani":
                alreadyRegisteredButton.setText("❗ Siqnal Al ❗");
                break;
            case "turkish":
                alreadyRegisteredButton.setText("❗ Sinyal Al ❗");
                break;
            case "portuguese":
                alreadyRegisteredButton.setText("❗ Obter Sinal ❗");
                break;
            case "arabic":
                alreadyRegisteredButton.setText("❗ الحصول على إشارة ❗");
                break;
            default:
                alreadyRegisteredButton.setText("❗ Get Signal ❗");
                break;
        }
        alreadyRegisteredButton.setCallbackData("luckyjet_give_signal");
        thirdRow.add(alreadyRegisteredButton);
        keyboard.add(thirdRow);

        List<InlineKeyboardButton> fourthRow = new ArrayList<>();
        InlineKeyboardButton sendMessageToSupportButton = new InlineKeyboardButton();
        switch (language) {
            case "russian":
                sendMessageToSupportButton.setText("\uD83C\uDD98 Написать в Support \uD83C\uDD98");
                break;
            case "english":
                sendMessageToSupportButton.setText("\uD83C\uDD98 Write to Support \uD83C\uDD98");
                break;
            case "hindi":
                sendMessageToSupportButton.setText("\uD83C\uDD98 समर्थन में लिखें \uD83C\uDD98");
                break;
            case "brazilian":
                sendMessageToSupportButton.setText("\uD83C\uDD98 Escrever para o Suporte \uD83C\uDD98");
                break;
            case "spanish":
                sendMessageToSupportButton.setText("\uD83C\uDD98 Escribir al Soporte \uD83C\uDD98");
                break;
            case "uzbek":
                sendMessageToSupportButton.setText("\uD83C\uDD98 Yordam xizmatiga yozing \uD83C\uDD98");
                break;
            case "azerbaijani":
                sendMessageToSupportButton.setText("\uD83C\uDD98 Dəstəyə Yazın \uD83C\uDD98");
                break;
            case "turkish":
                sendMessageToSupportButton.setText("\uD83C\uDD98 Desteğe Yaz \uD83C\uDD98");
                break;
            case "portuguese":
                sendMessageToSupportButton.setText("\uD83C\uDD98 Escrever para o Suporte \uD83C\uDD98");
                break;
            case "arabic":
                sendMessageToSupportButton.setText("\uD83C\uDD98 اكتب إلى الدعم \uD83C\uDD98");
                break;
            default:
                sendMessageToSupportButton.setText("\uD83C\uDD98 Write to Support \uD83C\uDD98");
                break;
        }
        sendMessageToSupportButton.setUrl(urlService.getSupportLink());
        fourthRow.add(sendMessageToSupportButton);
        keyboard.add(fourthRow);

        // Новая строка с кнопкой выбора игры
        List<InlineKeyboardButton> newGameSelectionRow = new ArrayList<>();
        InlineKeyboardButton gameSelectionButton = new InlineKeyboardButton();
        switch (language) {
            case "russian":
                gameSelectionButton.setText("🎮 Выбрать игру 🎮");
                break;
            case "english":
                gameSelectionButton.setText("🎮 Choose Game 🎮");
                break;
            case "hindi":
                gameSelectionButton.setText("🎮 खेल चुनें 🎮");
                break;
            case "brazilian":
                gameSelectionButton.setText("🎮 Escolher Jogo 🎮");
                break;
            case "spanish":
                gameSelectionButton.setText("🎮 Elegir Juego 🎮");
                break;
            case "uzbek":
                gameSelectionButton.setText("🎮 O'yinni Tanlang 🎮");
                break;
            case "azerbaijani":
                gameSelectionButton.setText("🎮 Oyunu Seç 🎮");
                break;
            case "turkish":
                gameSelectionButton.setText("🎮 Oyun Seç 🎮");
                break;
            case "portuguese":
                gameSelectionButton.setText("🎮 Escolher Jogo 🎮");
                break;
            case "arabic":
                gameSelectionButton.setText("🎮 اختر اللعبة 🎮");
                break;
            default:
                gameSelectionButton.setText("🎮 Choose Game 🎮");
                break;
        }
        gameSelectionButton.setCallbackData("choose_games_menu");
        newGameSelectionRow.add(gameSelectionButton);
        keyboard.add(newGameSelectionRow);

        // Установка клавиатуры
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }


    public static InlineKeyboardMarkup menuWithCancelButtonAndLinkToOneWin(String url, String language) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        // Первая строка с кнопкой-ссылкой на 1WIN
        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        InlineKeyboardButton oneWinButton = new InlineKeyboardButton();
        oneWinButton.setText("\uD83D\uDCBB 1WIN \uD83D\uDCBB");
        oneWinButton.setUrl(url);
        firstRow.add(oneWinButton);
        keyboard.add(firstRow);

        // Вторая строка с кнопкой "Отмена"
        List<InlineKeyboardButton> secondRow = new ArrayList<>();
        InlineKeyboardButton cancelButton = new InlineKeyboardButton();
        switch (language) {
            case "russian":
                cancelButton.setText("❌ Отмена ❌");
                break;
            case "english":
                cancelButton.setText("❌ Cancel ❌");
                break;
            case "hindi":
                cancelButton.setText("❌ रद्द करें ❌");
                break;
            case "brazilian":
                cancelButton.setText("❌ Cancelar ❌");
                break;
            case "spanish":
                cancelButton.setText("❌ Cancelar ❌");
                break;
            case "uzbek":
                cancelButton.setText("❌ Bekor qilish ❌");
                break;
            case "azerbaijani":
                cancelButton.setText("❌ Ləğv et ❌");
                break;
            case "turkish":
                cancelButton.setText("❌ İptal ❌");
                break;
            case "portuguese":
                cancelButton.setText("❌ Cancelar ❌");
                break;
            case "arabic":
                cancelButton.setText("❌ إلغاء ❌");
                break;
            default:
                cancelButton.setText("❌ Cancel ❌");
                break;
        }
        cancelButton.setCallbackData("luckyjet");
        secondRow.add(cancelButton);
        keyboard.add(secondRow);

        // Добавление новой строки с кнопкой "Проверить ID"
        List<InlineKeyboardButton> thirdRow = new ArrayList<>();
        InlineKeyboardButton checkIdButton = new InlineKeyboardButton();
        switch (language) {
            case "russian":
                checkIdButton.setText("🔍 Проверить ID 🔍");
                break;
            case "english":
                checkIdButton.setText("🔍 Check ID 🔍");
                break;
            case "hindi":
                checkIdButton.setText("🔍 आईडी की जाँच करें 🔍");
                break;
            case "brazilian":
                checkIdButton.setText("🔍 Verificar ID 🔍");
                break;
            case "spanish":
                checkIdButton.setText("🔍 Verificar ID 🔍");
                break;
            case "uzbek":
                checkIdButton.setText("🔍 ID ni tekshirish 🔍");
                break;
            case "azerbaijani":
                checkIdButton.setText("🔍 ID yoxlayın 🔍");
                break;
            case "turkish":
                checkIdButton.setText("🔍 ID'yi kontrol et 🔍");
                break;
            case "portuguese":
                checkIdButton.setText("🔍 Verificar ID 🔍");
                break;
            case "arabic":
                checkIdButton.setText("🔍 تحقق من ID 🔍");
                break;
            default:
                checkIdButton.setText("🔍 Check ID 🔍");
                break;
        }
        checkIdButton.setCallbackData("luckyjet_try_to_registration");
        thirdRow.add(checkIdButton);
        keyboard.add(thirdRow);

        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup menuWithCancelLinkForCheckID(String language,UrlService urlService) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        // Вторая строка с кнопкой "Отмена"
        List<InlineKeyboardButton> secondRow = new ArrayList<>();
        InlineKeyboardButton cancelButton = new InlineKeyboardButton();
        switch (language) {
            case "russian":
                cancelButton.setText("❌ Отмена ❌");
                break;
            case "english":
                cancelButton.setText("❌ Cancel ❌");
                break;
            case "hindi":
                cancelButton.setText("❌ रद्द करें ❌");
                break;
            case "brazilian":
                cancelButton.setText("❌ Cancelar ❌");
                break;
            case "spanish":
                cancelButton.setText("❌ Cancelar ❌");
                break;
            case "uzbek":
                cancelButton.setText("❌ Bekor qilish ❌");
                break;
            case "azerbaijani":
                cancelButton.setText("❌ Ləğv et ❌");
                break;
            case "turkish":
                cancelButton.setText("❌ İptal ❌");
                break;
            case "portuguese":
                cancelButton.setText("❌ Cancelar ❌");
                break;
            case "arabic":
                cancelButton.setText("❌ إلغاء ❌");
                break;
            default:
                cancelButton.setText("❌ Cancel ❌");
                break;
        }
        cancelButton.setCallbackData("cancel_registration_in_luckyjet");
        secondRow.add(cancelButton);
        keyboard.add(secondRow);

        List<InlineKeyboardButton> fourthRow = new ArrayList<>();
        InlineKeyboardButton sendMessageToSupportButton = new InlineKeyboardButton();
        switch (language) {
            case "russian":
                sendMessageToSupportButton.setText("\uD83C\uDD98 Написать в Support \uD83C\uDD98");
                break;
            case "english":
                sendMessageToSupportButton.setText("\uD83C\uDD98 Write to Support \uD83C\uDD98");
                break;
            case "hindi":
                sendMessageToSupportButton.setText("\uD83C\uDD98 सपोर्ट को लिखें \uD83C\uDD98");
                break;
            case "brazilian":
                sendMessageToSupportButton.setText("\uD83C\uDD98 Escrever para o Suporte \uD83C\uDD98");
                break;
            case "spanish":
                sendMessageToSupportButton.setText("\uD83C\uDD98 Escribir al Soporte \uD83C\uDD98");
                break;
            case "uzbek":
                sendMessageToSupportButton.setText("\uD83C\uDD98 Qo'llab-quvvatlashga yozing \uD83C\uDD98");
                break;
            case "azerbaijani":
                sendMessageToSupportButton.setText("\uD83C\uDD98 Dəstəyə yazın \uD83C\uDD98");
                break;
            case "turkish":
                sendMessageToSupportButton.setText("\uD83C\uDD98 Destek Yaz \uD83C\uDD98");
                break;
            case "portuguese":
                sendMessageToSupportButton.setText("\uD83C\uDD98 Escrever para o Suporte \uD83C\uDD98");
                break;
            case "arabic":
                sendMessageToSupportButton.setText("\uD83C\uDD98 اكتب إلى الدعم \uD83C\uDD98");
                break;
            default:
                sendMessageToSupportButton.setText("\uD83C\uDD98 Write to Support \uD83C\uDD98");
                break;
        }
        sendMessageToSupportButton.setUrl(urlService.getSupportLink());
        fourthRow.add(sendMessageToSupportButton);
        keyboard.add(fourthRow);

        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup menuWithCheckTheDeposit(String language) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        InlineKeyboardButton cancelButton = new InlineKeyboardButton();

        switch (language) {
            case "russian":
                cancelButton.setText("🔎 Проверить депозит 🔎");
                break;
            case "english":
                cancelButton.setText("🔎 Check Deposit 🔎");
                break;
            case "hindi":
                cancelButton.setText("🔎 जमा की जाँच करें 🔎");
                break;
            case "brazilian":
                cancelButton.setText("🔎 Verificar Depósito 🔎");
                break;
            case "spanish":
                cancelButton.setText("🔎 Verificar Depósito 🔎");
                break;
            case "uzbek":
                cancelButton.setText("🔎 Depozitni Tekshirish 🔎");
                break;
            case "azerbaijani":
                cancelButton.setText("🔎 Depoziti Yoxla 🔎");
                break;
            case "turkish":
                cancelButton.setText("🔎 Depozito Kontrolü 🔎");
                break;
            case "portuguese":
                cancelButton.setText("🔎 Verificar Depósito 🔎");
                break;
            case "arabic":
                cancelButton.setText("🔎 تحقق من الإيداع 🔎");
                break;
            default:
                cancelButton.setText("🔎 Check Deposit 🔎");
                break;
        }

        cancelButton.setCallbackData("luckyjet_give_signal");
        firstRow.add(cancelButton);
        keyboard.add(firstRow);

        List<InlineKeyboardButton> secondRow = new ArrayList<>();
        InlineKeyboardButton cancelDepositButton = new InlineKeyboardButton();

        switch (language) {
            case "russian":
                cancelDepositButton.setText("❌ Отмена ❌");
                break;
            case "english":
                cancelDepositButton.setText("❌ Cancel ❌");
                break;
            case "hindi":
                cancelDepositButton.setText("❌ रद्द करें ❌");
                break;
            case "brazilian":
                cancelDepositButton.setText("❌ Cancelar ❌");
                break;
            case "spanish":
                cancelDepositButton.setText("❌ Cancelar ❌");
                break;
            case "uzbek":
                cancelDepositButton.setText("❌ Bekor qilish ❌");
                break;
            case "azerbaijani":
                cancelDepositButton.setText("❌ Ləğv et ❌");
                break;
            case "turkish":
                cancelDepositButton.setText("❌ İptal ❌");
                break;
            case "portuguese":
                cancelDepositButton.setText("❌ Cancelar ❌");
                break;
            case "arabic":
                cancelDepositButton.setText("❌ إلغاء ❌");
                break;
            default:
                cancelDepositButton.setText("❌ Cancel ❌");
                break;
        }

        cancelDepositButton.setCallbackData("luckyjet");
        firstRow.add(cancelDepositButton);
        keyboard.add(secondRow);

        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup sendWebAppButton(User user,String language, String oneWinUrl, UrlService urlService) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> secondRow = new ArrayList<>();
        List<InlineKeyboardButton> firstRow = new ArrayList<>();

        InlineKeyboardButton webAppButton = new InlineKeyboardButton();

        switch (language) {
            case "russian":
                webAppButton.setText("\uD83C\uDF10 Получить сигнал \uD83C\uDF10");
                break;
            case "english":
                webAppButton.setText("\uD83C\uDF10 Get the signal \uD83C\uDF10");
                break;
            case "hindi":
                webAppButton.setText("\uD83C\uDF10 संकेत प्राप्त करें \uD83C\uDF10");
                break;
            case "brazilian":
                webAppButton.setText("\uD83C\uDF10 Obter o sinal \uD83C\uDF10");
                break;
            case "spanish":
                webAppButton.setText("\uD83C\uDF10 Obtener la señal \uD83C\uDF10");
                break;
            case "uzbek":
                webAppButton.setText("\uD83C\uDF10 Signali olish \uD83C\uDF10");
                break;
            case "azerbaijani":
                webAppButton.setText("\uD83C\uDF10 Siqnalı al \uD83C\uDF10");
                break;
            case "turkish":
                webAppButton.setText("\uD83C\uDF10 Sinyali al \uD83C\uDF10");
                break;
            case "portuguese":
                webAppButton.setText("\uD83C\uDF10 Obter o sinal \uD83C\uDF10");
                break;
            case "arabic":
                webAppButton.setText("\uD83C\uDF10 احصل على الإشارة \uD83C\uDF10");
                break;
            default:
                webAppButton.setText("\uD83C\uDF10 Get the signal \uD83C\uDF10");
                break;
        }
        // Использование WebAppInfo для указания URL веб-приложения
        WebAppInfo webAppInfo = new WebAppInfo();
        webAppInfo.setUrl(urlService.getIp() + "/luckyjet?oneWinUrl=" + urlService.getOneWinUrlWithLuckyJet(user)); // URL вашего веб-приложения с параметром
        webAppButton.setWebApp(webAppInfo);

        firstRow.add(webAppButton);
        rowsInline.add(firstRow);


        // Добавление кнопки для открытия сайта с 1WIN
        InlineKeyboardButton oneWinButton = new InlineKeyboardButton();
        switch (language) {
            case "russian":
                oneWinButton.setText("🔗 Открыть 1WIN 🔗");
                break;
            case "english":
                oneWinButton.setText("🔗 Open 1WIN 🔗");
                break;
            case "hindi":
                oneWinButton.setText("🔗 1WIN खोलें 🔗");
                break;
            case "brazilian":
                oneWinButton.setText("🔗 Abrir 1WIN 🔗");
                break;
            case "spanish":
                oneWinButton.setText("🔗 Abrir 1WIN 🔗");
                break;
            case "uzbek":
                oneWinButton.setText("🔗 1WINni ochish 🔗");
                break;
            case "azerbaijani":
                oneWinButton.setText("🔗 1WIN aç 🔗");
                break;
            case "turkish":
                oneWinButton.setText("🔗 1WIN aç 🔗");
                break;
            case "portuguese":
                oneWinButton.setText("🔗 Abrir 1WIN 🔗");
                break;
            case "arabic":
                oneWinButton.setText("🔗 افتح 1WIN 🔗");
                break;
            default:
                oneWinButton.setText("🔗 Open 1WIN 🔗");
                break;
        }
        oneWinButton.setUrl(oneWinUrl);

        secondRow.add(oneWinButton);
        rowsInline.add(secondRow);
        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }





}
