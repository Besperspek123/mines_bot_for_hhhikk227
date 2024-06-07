package com.example.tgbot.service;


import com.example.tgbot.entity.BotTokenFromDatabase;
import com.example.tgbot.entity.UrlFromDatabase;
import com.example.tgbot.repository.BotTokenRepository;
import com.example.tgbot.repository.UrlRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BotTokenService {


    private final BotTokenRepository botTokenRepository;

    @Autowired
    public BotTokenService(BotTokenRepository botTokenRepository) {
        this.botTokenRepository = botTokenRepository;
    }

    public void setTokenForPostbackBot(String token){
        BotTokenFromDatabase botTokenFromDatabase = botTokenRepository.getByName("postback");
        botTokenFromDatabase.setToken(token);
        botTokenRepository.save(botTokenFromDatabase);
        log.info("Токен postback бота был заменен на " + token);
    }
    public void setChatIdForPostbackBot(String token){
        BotTokenFromDatabase botTokenFromDatabase = botTokenRepository.getByName("chatid");
        botTokenFromDatabase.setToken(token);
        botTokenRepository.save(botTokenFromDatabase);
        log.info("Chatid пользователя postback бота был заменен на " + token);
    }
    public String getTokenForPostbackBot(){
        BotTokenFromDatabase botTokenFromDatabase = botTokenRepository.getByName("postback");
        return botTokenFromDatabase.getToken();
    }

    public String getChatId(){
        BotTokenFromDatabase botTokenFromDatabase = botTokenRepository.getByName("chatid");
        return botTokenFromDatabase.getToken();
    }


}
