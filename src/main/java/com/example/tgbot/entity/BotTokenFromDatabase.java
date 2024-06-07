package com.example.tgbot.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "token", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})
})
public class BotTokenFromDatabase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "token")
    private String token;

    public BotTokenFromDatabase(String name, String token){
        this.name = name;
        this.token = token;
    }
    public BotTokenFromDatabase(){
    }

}
