package com.gestionnaire.champion_lol.dao;

import com.gestionnaire.champion_lol.model.Champion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class ChampionDao {

    private EntityManager entityManager = Persistence.createEntityManagerFactory("my-persistence-unit").createEntityManager();

    public Champion createChampion(Champion champion) {
        entityManager.getTransaction().begin();
        entityManager.persist(champion);
        entityManager.getTransaction().commit();
        return champion;
    }

    public Champion getChampion(Long id) {
        return entityManager.find(Champion.class, id);
    }

    public Champion updateChampion(Champion champion) {
        entityManager.getTransaction().begin();
        Champion updatedChampion = entityManager.merge(champion);
        entityManager.getTransaction().commit();
        return updatedChampion;
    }

    public void deleteChampion(Long id) {
        entityManager.getTransaction().begin();
        Champion champion = entityManager.find(Champion.class, id);
        if (champion != null) {
            entityManager.remove(champion);
        }
        entityManager.getTransaction().commit();
    }
}
