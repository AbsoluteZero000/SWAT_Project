package app.Models;

import app.Util.Communication_Classes.RunnerComm;
import app.Util.Enums.Status;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.Id;

@Entity
public class Runner extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private Status status;

    private int deliveryFees;

    @OneToMany(mappedBy = "runner", fetch = FetchType.EAGER)
    private Set<Orders> orders;

    public Runner() {
        super("");
        status = Status.AVAILABLE;
        deliveryFees = 0;

    }

    public Runner(RunnerComm runnerComm) {
        super(runnerComm.name);
        deliveryFees = runnerComm.deliveryFees;
        this.status = Status.AVAILABLE;
    }

    public Runner(String name, int deliveryFees) {
        super(name);
        this.status = Status.AVAILABLE;
        this.deliveryFees = deliveryFees;
    }

    public int getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public int getDeliveryFees() {
        return deliveryFees;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDeliveryFees(int deliveryFees) {
        this.deliveryFees = deliveryFees;
    }

}
