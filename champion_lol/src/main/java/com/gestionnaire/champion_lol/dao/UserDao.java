package com.gestionnaire.champion_lol.dao;

import com.gestionnaire.champion_lol.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class UserDao {

    private EntityManager entityManager;

    public UserDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public User createUser(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        return user;
    }

    public User getUser(String username) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public User updateUser(User user) {
        entityManager.getTransaction().begin();
        User updatedUser = entityManager.merge(user);
        entityManager.getTransaction().commit();
        return updatedUser;
    }

    public void deleteUser(String username) {
        User user = getUser(username);
        if (user != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(user);
            entityManager.getTransaction().commit();
        }
    }

    public void testConnection() {
        try {
            entityManager.getTransaction().begin();
            entityManager.getTransaction().commit();
            System.out.println("Connection to database successful!");
        } catch (Exception e) {
            System.out.println("Connection to database failed: " + e.getMessage());
        }
    }
}
