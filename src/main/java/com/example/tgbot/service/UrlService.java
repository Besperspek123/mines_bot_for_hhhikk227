package com.example.tgbot.service;


import com.example.tgbot.entity.UrlFromDatabase;
import com.example.tgbot.entity.User;
import com.example.tgbot.repository.UrlRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UrlService {


    private final UrlRepository urlRepostitory;

    @Autowired
    public UrlService(UrlRepository urlRepository) {
        this.urlRepostitory = urlRepository;
    }

    public String getUrlByName(String name){
        return urlRepostitory.getByName(name).getUrl()  ;
    }

    public String getOneWinUrl(User user){
        String baseUrl;
        if(user.getLanguage().equals("russian") || user.getLanguage().equals("uzbek") || user.getLanguage().equals("azerbaijani")){
            baseUrl = getRuOneWinUrl();
        }
        else {
            baseUrl = getEngOneWinUrl();
        }

        int hashIndex = baseUrl.indexOf('#');
        String beforeHash = baseUrl;
        String afterHash = "";

        // Если в URL есть якорь, разделяем URL на две части: до и после якоря.
        if (hashIndex != -1) {
            beforeHash = baseUrl.substring(0, hashIndex);
            afterHash = baseUrl.substring(hashIndex);  // включает символ '#'
        }

        // Добавляем параметр sub1 перед якорем.
        String finalUrl = beforeHash + (beforeHash.contains("?") ? "&" : "?") + "sub1=" + user.getChatId() + afterHash;
        return finalUrl;
    }

    public String getOneWinUrlWithMines(User user) {
        String baseUrl;
        if (user.getLanguage().equals("russian") || user.getLanguage().equals("uzbek") || user.getLanguage().equals("azerbaijani")) {
            baseUrl = getRuOneWinUrl();
        } else {
            baseUrl = getEngOneWinUrl();
        }

        // Извлекаем домен из baseUrl
        String domain = getDomainFromUrl(baseUrl);

        // Возвращаем нужную ссылку
        return domain + "/casino/play/1play_1play_mines";
    }

    private String getDomainFromUrl(String url) {
        try {
            java.net.URL netUrl = new java.net.URL(url);
            return netUrl.getProtocol() + "://" + netUrl.getHost();
        } catch (java.net.MalformedURLException e) {
            e.printStackTrace();
            return ""; // Или обработайте ошибку иначе, если необходимо
        }
    }
    public String getOneWinUrlWithLuckyJet(User user){
        String baseUrl;
        if (user.getLanguage().equals("russian") || user.getLanguage().equals("uzbek") || user.getLanguage().equals("azerbaijani")) {
            baseUrl = getRuOneWinUrl();
        } else {
            baseUrl = getEngOneWinUrl();
        }

        // Извлекаем домен из baseUrl
        String domain = getDomainFromUrl(baseUrl);

        // Возвращаем нужную ссылку
        return domain + "/casino/play/1play_1play_luckyjet";
    }
    public String getOneWinUrlWithAviator(User user){
        String baseUrl;
        if (user.getLanguage().equals("russian") || user.getLanguage().equals("uzbek") || user.getLanguage().equals("azerbaijani")) {
            baseUrl = getRuOneWinUrl();
        } else {
            baseUrl = getEngOneWinUrl();
        }

        // Извлекаем домен из baseUrl
        String domain = getDomainFromUrl(baseUrl);

        // Возвращаем нужную ссылку
        return domain + "/casino/play/aviator";
    }
    public String getOneWinUrlWithCoin(User user){
        String baseUrl;
        if (user.getLanguage().equals("russian") || user.getLanguage().equals("uzbek") || user.getLanguage().equals("azerbaijani")) {
            baseUrl = getRuOneWinUrl();
        } else {
            baseUrl = getEngOneWinUrl();
        }

        // Извлекаем домен из baseUrl
        String domain = getDomainFromUrl(baseUrl);

        // Возвращаем нужную ссылку
        return domain + "/casino/play/1play_1win_coinflip";
    }

    public String getRuOneWinUrl(){
        return urlRepostitory.getByName("onewin").getUrl();
    }
    public String getIp(){
        return urlRepostitory.getByName("ip").getUrl();
    }
    public String getEngOneWinUrl(){
        return urlRepostitory.getByName("onewineng").getUrl();
    }

    public String getTelegramChannelUrl(User user){
        if(user.getLanguage().equals("russian") || user.getLanguage().equals("uzbek") || user.getLanguage().equals("azerbaijani") ){
            return getRUTelegramChannelUrl();
        }
        else {
            return getEngTelegramChannelUrl();
        }
    }
    public String getRUTelegramChannelUrl(){
        return urlRepostitory.getByName("telegram").getUrl();
    }
    public String getEngTelegramChannelUrl(){
        return urlRepostitory.getByName("telegrameng").getUrl();
    }
    public void setOneXbetUrl(String newUrl){
        if(newUrl.isEmpty()){
            throw new NullPointerException("Ссылка не может быть пустой");
        }
        UrlFromDatabase oldUrl = urlRepostitory.getByName("onexbet");
        oldUrl.setUrl(newUrl);
        urlRepostitory.save(oldUrl);
    }
    public void setRuOneWinUrl(String newUrl){
        if(newUrl.isEmpty()){
            throw new NullPointerException("Ссылка не может быть пустой");
        }
        UrlFromDatabase oldUrl = urlRepostitory.getByName("onewin");
        oldUrl.setUrl(newUrl);
        urlRepostitory.save(oldUrl);
    }
    public void setEngOneWinUrl(String newUrl){
        if(newUrl.isEmpty()){
            throw new NullPointerException("Ссылка не может быть пустой");
        }
        UrlFromDatabase oldUrl = urlRepostitory.getByName("onewineng");
        oldUrl.setUrl(newUrl);
        urlRepostitory.save(oldUrl);
    }


    public void setRuChannelUrl(String newUrl){
        if(newUrl.isEmpty()){
            throw new NullPointerException("Ссылка не может быть пустой");
        }
        UrlFromDatabase oldUrl = urlRepostitory.getByName("telegram");
        oldUrl.setUrl(newUrl);
        urlRepostitory.save(oldUrl);
    }
    public void setEngChannelUrl(String newUrl){
        if(newUrl.isEmpty()){
            throw new NullPointerException("Ссылка не может быть пустой");
        }
        UrlFromDatabase oldUrl = urlRepostitory.getByName("telegrameng");
        oldUrl.setUrl(newUrl);
        urlRepostitory.save(oldUrl);
    }

    public String getSupportLink() {
        UrlFromDatabase url = urlRepostitory.getByName("support");
        return url.getUrl();
    }

    public void setSupport(String supportUrl) {
        UrlFromDatabase url = urlRepostitory.getByName("support");
        url.setUrl(supportUrl);
        urlRepostitory.save(url);
    }


}