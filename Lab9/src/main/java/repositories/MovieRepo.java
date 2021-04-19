package repositories;

import com.company.EntityManagerSingletone;
import entities.Movie;

import javax.persistence.EntityManager;
import java.util.List;

public class MovieRepo {
    public void create(Movie movie) {
        EntityManager em = EntityManagerSingletone.getInstance().getEntityManager();
        em.getTransaction().begin();
        em.persist(movie);
        em.getTransaction().commit();

    }

    public Movie findById(int id) {
        EntityManager em = EntityManagerSingletone.getInstance().getEntityManager();
        return em.find(Movie.class, id);
    }

    public List<Movie> findByName(String name) {
        EntityManager em = EntityManagerSingletone.getInstance().getEntityManager();
        return em.createNamedQuery("Movie.findByName").setParameter("name", name).getResultList();
    }
}
