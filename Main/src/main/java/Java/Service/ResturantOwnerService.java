package Java.Service;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import Java.Models.Restaurant;
import Java.Models.RestaurantReport;

@RolesAllowed("RestaurantOwner")
@Stateless
public class ResturantOwnerService
{
    @PersistenceContext
    private EntityManager em;

    public void createRestaurantMenu(Restaurant restaurant){
        em.persist(restaurant);
    }
    public void editRestaurantMenu(Restaurant restaurant){

        TypedQuery<Restaurant> query = em.createQuery("select r from Restaurant r  where r.id =? 1", Restaurant.class);
        query.setParameter(1, restaurant.GetId());
        Restaurant actualRestaurant = query.getSingleResult();
        
        if(actualRestaurant == null)
            throw new NullPointerException();
        
        actualRestaurant.setMeals(restaurant.getMeals());
        em.merge(actualRestaurant);
    }

    public Restaurant getRestaurantDetails(int id){
        TypedQuery<Restaurant> query = em.createQuery("select r from Restaurant r  where r.id =? 1", Restaurant.class);
        query.setParameter(1, id);
        return query.getSingleResult();
    }

    public RestaurantReport getRestaurantReport(Restaurant restaurant){
        return new RestaurantReport(restaurant);
    }

}
