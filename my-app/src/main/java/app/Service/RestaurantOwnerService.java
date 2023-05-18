package app.Service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import app.Models.Restaurant;
import app.Models.User;
import app.Util.Communication_Classes.RestaurantComm;

@Stateless
public class RestaurantOwnerService {
    @PersistenceContext
    private EntityManager em;

    public Restaurant addRestaurant(RestaurantComm restComm) {

        User user = new User(restComm.owner);
        // return user;
        Restaurant restaurant = new Restaurant(restComm.name, user);
        try {
            // return restaurant;
            em.persist(user);
            em.persist(restaurant);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return restaurant;
    }

    // public void editRestaurantMenu(int id, Set<Meal> meals) {

    // TypedQuery<Restaurant> query = em.createQuery("select r from Restaurant r
    // where r.id =? 1", Restaurant.class);
    // query.setParameter(1, meals);
    // Restaurant actualRestaurant = query.getSingleResult();

    // if (actualRestaurant == null)
    // throw new NullPointerException();

    // actualRestaurant.setMeals(meals);
    // em.merge(actualRestaurant);
    // }

    public Restaurant getRestaurantDetails(int id) {
        TypedQuery<Restaurant> query = em.createQuery("SELECT r FROM Restaurant r WHERE r.id =:id",
                Restaurant.class);
        query.setParameter("id", id);
        List<Restaurant> restaurants = query.getResultList();
        if (restaurants.isEmpty()) {
            Restaurant res = new Restaurant();
            res.setName("null");
            return res;
        } else {
            return restaurants.get(0);
        }
    }
    // public Restaurant addRestaurant(String name, User owner){
    // Restaurant restaurant = new Restaurant(name, owner);
    // em.persist(restaurant);
    // return restaurant;
    // }
    // public RestaurantReport getRestaurantReport(Restaurant restaurant) {
    // return new RestaurantReport(restaurant);
    // }

}
