package Java.Models;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Restaurant{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String name;
    private int ownerId;
    @OneToMany(mappedBy = "restaurant")
    private ArrayList<Order> orders;

    @OneToMany(mappedBy = "restaurant")
    private ArrayList<Meal> meals;

    public int GetId(){
        return id;
    }
    public String GetName(){
        return name;
    }

    public int getOwnerId(){
        return ownerId;
    }

    public ArrayList<Meal> getMeals(){
        return meals;
    }

    public ArrayList<Order> getOrders(){
        return orders;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setOwnerId(int id){
        this.ownerId = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setMeals(ArrayList<Meal> meals){
        this.meals = meals;
    }

    public void setOrders(ArrayList<Order> orders){
        this.orders = orders;
    }

}
