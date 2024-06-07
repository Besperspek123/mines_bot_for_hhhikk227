package com.example.tgbot.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "deposit")
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "playerID")
    private String playerID;

    public Deposit(String playerID){
        this.playerID = playerID;
    }
    public Deposit(){
    }

}
