package com.example.tgbot.repository;

import com.example.tgbot.entity.Deposit;
import com.example.tgbot.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
    Deposit getByPlayerID(String playerId);
}
