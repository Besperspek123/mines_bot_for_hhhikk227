package com.example.tgbot.repository;

import com.example.tgbot.entity.BotTokenFromDatabase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BotTokenRepository extends JpaRepository<BotTokenFromDatabase, Long> {

    BotTokenFromDatabase getByName(String name);
}
