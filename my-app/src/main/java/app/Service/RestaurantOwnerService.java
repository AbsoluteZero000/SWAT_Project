package app.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import app.Models.Meal;
import app.Models.Restaurant;
import app.Models.User;
import app.Util.RestaurantReport;
import app.Util.Communication_Classes.EditRestComm;
import app.Util.Communication_Classes.OrderComm;
import app.Util.Communication_Classes.RestaurantComm;

@Stateless
public class RestaurantOwnerService {
    @PersistenceContext
    private EntityManager em;

    public Restaurant createMenu(OrderComm orderComm) {
        TypedQuery<Restaurant> query = em.createQuery(
                "select r from Restaurant r where r.id = ?1",
                Restaurant.class);
        query.setParameter(1, orderComm.restaurantId);
        Restaurant restaurant = query.getSingleResult();
        for (int i = 0; i < orderComm.meals.size(); i++) {
            if (em.contains(orderComm.meals.get(i)))
                continue;
            em.persist(orderComm.meals.get(i));
        }
        Set<Meal> tempMeals = new HashSet<>();
        for (int i = 0; i < orderComm.meals.size(); i++)
            tempMeals.add(new Meal(orderComm.meals.get(i)));
        restaurant.setMenu(tempMeals);
        em.merge(restaurant);
        return restaurant;
    }

    public Restaurant addRestaurant(RestaurantComm restComm) {

        User user = new User(restComm.owner);
        Restaurant restaurant = new Restaurant(restComm);
        restaurant.setOwner(user);
        Set<Meal> menu = new HashSet<Meal>();

        for (int i = 0; i < restComm.menu.size(); i++) {
            Meal meal = new Meal(restComm.menu.get(i));
            meal.setRestaurant(restaurant);
            menu.add(meal);
            em.persist(meal);
        }

        restaurant.setMenu(menu);

        em.persist(user);
        em.persist(restaurant);

        return restaurant;
    }

    public Restaurant editRestaurantMenu(EditRestComm editRestComm) {
        TypedQuery<Restaurant> query = em.createQuery(
                "select r from Restaurant r where r.id = ?1",
                Restaurant.class);
        // System.out.println(editRestComm.restId);
        query.setParameter(1, editRestComm.restId);
        Restaurant restaurant = query.getSingleResult();

        if (restaurant == null)
            return null;

        Iterator<Meal> meals = restaurant.getMenu().iterator();
        while (meals.hasNext()) {
            em.remove(meals.next());
        }
        Set<Meal> menu = new HashSet<Meal>();
        for (int i = 0; i < editRestComm.restComm.menu.size(); i++) {
            Meal meal = new Meal(editRestComm.restComm.menu.get(i));
            meal.setRestaurant(restaurant);
            menu.add(meal);
            em.persist(meal);
        }

        restaurant.setMenu(menu);
        em.merge(restaurant);
        return restaurant;
    }

    public Restaurant getRestaurantDetails(int id) {
        System.out.println("hey");
        TypedQuery<Restaurant> query = em.createQuery("SELECT r FROM Restaurant r WHERE r.id =:id",
                Restaurant.class);
        query.setParameter("id", id);
        Restaurant restaurant = query.getSingleResult();
        return restaurant;
    }

    public RestaurantReport getRestaurantReport(int id) {
        TypedQuery<Restaurant> query = em.createQuery("SELECT r FROM Restaurant r Join r.menu m WHERE r.id =:id",
                Restaurant.class);
        query.setParameter("id", id);
        List<Restaurant> restaurants = query.getResultList();
        return new RestaurantReport(restaurants.get(0));
    }

}
