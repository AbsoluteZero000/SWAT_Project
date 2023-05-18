package app.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import app.Util.Communication_Classes.UserComm;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String name;
    private String role;

    @OneToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public User(UserComm userComm){
        this.email = userComm.email;
        this.name  = userComm.name;
        this.role = userComm.role;
    }
    public User(String email, String name, String role){
        this.email = email;
        this.name = name;
        this.role = role;
    }
    public int getId() {
        return id;
    }

    public String getEmail(){
        return email;
    }


    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmail(String email){
        this.email = email;
    }


}
