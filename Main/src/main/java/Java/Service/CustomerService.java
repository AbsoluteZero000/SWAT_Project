package Java.Service;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import Java.Util.Exceptions.*;
import java.util.ArrayList;
import Java.Models.*;
import Java.Util.OrderStatus;
import Java.Util.Status;

@RolesAllowed("Customer")
@Stateless
public class CustomerService {

    @PersistenceContext
    private EntityManager em;

    public CustomerService(){}

    public void createOrder(Order order) throws UnImplementedFunctionException{
        TypedQuery<Runner> query = em.createQuery("Select r from Runner r where r.status = Status.AVAILABLE", Runner.class);
        query.setMaxResults(1);
        Runner runner = query.getSingleResult();
        runner.setStatus(Status.BUSY);
        order.setRunner(runner);
        em.merge(runner);
        em.persist(order);

        throw new UnImplementedFunctionException();
    }

    public void editOrder(Order order) throws OrderCanceledException{
        TypedQuery<Order> query = em.createQuery("Select o from Order o where o.orderStatus <> OrderStatus.CANCELED", Order.class);
        Order tempOrder = query.getSingleResult();

        if(tempOrder.getOrderStatus() == OrderStatus.CANCELED)
            throw new OrderCanceledException();

        em.merge(order);
    }

    public ArrayList<Restaurant> getAllRestaurants(){

        TypedQuery<Restaurant> query = em.createQuery("select r from Resturant r", Restaurant.class);
        return (ArrayList<Restaurant>) query.getResultList();
    }
}
