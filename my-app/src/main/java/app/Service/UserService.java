package app.Service;

import java.io.IOException;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.BadRequestException;

import app.Models.User;
import app.Util.Communication_Classes.LoginWrapper;
import app.Util.Exceptions.UserAlreadyExistException;

@Stateless
@PermitAll
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
            TypedQuery<User> query = em.createQuery("select u from User u where u.name = :username", User.class);
            query.setParameter("username", name);
        try{
            return query.getSingleResult();
        }
        catch(Exception e){
            return null;
        }
    }


    public User logIn(LoginWrapper loginWrapper){
        User user = findUserByName(loginWrapper.name);
        if(user == null)
            throw new BadRequestException("user doesn't exist");

        return user;
    }
    public User signup(LoginWrapper loginWrapper) throws UserAlreadyExistException, IOException{
        User user = findUserByName(loginWrapper.name);
        if(user == null)
            user = new User(loginWrapper);
        Runtime.getRuntime().exec(String.format("cmd.exe /c add-user.bat -a -u %s -p %s -g %s", loginWrapper.name, loginWrapper.password, loginWrapper.role));
        return addUser(user);

    }

}
