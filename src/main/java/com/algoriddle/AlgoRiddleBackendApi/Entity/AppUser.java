package com.algoriddle.AlgoRiddleBackendApi.Entity;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Entity
public class AppUser {
    @Id
    @Column(name = "User_ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    public @Getter @Setter UUID ID;
    public @Getter @Setter String name;
    public @Getter @Setter String username;
    public @Getter @Setter String email;

    protected AppUser() {}

    public AppUser(String name, String username, String email) {
        this.name = name;
        this.username = username;
        this.email = email;
    }
}
