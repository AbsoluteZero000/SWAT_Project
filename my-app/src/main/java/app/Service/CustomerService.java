package app.Service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import app.Util.Exceptions.*;
import java.util.ArrayList;
import java.util.List;

import app.Models.*;
import app.Util.Enums.OrderStatus;
import app.Util.Enums.Status;
import app.Util.OrderDetails;
import app.Util.Communication_Classes.OrderComm;

@Stateless
public class CustomerService {

    @PersistenceContext
    private EntityManager em;

    public CustomerService() {
    }

    public OrderDetails createOrder(OrderComm orderComm) {
        Orders order = new Orders();
        for (int i = 0; i < orderComm.meals.size(); i++) {
            order.addItemsToArray(new Meal(orderComm.meals.get(i)));
        }
        // TypedQuery<Restaurant> query2 = em.createQuery(
        // "Select r from Restaurant r where r.id =?1",
        // Restaurant.class);
        // query2.setParameter(1, resturantId);
        // Restaurant restaurant = query2.getSingleResult();
        // order.setRestaurant(restaurant);

        TypedQuery<Runner> query = em.createQuery(
                "Select r from Runner r where r.status = app.Util.Enums.Status.AVAILABLE",
                Runner.class);
        query.setMaxResults(1);
        List<Runner> runners = query.getResultList();
        if (runners.size() == 0)
            throw new NullPointerException();

        Runner runner = runners.get(0);
        runner.setStatus(Status.BUSY);
        order.setRunner(runner);

        em.merge(runner);
        order.setRunner(runner);

        em.persist(order);
        return new OrderDetails(order);
    }

    public void editOrder(int id, ArrayList<Meal> meals) throws OrderCanceledException, NullPointerException {
        TypedQuery<Orders> query = em.createQuery(
                "Select o from Orders o where o.id =?1",
                Orders.class);
        query.setParameter(1, id);
        Orders tempOrder = query.getSingleResult();

        if (tempOrder == null)
            throw new NullPointerException("the order you have entered the id of doesn't exist");

        if (tempOrder.getOrderStatus() == OrderStatus.CANCELED)
            throw new OrderCanceledException();

        tempOrder.setMealList(meals);
        em.merge(tempOrder);
    }

    public ArrayList<Restaurant> getAllRestaurants() {

        TypedQuery<Restaurant> query = em.createQuery("select r from Resturant r", Restaurant.class);
        return (ArrayList<Restaurant>) query.getResultList();
    }

    public Orders getOrder(int id) {
        TypedQuery<Orders> query = em.createQuery(
                "Select o from Order o where o.id =?1",
                Orders.class);
        query.setParameter(1, id);
        return query.getSingleResult();
    }

}
