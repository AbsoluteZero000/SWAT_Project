package Java.Models;

import java.util.ArrayList;
import Java.Util.OrderStatus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;




@Entity
public class Order {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private ArrayList<Meal> itemsArray;
    private int totalPrice;

    @ManyToOne
    @JoinColumn(name = "fk_runnerId")
    private Runner runner;

    @ManyToOne
    @JoinColumn(name = "fk_restaurantId")
    private Restaurant restaurant;

    private OrderStatus orderStatus;

    public int getId(){
        return id;
    }

    public ArrayList<Meal> getItemsArray(){
        return itemsArray;
    }

    public int getTotalPrice(){
        return totalPrice;
    }

    public Runner getRunner(){
        return runner;
    }

    public OrderStatus getOrderStatus(){
        return orderStatus;
    }

    public void addItemsToArray(Meal meal){
        itemsArray.add(meal);
    }

    public void setRunner(Runner runner){
        this.runner = runner;
    }
    public void setTotalPrice(int price){
        this.totalPrice = price;
    }

    public void setOrderStatus(OrderStatus status){
        this.orderStatus = status;
    }

}
