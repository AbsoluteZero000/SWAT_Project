package app.Models;

import java.io.Serializable;
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
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private ArrayList<Meal> orderedMeals = new ArrayList<Meal>();

    @Transient
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "fk_runnerId")
    private Runner runner;

    @ManyToOne
    @JoinColumn(name = "customer_Id")
    private User customer;

    public ArrayList<Meal> getOrderedMeals() {
        return orderedMeals;
    }

    public void setOrderedMeals(ArrayList<Meal> orderedMeals) {
        this.orderedMeals = orderedMeals;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    @ManyToOne
    @JoinColumn(name = "fk_restaurantId")
    private Restaurant restaurant;

    private OrderStatus orderStatus;

    public Orders() {
        orderStatus = OrderStatus.PREPARING;
    }

    private double calculateTotalPrice() {
        double sum = 0;
        for (int i = 0; i < orderedMeals.size(); i++) {
            sum += orderedMeals.get(i).getPrice();
        }
        return sum;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Meal> getMealsArray() {
        return orderedMeals;
    }

    public double getTotalPrice() {
        calculateTotalPrice();
        return totalPrice;
    }

    public int getRestaurantId() {
        return restaurant.getId();
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Restaurant getRestaurant() {
        return this.restaurant;
    }

    public Runner getRunner() {
        return runner;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void addItemsToArray(Meal meal) {
        orderedMeals.add(meal);
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

        for (int i = 0; i < meals.size(); i++)
            orderedMeals.add(meals.get(i));
        calculateTotalPrice();
    }

    public void setId(int id) {
        this.id = id;
    }

}
