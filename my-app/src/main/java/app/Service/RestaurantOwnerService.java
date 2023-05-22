package app.Service;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import app.Models.Meal;
import app.Models.Restaurant;
import app.Models.User;
import app.Util.RestaurantReport;
import app.Util.Communication_Classes.MenuWrapper;
import app.Util.Communication_Classes.RestaurantComm;

@Stateless
@PermitAll
public class RestaurantOwnerService {
    @PersistenceContext
    private EntityManager em;

    public Restaurant addRestaurant(RestaurantComm restComm) {

        User user = new User(restComm.owner.name);
        Restaurant restaurant = new Restaurant(restComm);
        restaurant.setOwner(user);
        Set<Meal> menu = new HashSet<Meal>();

        for (int i = 0; i < restComm.menu.size(); i++) {
            Meal meal = new Meal(restComm.menu.get(i));
            meal.setRestaurant(restaurant);
            menu.add(meal);
        }

        restaurant.setMenu(menu);

        em.persist(user);
        em.persist(restaurant);

        return restaurant;
    }

    public Restaurant editMenu(int id, MenuWrapper menuWrapper) {
        TypedQuery<Restaurant> query = em.createQuery(
                "select r from Restaurant r where r.id = ?1",
                Restaurant.class);
        query.setParameter(1, id);
        Restaurant restaurant = query.getSingleResult();
        Set<Meal> menu = new HashSet<Meal>();

        TypedQuery<Meal> query2 = em.createQuery("select m from Meal m where m.name = ?1", Meal.class);
        for (int i = 0; i < menuWrapper.menu.size(); i++) {
            query2.setParameter(1, menuWrapper.menu.get(i).name);
            Meal meal;
            try {
                meal = query2.getSingleResult();
            } catch (Exception e) {
                meal = new Meal(menuWrapper.menu.get(i));
                em.persist(meal);
            }
            menu.add(meal);
        }
        restaurant.setMenu(menu);
        em.persist(restaurant);
        return restaurant;
    }

    public Restaurant getRestaurantDetails(int id) {
        TypedQuery<Restaurant> query = em.createQuery("SELECT r FROM Restaurant r WHERE r.id =:id",
                Restaurant.class);
        query.setParameter("id", id);
        Restaurant restaurant = query.getSingleResult();
        return restaurant;
    }

    public RestaurantReport getRestaurantReport(int id) {
        TypedQuery<Restaurant> query = em.createQuery("SELECT r FROM Restaurant r WHERE r.id =:id",
        Restaurant.class);
        query.setParameter("id", id);
        Restaurant restaurant = query.getSingleResult();
        return new RestaurantReport(restaurant);
    }
}
