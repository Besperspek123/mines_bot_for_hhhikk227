package com.example.tgbot.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "registration")
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "playerID")
    private String playerID;

    public Registration(String playerID){
        this.playerID = playerID;
    }
    public Registration(){
    }

}
