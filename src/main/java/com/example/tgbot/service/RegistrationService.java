package com.example.tgbot.service;


import com.example.tgbot.entity.Registration;
import com.example.tgbot.exception.BadOneWinIdException;
import com.example.tgbot.exception.PlayerIsAlreadyRegisteredException;
import com.example.tgbot.repository.RegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RegistrationService {



    private final RegistrationRepository registrationRepository;

    @Autowired
    public RegistrationService(RegistrationRepository registrationRepository){
        this.registrationRepository = registrationRepository;
    }

    public void saveRegistration(String playerId){
        if (registrationRepository.getByPlayerID(playerId) != null){
            throw new PlayerIsAlreadyRegisteredException("Player with this id is already registered");
        }
        if(!playerId.matches("^\\d{8}$")){
            throw new BadOneWinIdException("Format id is incorrect");
        }
        Registration registration = new Registration();
        registration.setPlayerID(playerId);
        registrationRepository.save(registration);
        log.info("Пользователь с id: " + playerId + " был зарегистрирован на 1win");
    }
    public boolean isPlayerRegistered(String playerID){
        Registration registration= registrationRepository.getByPlayerID(playerID);
        if (registration != null){
            return true;
        }
        return false;
    }


}
