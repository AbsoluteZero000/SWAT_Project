package app.Models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import app.Util.Communication_Classes.RestaurantComm;

@Entity
public class Restaurant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Orders> orders;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Meal> menu = new HashSet<Meal>() {

    };

    public Restaurant() {
    }

    public Restaurant(String name, User owner) {
        this.setName(name);
        this.setOwner(owner);
    }

    public Restaurant(RestaurantComm restComm) {
        this.name = restComm.name;
        this.owner = new User(restComm.owner);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Meal> getMenu() {
        return menu;
    }

    public User getOwner() {
        return owner;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMenu(Set<Meal> menu) {
    this.menu = menu;
    }

    public void addItemsToMenu(Meal meal){
        menu.add(meal);
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void addOrder(Orders order){
        this.orders.add(order);
    }
    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

}
