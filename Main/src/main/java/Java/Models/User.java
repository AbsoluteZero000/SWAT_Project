package Java.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String name;

    private String role;


    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getRole(){
        return role;
    }


    public void setName(String name){
        this.name = name;
    }

    public void setRole(String role){
        this.role = role;
    }
}
