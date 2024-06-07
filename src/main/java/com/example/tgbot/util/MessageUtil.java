package com.example.tgbot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageUtil {

    private static MessageSource messageSource;

    @Autowired
    public MessageUtil(MessageSource messageSource) {
        MessageUtil.messageSource = messageSource;
    }

    public static String getMessage(String key, Locale locale) {
        return messageSource.getMessage(key, null, locale);
    }
}