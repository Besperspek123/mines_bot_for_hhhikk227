package com.example.tgbot.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class CustomBotConfig {

    //Влючение функции проверки подписки на телеграм канал
    private boolean checkSubscriptionToTelegram = true;


    public boolean isCheckSubscriptionToTelegram() {
        return checkSubscriptionToTelegram;
    }

    public void setCheckSubscriptionToTelegram(boolean checkSubscriptionToTelegram) {
        this.checkSubscriptionToTelegram = checkSubscriptionToTelegram;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:locale/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

}
