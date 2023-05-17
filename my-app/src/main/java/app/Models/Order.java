package app.Models;

import java.util.ArrayList;
import app.Util.Enums.OrderStatus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private ArrayList<Meal> itemsArray;
    @Transient
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "fk_runnerId")
    private Runner runner;

    @ManyToOne
    @JoinColumn(name = "fk_restaurantId")
    private Restaurant restaurant;

    private OrderStatus orderStatus;

    public Order(){
        orderStatus = OrderStatus.PREPARING;
    }

    private double calculateTotalPrice() {
        double sum = 0;
        for (int i = 0; i < itemsArray.size(); i++) {
            sum += itemsArray.get(i).getPrice();
        }
        return sum;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Meal> getItemsArray() {
        return itemsArray;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Runner getRunner() {
        return runner;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public ArrayList<Meal> getMealsList() {
        return itemsArray;
    }

    public void addItemsToArray(Meal meal) {
        itemsArray.add(meal);
        calculateTotalPrice();
    }

    public void setRunner(Runner runner) {
        this.runner = runner;
    }

    public void setTotalPrice(double price) {
        this.totalPrice = price;
    }

    public void setOrderStatus(OrderStatus status) {
        this.orderStatus = status;
    }

    public void setMealList(ArrayList<Meal> meals) {
        itemsArray = meals;
        calculateTotalPrice();
    }

    public void setId(int id) {
        this.id = id;
    }

}
