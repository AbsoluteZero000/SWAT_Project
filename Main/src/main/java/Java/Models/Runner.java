package Java.Models;

import Java.Util.Status;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.Id;


@Entity
public class Runner {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String name;

    private Status status;

    private int deliveryFees;

    @OneToMany(mappedBy = "runner")
    private ArrayList<Order> orders;
    public int GetId(){
        return id;
    }

    public String GetName(){
        return name;
    }

    public Status GetStatus(){
        return status;
    }

    public int GetDeliveryFees(){
        return deliveryFees;
    }

    public void SetName(String name){
        this.name = name;
    }

    public void SetStatus(Status status){
        this.status = status;
    }

    public void SetDeliveryFees(int deliveryFees){
        this.deliveryFees = deliveryFees;
    }

}
