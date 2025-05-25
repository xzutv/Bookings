package se.yrgo.domain;

import java.util.*;

import javax.persistence.*;

@Entity
public class Customer {

    @Id
    private int customerId;
    private String name;
    private String email;
    private int telephone;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
     @JoinColumn(name = "id") 
    private Company company;

    public Customer(int customerId, String name, String email, int telephone) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.telephone = telephone;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getTelephone() {
        return telephone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setCustomerId( int customerId) {
        this.customerId = customerId;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return customerId + "Name: " + name + "Telephone" + telephone + "email" + email;
    }

    public Customer() {}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + customerId;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + telephone;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Customer other = (Customer) obj;
        if (customerId != other.customerId)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (telephone != other.telephone)
            return false;
        if (id != other.id)
            return false;
        return true;
    }

    

}


