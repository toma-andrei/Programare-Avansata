package repositories;

import entities.EntityManagerSingleton;
import entities.Genre;
import entities.Song;
import entities.User;

import javax.persistence.EntityManager;

public class SongRepository {
    public void create(Song song) {
        EntityManager em = EntityManagerSingleton.getInstance().getEntityManager();
        em.getTransaction().begin();
        em.persist(song);
        em.getTransaction().commit();
    }

    public User findById(int id) {
        EntityManager em = EntityManagerSingleton.getInstance().getEntityManager();
        return (User) em.createNamedQuery("Song.findById").setParameter("id", id).getSingleResult();
    }
}
