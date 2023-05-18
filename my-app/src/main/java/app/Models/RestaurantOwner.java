package app.Models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import app.Util.Communication_Classes.UserComm;

public class RestaurantOwner extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(mappedBy = "owner")
    private Restaurant restaurant;

    public RestaurantOwner(UserComm userComm) {
        super(userComm.name);
    }

    public void setRestaurant(Restaurant restaurant){
        this.restaurant = restaurant;
    }

    public Restaurant getRestaurant(){
        return restaurant;
    }
}
