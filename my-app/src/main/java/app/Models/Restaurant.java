package app.Models;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToMany(mappedBy = "restaurant")
    private Set<Order> orders;

    @OneToMany(mappedBy = "restaurant")
    private Set<Meal> meals;

    public int GetId() {
        return id;
    }

    public String GetName() {
        return name;
    }

    public User getOwnerId() {
        return owner;
    }

    public Set<Meal> getMeals() {
        return meals;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOwnerId(User owner) {
        this.owner = owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMeals(Set<Meal> meals) {
        this.meals = meals;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

}
