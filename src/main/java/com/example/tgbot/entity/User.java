package com.example.tgbot.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "users", schema = "public")
public class User {

    public User() {
    }

    public User(long id, String username, long chatId) {
        this.id = id;
        this.username = username;
        this.chatId = chatId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "isAdministrator")
    private Boolean isAdministrator;

    @Column(name = "username")
    private String username;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "language")
    private String language;

    @Column(name = "isEnteredToTheChannel")
    private Boolean isEnteredToTheChannel;

    @Column(name = "isDeposit")
    private Boolean isDeposit;

    @Column(name = "chatId")
    private long chatId;

    @Column(name = "oneWinId")
    private Long oneWinId;

    @Column(name = "isVerify")
    private Boolean isVerify;

    @Column(name = "dateRegistration")
    private LocalDate registrationDate;

    @Column(name = "awaitingMode")
    private Boolean awaitingMode;

    @Column(name = "isParticipant")
    private Boolean isParticipantInLottery;
}
