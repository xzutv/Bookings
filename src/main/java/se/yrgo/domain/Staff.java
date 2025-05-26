package se.yrgo.domain;

import java.util.*;

import javax.persistence.*;

@Entity
public class Staff {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private String name;
    private String email;
    private String phoneNumber;

    @OneToMany(mappedBy = "staff")
    private List<Activity> activities = new ArrayList<>();

    public Staff(){};
    
    public Staff(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }


    public String getEmail () {
        return email;
    }

    public String getPhoneNumber () {
        return phoneNumber;
    }

    public void setName (String name) {
        this.name = name;
    }


    public void SetEmail (String email) {
        this.email = email;    
    }

    public void setPhoneNumber (String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String toString () {
        return name + " with email: " + email + " and phone number: " + phoneNumber;
    }
}
