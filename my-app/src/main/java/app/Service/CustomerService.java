package app.Service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import app.Util.Exceptions.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import app.Models.*;
import app.Util.Enums.OrderStatus;
import app.Util.Enums.Status;
import app.Util.OrderDetails;

@Stateless
public class CustomerService {

    @PersistenceContext
    private EntityManager em;

    public CustomerService() {
    }


    public OrderDetails createOrder(int restId, ArrayList<Integer> ids) {
        Orders order = new Orders();
        TypedQuery<Meal> query3 = em.createQuery(
            "Select m from Meal m  where m.id =?1",
            Meal.class);
        for (int i = 0; i < ids.size(); i++) {
            query3.setParameter(1,ids.get(0));
            Meal meal = query3.getSingleResult();
            if(meal == null)
                throw new NullPointerException("meal doesn't exist");

            meal.addOrderTo(order);
            order.addItemsToArray(meal);
        }

        TypedQuery<Restaurant> query2 = em.createQuery(
        "Select r from Restaurant r where r.id =?1",
        Restaurant.class);
        query2.setParameter(1, restId);
        Restaurant restaurant = query2.getSingleResult();

        order.setRestaurant(restaurant);
        restaurant.addOrder(order);

        TypedQuery<Runner> query = em.createQuery(
                "Select r from Runner r where r.status = app.Util.Enums.Status.AVAILABLE",
                Runner.class);
        query.setMaxResults(1);
        List<Runner> runners = query.getResultList();
        if (runners.size() == 0)
            throw new NullPointerException("no available runners");

        Runner runner = runners.get(0);
        runner.setStatus(Status.BUSY);
        order.setRunner(runner);

        em.merge(runner);
        order.setRunner(runner);

        em.persist(order);
        return new OrderDetails(order);
    }

    public OrderDetails editOrder(int orderId, ArrayList<Integer> ids) throws OrderCancelledException, NullPointerException {
        Set<Meal> meals = new HashSet<Meal>();

        TypedQuery<Meal> query3 = em.createQuery(
            "Select m from Meal m  where m.id =?1",
            Meal.class);
        for (int i = 0; i < ids.size(); i++) {
            query3.setParameter(1,ids.get(i));
            Meal meal = query3.getSingleResult();
            if(meal == null)
                throw new NullPointerException("meal doesn't exist");
            meals.add(meal);
        }
        TypedQuery<Orders> query = em.createQuery(
                "Select o from Orders o where o.id =?1",
                Orders.class);
        query.setParameter(1, orderId);
        Orders order = query.getSingleResult();

        if (order == null)
            throw new NullPointerException("the order you have entered the id of doesn't exist");

        for(Meal meal : order.getMealsArray() ){
            meal.removeOrder(order);
            em.merge(meal);
        }

        if (order.getOrderStatus() == OrderStatus.CANCELED)
            throw new OrderCancelledException();


        order.setMealList(meals);
        em.merge(order);
        return new OrderDetails(order);
    }

    public ArrayList<Restaurant> getAllRestaurants() {
        TypedQuery<Restaurant> query = em.createQuery("select r from Restaurant r", Restaurant.class);
        return (ArrayList<Restaurant>) query.getResultList();
    }

    public Orders getOrder(int id) {
        TypedQuery<Orders> query = em.createQuery(
                "Select o from Order o where o.id =?1",
                Orders.class);
        query.setParameter(1, id);
        return query.getSingleResult();
    }

    public Set<Meal> getMenu(int id){
        TypedQuery<Restaurant> query = em.createQuery("SELECT r FROM Restaurant r WHERE r.id =:id",
            Restaurant.class);
        query.setParameter("id", id);
        Restaurant restaurant = query.getSingleResult();
        return restaurant.getMenu();
    }

}
