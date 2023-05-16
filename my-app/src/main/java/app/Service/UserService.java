package app.Service;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import app.Models.User;

@Stateless
public class UserService {
    @PersistenceContext
    private EntityManager em;

    public UserService(){}
    public void addUser(User u){
        em.persist(u);
    }
}
