package app.Models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import app.Util.Enums.OrderStatus;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany
    @JoinTable(
    name = "ordertable",
    joinColumns = @JoinColumn(name = "Order_id"),
    inverseJoinColumns = @JoinColumn(name = "meal_id"))
    private Set<Meal> orderedMeals = new HashSet<Meal>();

    @Transient
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "fk_runnerId")
    private Runner runner;

    @ManyToOne
    @JoinColumn(name = "fk_restaurantId")
    private Restaurant restaurant;

    private OrderStatus orderStatus;

    public Orders() {
        orderStatus = OrderStatus.PREPARING;
    }

    private double calculateTotalPrice() {
        double sum = 0;
        for (Meal meals: orderedMeals) {
            sum += meals.getPrice();
        }
        return sum;
    }

    public int getId() {
        return id;
    }

    public Set<Meal> getMealsArray() {
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

    public void setMealList(Set<Meal> meals) {
        this.orderedMeals = meals;
        calculateTotalPrice();
    }

    public void setId(int id) {
        this.id = id;
    }

}
