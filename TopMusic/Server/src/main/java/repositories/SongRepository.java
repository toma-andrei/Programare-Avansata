package repositories;

import entities.EntityManagerSingleton;
import entities.Genre;
import entities.Song;
import entities.User;
import org.hibernate.PersistentObjectException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SongRepository {
    public void create(Song song) {
//        EntityManager em = EntityManagerSingleton.getInstance().getEntityManager();
//        em.getTransaction().begin();
//        em.persist(song);
//        em.getTransaction().commit();
        EntityManagerFactory factory;
        EntityManager em;
        factory = Persistence.createEntityManagerFactory("myPersist");
        em = factory.createEntityManager();
        em.merge(song);
        em.persist(song);

    }

    public User findById(int id) {
        EntityManager em = EntityManagerSingleton.getInstance().getEntityManager();
        return (User) em.createNamedQuery("Song.findById").setParameter("id", id).getSingleResult();
    }
}
