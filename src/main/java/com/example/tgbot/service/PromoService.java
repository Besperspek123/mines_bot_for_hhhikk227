package com.example.tgbot.service;


import com.example.tgbot.entity.PromoFromDatabase;
import com.example.tgbot.entity.User;
import com.example.tgbot.repository.PromoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PromoService {


    private final PromoRepository promoRepository;

    @Autowired
    public PromoService(PromoRepository promoRepository) {
        this.promoRepository = promoRepository;
    }

//    public String getUrlByName(String name){
//        return promoRepository.getByName(name).getPromo()  ;
//    }

    public String getOneXbetPromo(){
        return promoRepository.getByName("onexbet").getPromo();
    }

    public String getOneWinPromo(User user) {
        if (user.getLanguage().equals("russian")) {
            return promoRepository.getByName("onewin").getPromo();
        } else if (user.getLanguage().equals("english")) {
            return promoRepository.getByName("onewineng").getPromo();
        }
        return null;
    }

    public String getRuOneWinPromo(){
        return promoRepository.getByName("onewin").getPromo();
    }

    public String getEngOneWinPromo(){
        return promoRepository.getByName("onewineng").getPromo();
    }
    public String getStarzPromo(){
        return promoRepository.getByName("starz").getPromo();
    }
    public String getMelbetPromo(){
        return promoRepository.getByName("melbet").getPromo();
    }
    public String getBetwinnerPromo(){
        return promoRepository.getByName("betwinner").getPromo();
    }

//    public void setPromoByName(String name, String newPromo){
//        if (name.isEmpty()){
//            throw new NullPointerException("Имя не может быть равно нулю");
//        }
//        PromoFromDatabase oldPromo = promoRepository.getByName(name);
//        oldPromo.setPromo(newPromo);
//        promoRepository.save(oldPromo);
//    }
    public void setOneXbetPromo(String newPromo){
        if(newPromo.isEmpty()){
            throw new NullPointerException("Промо не может быть пуст");
        }
        PromoFromDatabase oldPromo = promoRepository.getByName("onexbet");
        oldPromo.setPromo(newPromo);
        promoRepository.save(oldPromo);
    }

    public void setMinesPromo(String newPromo){
        if(newPromo.isEmpty()){
            throw new NullPointerException("Промо не может быть пуст");
        }
        PromoFromDatabase oldPromo = promoRepository.getByName("mines");
        oldPromo.setPromo(newPromo);
        promoRepository.save(oldPromo);
    }
    public void setMelbetPromo(String newPromo){
        if(newPromo.isEmpty()){
            throw new NullPointerException("Промо не может быть пуст");
        }
        PromoFromDatabase oldPromo = promoRepository.getByName("melbet");
        oldPromo.setPromo(newPromo);
        promoRepository.save(oldPromo);
    }
    public void setBetwinnerPromo(String newPromo){
        if(newPromo.isEmpty()){
            throw new NullPointerException("Промо не может быть пуст");
        }
        PromoFromDatabase oldPromo = promoRepository.getByName("betwinner");
        oldPromo.setPromo(newPromo);
        promoRepository.save(oldPromo);
    }
    public void setStarzPromo(String newPromo){
        if(newPromo.isEmpty()){
            throw new NullPointerException("Промо не может быть пуст");
        }
        PromoFromDatabase oldPromo = promoRepository.getByName("starz");
        oldPromo.setPromo(newPromo);
        promoRepository.save(oldPromo);
    }
    public void setRuOneWinPromo(String newPromo){
        if(newPromo.isEmpty()){
            throw new NullPointerException("Промо не может быть пуст");
        }
        PromoFromDatabase oldPromo = promoRepository.getByName("onewin");
        oldPromo.setPromo(newPromo);
        promoRepository.save(oldPromo);
    }
    public void setEngOneWinPromo(String newPromo){
        if(newPromo.isEmpty()){
            throw new NullPointerException("Промо не может быть пуст");
        }
        PromoFromDatabase oldPromo = promoRepository.getByName("onewineng");
        oldPromo.setPromo(newPromo);
        promoRepository.save(oldPromo);
    }


}
