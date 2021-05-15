package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerSingleton {
    private static EntityManagerSingleton instance;
    private EntityManagerFactory factory;
    EntityManager em;

    private EntityManagerSingleton() {
        factory = Persistence.createEntityManagerFactory("myPersist");
        em = factory.createEntityManager();
    }

    public static EntityManagerSingleton getInstance() {
        if (instance == null) {
            instance = new EntityManagerSingleton();
        }

        return instance;
    }

    public EntityManager getEntityManager() {
        return em;
    }
}
