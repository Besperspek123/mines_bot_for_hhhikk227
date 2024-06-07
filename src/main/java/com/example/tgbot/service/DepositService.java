package com.example.tgbot.service;


import com.example.tgbot.entity.Deposit;
import com.example.tgbot.entity.Registration;
import com.example.tgbot.exception.BadOneWinIdException;
import com.example.tgbot.exception.PlayerIsAlreadyRegisteredException;
import com.example.tgbot.repository.DepositRepository;
import com.example.tgbot.repository.RegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DepositService {



    private final DepositRepository depositRepository;

    @Autowired
    public DepositService(DepositRepository depositRepository){
        this.depositRepository = depositRepository;
    }

    public void saveDeposit(String playerId){
        if(!playerId.matches("^\\d{8}$")){
            throw new BadOneWinIdException("Format id is incorrect");
        }
        Deposit deposit = new Deposit();
        deposit.setPlayerID(playerId);
        depositRepository.save(deposit);
        log.info("Пользователь с id: " + playerId + " сделал первый депозит");
    }
    public boolean isPlayerDeposited(String playerID){
        Deposit deposit= depositRepository.getByPlayerID(playerID);
        if (deposit != null){
            return true;
        }
        return false;
    }

}
