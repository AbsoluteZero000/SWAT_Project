package app.Service;

import java.util.HashSet;
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
import app.Util.Communication_Classes.MenuWrapper;
import app.Util.Communication_Classes.RestaurantComm;


@Stateless
public class RestaurantOwnerService {
    @PersistenceContext
    private EntityManager em;

    public Restaurant addRestaurant(RestaurantComm restComm) {

        User user = new User(restComm.owner);
        Restaurant restaurant = new Restaurant(restComm);
        restaurant.setOwner(user);
        Set<Meal> menu = new HashSet<Meal>();

        for(int i = 0 ; i < restComm.menu.size(); i++){
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
    public Restaurant removeFromMenu(int restId){
        TypedQuery<Restaurant> query = em.createQuery(
            "select r from Restaurant r where r.id = ?1",
            Restaurant.class);
        query.setParameter(1, restId);
        Restaurant restaurant = query.getSingleResult();

        Set<Meal> menu = restaurant.getMenu();
        try{
        for(int i = 0; i < menu.size(); i++){
            Meal meal = (Meal)menu.toArray()[i];
            menu.remove(meal);
            meal.removeRestaurant();
            em.merge(meal);
        }
    }
    catch(Exception e){
        System.out.println("everything is fine :burning emoji:");
    }
        restaurant.setMenu(new HashSet<Meal>());
        em.merge(restaurant);
        return restaurant;
    }
    public Restaurant editMenu(Restaurant restaurant, MenuWrapper menuWrapper) {

        Set<Meal> menu = new HashSet<Meal>();
        for (int i = 0; i < menuWrapper.menu.size(); i++) {
            Meal meal = new Meal(menuWrapper.menu.get(i));
            menu.add(meal);
            em.persist(meal);
        }
        restaurant.setMenu(menu);
        System.out.print(((Meal)(restaurant.getMenu().toArray()[0])).getName());
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

    public RestaurantReport getRestaurantReport(int id){
        TypedQuery<Restaurant> query = em.createQuery("SELECT r FROM Restaurant r Join r.menu m WHERE r.id =:id",
            Restaurant.class);
        query.setParameter("id", id);
        List<Restaurant> restaurants = query.getResultList();
        return new RestaurantReport(restaurants.get(0));
    }
}
