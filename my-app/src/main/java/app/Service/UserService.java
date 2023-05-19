package app.Service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import app.Models.Restaurant;
import app.Models.User;

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

    public void init(){

        User user = new User("ahmed");
        Restaurant restaurant = new Restaurant("Mcdonalds", user);
        restaurant.setOwner(user);
        em.persist(user);
        em.persist(restaurant);
    }

}
