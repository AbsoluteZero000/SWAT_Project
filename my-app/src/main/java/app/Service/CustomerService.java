package app.Service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import app.Util.Exceptions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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

        TypedQuery<Restaurant> query2 = em.createQuery(
        "Select r from Restaurant r where r.id =?1",
        Restaurant.class);
        query2.setParameter(1, orderComm.restaurantId);
        Restaurant restaurant = query2.getSingleResult();

        order.setRestaurant(restaurant);
        restaurant.addOrder(order);

        TypedQuery<Runner> query = em.createQuery(
                "Select r from Runner r where r.status = app.Util.Enums.Status.AVAILABLE",
                Runner.class);
        query.setMaxResults(1);
        List<Runner> runners = query.getResultList();
        if (runners.size() == 0)
            return null;

        Runner runner = runners.get(0);
        runner.setStatus(Status.BUSY);
        order.setRunner(runner);

        em.merge(runner);
        order.setRunner(runner);

        em.persist(order);
        return new OrderDetails(order);
    }

    public OrderDetails editOrder(Orders order) throws OrderCancelledException, NullPointerException {
        TypedQuery<Orders> query = em.createQuery(
                "Select o from Orders o where o.id =?1",
                Orders.class);
        query.setParameter(1, order.getId());
        Orders tempOrder = query.getSingleResult();

        if (tempOrder == null)
            throw new NullPointerException("the order you have entered the id of doesn't exist");

        if (tempOrder.getOrderStatus() == OrderStatus.CANCELED)
            throw new OrderCancelledException();

        tempOrder.setMealList(order.getMealsArray());
        em.merge(tempOrder);
        return new OrderDetails(tempOrder);
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
