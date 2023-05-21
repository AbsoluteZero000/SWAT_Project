package app.Service;

import java.io.IOException;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import app.Models.User;
import app.Util.Communication_Classes.LoginWrapper;
import app.Util.Exceptions.UserAlreadyExistException;

@Stateless
public class UserService {
    @PersistenceContext
    private EntityManager em;

    public UserService() {
    }

    public User addUser(User user) {
        em.persist(user);
        return user;
    }


    public User findUserByName(String name){

        try{
            TypedQuery<User> query = em.createQuery("select u from User where u.name = ?1", User.class);
            query.setParameter(1, name);
            return query.getSingleResult();
        }
        catch(Exception e){
            return null;
        }
    }
    public Boolean addAccount(LoginWrapper loginWrapper){
        return false;
    }

    @PermitAll
    public User signup(LoginWrapper loginWrapper) throws UserAlreadyExistException, IOException{

        User user = new User(loginWrapper.name);
        Runtime.getRuntime().exec(String.format("cmd.exe /c  add-user.bat -a -u %s -p %s -g %s", loginWrapper.name, loginWrapper.password, loginWrapper.role));
        
        return addUser(user);

    }

}
