package app.Service;

import java.io.IOException;
import java.util.List;
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

    public User addUser(User user) throws UserAlreadyExistException {
        TypedQuery<User> query = em.createQuery("select u from User u where u.name = :username", User.class);
        query.setParameter("username", user.getName());
        List<User> u = query.getResultList();
        if (u.isEmpty()) {
            em.persist(user);
            return user;
        }
        throw new UserAlreadyExistException();
    }


    public User findUserByName(String name){
            TypedQuery<User> query = em.createQuery("select u from User u where u.name = :username", User.class);
            query.setParameter("username", name);
        try{
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @PermitAll
    public User logIn(LoginWrapper loginWrapper){
        User user = findUserByName(loginWrapper.name);
        if(user == null)
            throw new BadRequestException("user doesn't exist");

        return user;
    }

    @PermitAll
    public User signup(LoginWrapper loginWrapper) throws UserAlreadyExistException, IOException{




        String[] path = new String[] {
            "cmd.exe", "/c", "add-user.bat",
            "-a",
            "-u", loginWrapper.name,
            "-p", loginWrapper.password,
            "-g", loginWrapper.role
        };
        ProcessBuilder processBuilder = new ProcessBuilder(path);
        try {
            Process process = processBuilder.start();
        } catch (Exception e) {
            return null;
        }
        return addUser(new User(loginWrapper));
    }

}
