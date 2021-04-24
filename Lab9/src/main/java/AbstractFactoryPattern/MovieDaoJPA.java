package AbstractFactoryPattern;

import com.company.EntityManagerSingletone;
import entities.Movie;

import javax.persistence.EntityManager;
import java.util.List;

public class MovieDaoJPA implements Dao<Movie> {
    @Override
    public void add(Movie obj) {

        EntityManager em = EntityManagerSingletone.getInstance().getEntityManager();
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
    }

    @Override
    public List<Movie> getAll() {
        EntityManager em = EntityManagerSingletone.getInstance().getEntityManager();
        return (List<Movie>) em.createNamedQuery("Movie.findAll").getResultList();

    }
}
