package com.gestionnaire.champion_lol.services;

import com.gestionnaire.champion_lol.model.LoginRequest;
import com.gestionnaire.champion_lol.model.User;
import com.gestionnaire.champion_lol.dao.UserDao;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.persistence.Persistence;

import java.security.Key;
import java.util.Date;

public class AuthenticationService {

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final UserDao userDao = new UserDao(Persistence.createEntityManagerFactory("my-persistence-unit").createEntityManager());

    public static String authenticate(LoginRequest loginRequest) {
        User user = userDao.getUser(loginRequest.getUsername());

        if (user != null && user.checkPassword(loginRequest.getPassword())) {
            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + 3600000);

            return Jwts.builder()
                    .setSubject(user.getUsername())
                    .setIssuedAt(now)
                    .setExpiration(expiryDate)
                    .signWith(key)
                    .compact();
        } else {
            return null;
        }
    }
}
