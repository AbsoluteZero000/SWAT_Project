package app.Service;

import java.io.IOException;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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

    public User findUserByName(String name) {
        TypedQuery<User> query = em.createQuery("select u from User u where u.name = :username", User.class);
        query.setParameter("username", name);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean login(String username, String password) {
        User user = this.findUserByName(username);
        if (user.getPassword().equals(password))
            return true;
        return false;
    }

    public Boolean addAccount(LoginWrapper loginWrapper) {
        return false;
    }

    @PermitAll
    public User signup(LoginWrapper loginWrapper) throws UserAlreadyExistException, IOException {

        User user = new User(loginWrapper);
        // Runtime.getRuntime().exec(String.format("cmd.exe /c add-user.bat -a -u Guest
        // -p Guest -g Guest", loginWrapper.name,
        // loginWrapper.password, loginWrapper.role));

        return addUser(user);

    }

}
