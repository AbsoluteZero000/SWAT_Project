package app.Service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import app.Models.*;
import app.Util.OrderDetails;
import app.Util.Communication_Classes.RunnerComm;
import app.Util.Enums.OrderStatus;
import app.Util.Enums.Status;

@Stateless
public class RunnerService {
    @PersistenceContext
    private EntityManager em;

    public OrderDetails markOrder(int id) {
        TypedQuery<Orders> query = em.createQuery("Select o from Orders o where o.id =?1", Orders.class);
        query.setParameter(1, id);
        Orders order = query.getSingleResult();

        if (order == null)
            throw new NullPointerException();

        order.setOrderStatus(OrderStatus.DELIVERED);
        Runner runner = order.getRunner();
        runner.setStatus(Status.AVAILABLE);
        em.merge(order);
        em.merge(runner);

        return new OrderDetails(order);
    }

    public Runner addRunner(RunnerComm runnerComm) {
        Runner runner = new Runner(runnerComm);
        em.persist(runner);
        return runner;
    }

    public String test() {
        return "Runner Service";
    }

    public int getNumberOfTrips(int id) {
    TypedQuery<Orders> query = em.createQuery("SELECT o FROM Orders o WHERE o.runner.id =?1 and o.orderStatus = app.Util.Enums.OrderStatus.DELIVERED", Orders.class);
    query.setParameter(1, id);
    List<Orders> list = query.getResultList();
    return list.size();
    }
}
