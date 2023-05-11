package Java.Service;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import Java.Util.*;

import Java.Models.*;
import Java.Util.OrderStatus;

@RolesAllowed("Runner")
@Stateless
public class RunnerService{
    @PersistenceContext
    private EntityManager em;


    public void markOrder(int id){
        TypedQuery<Order> query = em.createQuery("Select o from Order o where o.id =? 1", Order.class);
        query.setParameter(1, id);
        Order order = query.getSingleResult();
        order.setOrderStatus(OrderStatus.DELIVERED);
        Runner runner = order.getRunner();
        runner.setStatus(Status.AVAILABLE);
        em.merge(order);
    }

    public int getNumberOfTrips(){
        TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o JOIN o.runner r WHERE o.id = r.id and o.OrderStatus = OrderStatus.DELIVERED",Order.class);
        List<Order> list = query.getResultList();
        return list.size();
    }
}
