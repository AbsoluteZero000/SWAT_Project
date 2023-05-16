package app.Models;

import app.Util.Enums.Status;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.Id;

@Entity
public class Runner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private Status status;

    private int deliveryFees;

    @OneToMany(mappedBy = "runner")
    private Set<Order> orders;

    public Runner(){}

    public Runner(String name, int deliveryFees){
        this.name = name;
        this.status = Status.AVAILABLE;
        this.deliveryFees = deliveryFees;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public int getDeliveryFees() {
        return deliveryFees;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDeliveryFees(int deliveryFees) {
        this.deliveryFees = deliveryFees;
    }

}
