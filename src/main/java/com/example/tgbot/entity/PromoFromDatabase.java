package com.example.tgbot.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "promo", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})
})
public class PromoFromDatabase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "promo")
    private String promo;

    public PromoFromDatabase(String name, String promo){
        this.name = name;
        this.promo = promo;
    }
    public PromoFromDatabase(){
    }

}
