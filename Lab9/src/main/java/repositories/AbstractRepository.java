package repositories;

import com.company.EntityManagerSingletone;
import entities.Genre;
import entities.Movie;

import javax.persistence.EntityManager;
import java.util.List;

public class AbstractRepository<T> {

    public T dummy;

    public AbstractRepository(T obj) {
        dummy = obj;
    }

    public void create(T obj) {
        EntityManager em = EntityManagerSingletone.getInstance().getEntityManager();
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
    }

    public T findById(int id) {
        EntityManager em = EntityManagerSingletone.getInstance().getEntityManager();
        if (dummy instanceof Movie)
            return (T) em.find(Movie.class, id);
        else if (dummy instanceof Genre)
            return (T) em.find(Genre.class, id);

        return null;
    }

    public List<T> findAll(){
        EntityManager em = EntityManagerSingletone.getInstance().getEntityManager();

        if(dummy instanceof Movie)
            return (List<T>) em.createNamedQuery("Movie.findAll").getResultList();
        if(dummy instanceof Genre)
            return (List<T>) em.createNamedQuery("Genre.findAll").getResultList();
        return null;
    }

    public List<T> findByName(String name) {

        EntityManager em = EntityManagerSingletone.getInstance().getEntityManager();
        if (dummy instanceof Movie)
            return em.createNamedQuery("Movie.findByName").setParameter("name", name).getResultList();
        else if (dummy instanceof Genre)
            return em.createNamedQuery("Genre.findByName").setParameter("name", name).getResultList();

        return null;
    }
}
