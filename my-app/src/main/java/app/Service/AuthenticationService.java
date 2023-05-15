package app.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import app.Models.User;
import app.Util.Exceptions.WrongCredentialsException;

public class AuthenticationService {
    @PersistenceContext
    private EntityManager em;

    private User currentUser;

    public User login(String name, String password) throws WrongCredentialsException{

        TypedQuery<User> query = em.createQuery(
            "Select u from User u where r.email =?1",
            User.class);
        User user = query.getSingleResult();

        if(user.checkPassword(password)){
            return user;
        }

        throw new WrongCredentialsException();
    }
}
