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

    public void CreateOrder(Order order){
        TypedQuery<Runner> query = em.createQuery("Select r from Runner r where r.status = Status.AVAILABLE", Runner.class);
        query.setMaxResults(1);
        Runner runner = query.getSingleResult();
        runner.SetStatus(Status.BUSY);
        order.SetRunner(runner);
        em.merge(runner);
        em.persist(order);
    }
    public void EditOrder(Order order) throws OrderCanceledException{
        TypedQuery<Order> query = em.createQuery("Select o from Order o where o.orderStatus <> OrderStatus.CANCELED", Order.class);
        Order tempOrder = query.getSingleResult();

        if(tempOrder.GetOrderStatus() == OrderStatus.CANCELED)
            throw new OrderCanceledException();

        em.merge(order);
    }

    public ArrayList<Restaurant> GetAllRestaurants(){

        TypedQuery<Restaurant> query = em.createQuery("select r from Resturant r", Restaurant.class);
        return (ArrayList<Restaurant>) query.getResultList();
    }
}
