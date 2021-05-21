package repositories;

import entities.EntityManagerSingleton;
import entities.User;

import javax.persistence.Entity;
import javax.persistence.EntityManager;

public class UserRepository {

    public void create(User user) {
        EntityManager em = EntityManagerSingleton.getInstance().getEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }
    public User findById(int id){
        EntityManager em = EntityManagerSingleton.getInstance().getEntityManager();
        return (User) em.createNamedQuery("User.findById").setParameter("id", id).getSingleResult();
    }

    public User findByName(String name){
        EntityManager em = EntityManagerSingleton.getInstance().getEntityManager();
        return (User) em.createNamedQuery("User.findByUsername").setParameter("username", name).getSingleResult();
    }

    public static boolean usernameExists(String username){
        EntityManager em = EntityManagerSingleton.getInstance().getEntityManager();
        return em.createNamedQuery("User.findByUsername").setParameter("username", username).getResultList().size() == 1;
    }

    public boolean correctLoginInput(String username, String password){
        EntityManager em = EntityManagerSingleton.getInstance().getEntityManager();
        return em.createNamedQuery("User.correctLogin").setParameter("username", username).setParameter("password", password).getResultList().size() == 1;
    }
}
