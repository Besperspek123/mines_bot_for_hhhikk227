package com.example.tgbot.repository;

import com.example.tgbot.entity.UrlFromDatabase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<UrlFromDatabase, Long> {

    UrlFromDatabase getByName(String name);
}
