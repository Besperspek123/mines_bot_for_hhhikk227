package com.example.tgbot.interfaces;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.InputStream;
import java.util.List;

public interface BotActions {
    void sendMessageWithInlineKeyboard(long chatId, String text, InlineKeyboardMarkup markup);
    void handleCallbackQuery(CallbackQuery callbackQuery);
    void sendMessageWithoutCallbackQuery(long chatId,String answer);
    void sendMessageWithCallbackQuery(long chatId, String firstAnswer, CallbackQuery callbackQuery);
    void sendMultiplePhotos(Long chatId, List<String> photoPaths);
    void sendPhoto(Long chatId,String photoPath);
    void sendPhoto(Long chatId, byte[] photoBytes, String broadcastText,String photoFileId);

    Integer sendMessageWithReturnIdMessage(long chatId, String textToSend, CallbackQuery callbackQuery);

    void deleteMessage(long chatId, int messageId);
    void editMessage(long chatId, int messageId, String newText);
    void sendMessageWithInlineKeyboardAndParseMARKDOWN(long chatId, String answer, InlineKeyboardMarkup inlineKeyboardMarkup);
    void sendMessageWithParseMARKDOWN(long chatId, String answer);
    void sendMessageWithPhoto(long chatId,String answer,String photoPath);
    void sendMessageWithPhotoAndKeyboard(long chatId,String answer,String Path,InlineKeyboardMarkup inlineKeyboardMarkup);

    void sendVideo(Long chatId, String videoPath);

    void sendMessageWithVideoAndKeyboard(long chatId, String answer, String path, String thumbNailPath, InlineKeyboardMarkup inlineKeyboardMarkup);


    void sendMessageWithParseMarkdownAndCallback(long chatId, String message, CallbackQuery callbackQuery);
    boolean sendBroadcastMessageWithPhoto(long chatId, String broadcastText, byte[] photoBytes);


    String uploadPhoto(long chatid,byte[] photoBytes); // For uploading the photo
    boolean sendBroadcastMessage(long chatId, String textToSend, String username) throws TelegramApiException;
    boolean sendBroadcastMessageWithPhotoUrl(long chatId, String broadcastText, String photoUrl) throws TelegramApiException;

    void sendPhotoWithFileId(Long chatId, String fileId, String broadcastText);
    void sendPhotoWithBytes(Long chatId, byte[] photoBytes, String broadcastText);
}

