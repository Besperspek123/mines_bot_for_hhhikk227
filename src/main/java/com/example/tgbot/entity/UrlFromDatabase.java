package com.example.tgbot.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "url", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})
})
public class UrlFromDatabase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "url")
    private String url;

    public UrlFromDatabase(String name,String url){
        this.name = name;
        this.url = url;
    }
    public UrlFromDatabase(){
    }

}
