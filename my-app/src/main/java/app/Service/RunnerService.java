package app.Service;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

import app.Models.*;
import app.Util.Enums.OrderStatus;
import app.Util.Enums.Status;

@RolesAllowed("Runner")
@Stateless
public class RunnerService {
    @PersistenceContext
    private EntityManager em;

    public void markOrder(int id) {
        TypedQuery<Order> query = em.createQuery("Select o from Order o where o.id =? 1", Order.class);
        query.setParameter(1, id);
        Order order = query.getSingleResult();

        if (order == null)
            throw new NullPointerException();

        order.setOrderStatus(OrderStatus.DELIVERED);
        Runner runner = order.getRunner();
        runner.setStatus(Status.AVAILABLE);
        em.merge(order);
        em.merge(runner);
    }

    public int getNumberOfTrips(int id) {
        TypedQuery<Order> query = em.createQuery(
                "SELECT o FROM Order o JOIN o.runner r WHERE o.id = r.id and o.runner_id =? 1 and o.OrderStatus = OrderStatus.DELIVERED",
                Order.class);
        query.setParameter(1, id);
        List<Order> list = query.getResultList();
        return list.size();
    }
}
