package app.Service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import app.Models.*;
import app.Util.Communication_Classes.RunnerComm;
import app.Util.Enums.OrderStatus;
import app.Util.Enums.Status;


@Stateless
public class RunnerService {
    @PersistenceContext
    private EntityManager em;



    public void markOrder(int id) {
        TypedQuery<Orders> query = em.createQuery("Select o from Order o where o.id =? 1", Orders.class);
        query.setParameter(1, id);
        Orders order = query.getSingleResult();

        if (order == null)
            throw new NullPointerException();

        order.setOrderStatus(OrderStatus.DELIVERED);
        Runner runner = order.getRunner();
        runner.setStatus(Status.AVAILABLE);
        em.merge(order);
        em.merge(runner);
    }

    public Runner addRunner(RunnerComm runnerComm){
        Runner runner = new Runner(runnerComm);
        em.persist(runner);
        return runner;
    }

    public String test(){
        return "Runner Service";
    }
    // public int getNumberOfTrips(int id) {
    //     TypedQuery<Order> query = em.createQuery(
    //             "SELECT o FROM Order o JOIN o.runner r WHERE o.id = r.id and o.runner_id =? 1 and o.OrderStatus = OrderStatus.DELIVERED",
    //             Order.class);
    //     query.setParameter(1, id);
    //     List<Order> list = query.getResultList();
    //     return list.size();
    // }
}
