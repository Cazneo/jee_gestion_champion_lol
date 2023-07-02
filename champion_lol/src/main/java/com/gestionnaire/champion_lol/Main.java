package com.gestionnaire.champion_lol;

import com.gestionnaire.champion_lol.dao.UserDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        UserDao userDao = new UserDao(entityManager);
        userDao.testConnection();
    }
}
