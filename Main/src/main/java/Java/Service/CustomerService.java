package Java.Service;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import Java.Util.Exceptions.*;
import java.util.ArrayList;
import Java.Models.*;
import Java.Util.Enums.OrderStatus;
import Java.Util.Enums.Status;
import Java.Util.OrderDetails;

@RolesAllowed("Customer")
@Stateless
public class CustomerService {

    @PersistenceContext
    private EntityManager em;

    public CustomerService() {
    }

    public OrderDetails createOrder(Order order) {
        TypedQuery<Runner> query = em.createQuery(
                "Select r from Runner r where r.status = Status.AVAILABLE",
                Runner.class);
        query.setMaxResults(1);
        Runner runner = query.getSingleResult();
        runner.setStatus(Status.BUSY);
        order.setRunner(runner);
        em.merge(runner);
        order.setRunner(runner);
        em.persist(order);
        return new OrderDetails(order);
    }

    public void editOrder(Order order) throws OrderCanceledException, NullPointerException {
        TypedQuery<Order> query = em.createQuery(
                "Select o from Order o where o.id =?1",
                Order.class);
        query.setParameter(1, order.getId());
        Order tempOrder = query.getSingleResult();

        if (tempOrder == null)
            throw new NullPointerException("the order you have entered the id of doesn't exist");

        if (tempOrder.getOrderStatus() == OrderStatus.CANCELED)
            throw new OrderCanceledException();

        tempOrder.setMealList(order.getMealsList());
        em.merge(tempOrder);
    }

    public ArrayList<Restaurant> getAllRestaurants() {

        TypedQuery<Restaurant> query = em.createQuery("select r from Resturant r", Restaurant.class);
        return (ArrayList<Restaurant>) query.getResultList();
    }

}
