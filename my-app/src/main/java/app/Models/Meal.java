package app.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import app.Util.Communication_Classes.MealComm;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Meal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;

    @ManyToOne
    @JoinColumn(name = "restaurantId")
    private Restaurant restaurant;

    @ManyToMany(mappedBy = "orderedMeals")
    Set<Orders> order;

    public Meal(){}
    public Meal(MealComm mealComm) {
        this.name = mealComm.name;
        this.price = mealComm.price;
    }

    public Meal(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addOrderTo(Orders order){
        this.order.add(order);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRestaurant(Restaurant res){
        this.restaurant = res;
    }

    public double getPrice() {
        return this.price;
    }

    public void removeOrder(Orders order){
        this.order.remove(order);
    }





}
