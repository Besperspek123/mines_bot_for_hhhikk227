package com.example.tgbot.repository;

import com.example.tgbot.entity.PromoFromDatabase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromoRepository extends JpaRepository<PromoFromDatabase, Long> {

    PromoFromDatabase getByName(String name);
}
