package com.example.tgbot.repository;

import com.example.tgbot.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    Registration getByPlayerID(String playerId);
}
