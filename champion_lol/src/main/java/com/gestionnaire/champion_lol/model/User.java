package com.gestionnaire.champion_lol.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import org.mindrot.jbcrypt.BCrypt;

@Entity
public class User {

    @Id
    private String username;
    private String password;
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public boolean checkPassword(String plaintext) {
        return BCrypt.checkpw(plaintext, this.password);
    }
    public String getPassword() {
        return this.password;
    }


    public void setPassword(String plaintext) {
        this.password = BCrypt.hashpw(plaintext, BCrypt.gensalt());
    }
}