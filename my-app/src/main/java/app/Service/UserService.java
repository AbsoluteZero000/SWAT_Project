package app.Service;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import app.Models.User;
import app.Util.Exceptions.WrongCredentialsException;

@Stateless
public class UserService {
    @PersistenceContext
    private EntityManager em;

    public void addUser(User u){
        em.persist(u);
    }
}
