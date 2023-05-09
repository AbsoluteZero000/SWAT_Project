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


    public int GetId(){
        return id;
    }

    public String GetName(){
        return name;
    }

    public String GetRole(){
        return role;
    }


    public void SetName(String name){
        this.name = name;
    }

    public void SetRole(String role){
        this.role = role;
    }
}
