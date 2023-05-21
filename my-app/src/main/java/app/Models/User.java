package app.Models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.security.auth.login.LoginContext;

import app.Util.Communication_Classes.LoginWrapper;
import app.Util.Communication_Classes.UserComm;
import app.Util.Enums.Role;

@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String password;
    private Role role;



    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL)
    private Restaurant restaurant;

    public User(LoginWrapper wrapper) {
        this.name = wrapper.name;
        this.password = wrapper.password;
        this.role = wrapper.role;

    }

    public User(String name) {
        this.name = name;

    }
    public User(){}
    public int getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
