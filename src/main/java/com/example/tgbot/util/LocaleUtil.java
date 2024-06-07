package com.example.tgbot.util;

import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class LocaleUtil {

    public static Locale getLocaleForLanguage(String language) {
        switch (language.toLowerCase()) {
            case "russian":
                return new Locale("ru");
            case "english":
                return new Locale("en");
            case "hindi":
                return new Locale("hi");
            case "brazilian":
                return new Locale("br");
            case "spanish":
                return new Locale("es");
            case "uzbek":
                return new Locale("uz");
            case "azerbaijani":
                return new Locale("az");
            case "turkish":
                return new Locale("tr");
            case "portuguese":
                return new Locale("pt");
            case "arabic":
                return new Locale("ar");
            default:
                return new Locale("en");
        }
    }
}
