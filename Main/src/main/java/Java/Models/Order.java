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

    public int GetId(){
        return id;
    }

    public ArrayList<Meal> GetItemsArray(){
        return itemsArray;
    }

    public int GetTotalPrice(){
        return totalPrice;
    }

    public Runner GetRunner(){
        return runner;
    }

    public OrderStatus GetOrderStatus(){
        return orderStatus;
    }

    public void AddItemsToArray(Meal meal){
        itemsArray.add(meal);
    }

    public void SetRunner(Runner runner){
        this.runner = runner;
    }
    public void SetTotalPrice(int price){
        this.totalPrice = price;
    }

    public void setOrderStatus(OrderStatus status){
        this.orderStatus = status;
    }

}
