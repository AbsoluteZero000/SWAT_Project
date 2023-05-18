package app.Models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import app.Util.Communication_Classes.RestaurantComm;


@Entity
public class Restaurant implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToOne(mappedBy = "restaurant")
    private User owner;

    // @OneToMany(mappedBy = "restaurant")
    // private Set<Order> orders;

    // @OneToMany(mappedBy = "restaurant")
    // private Set<Meal> meals;

    public Restaurant(){}
    public Restaurant(String name, User owner){
        this.name = name;
        this.owner = owner;
    }
    public Restaurant(RestaurantComm restComm){
        this.name = restComm.name;
        this.owner = new User(restComm.owner);
    }
    public int GetId() {
        return id;
    }

    public String GetName() {
        return name;
    }

    // public User getOwnerId() {
    //     return owner;
    // }

    // public Set<Meal> getMeals() {
    //     return meals;
    // }

    // public Set<Orders> getOrders() {
    //     return orders;
    // }

    public void setId(int id) {
        this.id = id;
    }

    // public void setOwnerId(User owner) {
    //     this.owner = owner;
    // }

    public void setName(String name) {
        this.name = name;
    }

    // public void setMeals(Set<Meal> meals) {
    //     this.meals = meals;
    // }

    // public void setOrders(Set<Orders> orders) {
    //     this.orders = orders;
    // }

}
