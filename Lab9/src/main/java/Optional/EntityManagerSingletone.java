package Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerSingletone {
    private static EntityManagerSingletone instance;
    private EntityManagerFactory factory;
    EntityManager em;

    private EntityManagerSingletone() {
        factory = Persistence.createEntityManagerFactory("MyAppPU");
        em = factory.createEntityManager();
    }

    public static EntityManagerSingletone getInstance() {
        if (instance == null) {
            instance = new EntityManagerSingletone();
        }

        return instance;
    }

    public EntityManager getEntityManager() {
        return em;
    }
}
