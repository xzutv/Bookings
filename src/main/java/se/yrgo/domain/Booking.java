package se.yrgo.domain;

import javax.persistence.*;
import javax.persistence.Entity;

import org.hibernate.annotations.*;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Activity activity;

    @ManyToOne
    private Customer customer;

    private String startTime;
    private String endTime;
    private Boolean isBooked;
    private String notes;



    public Booking() {};

public Booking(Customer customer, String startTime, String endTime, Boolean isBooked, String notes, Activity activity){
        this.customer = customer;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isBooked = isBooked;
        this.notes = notes;
        this.activity = activity;
    }


    @Override
    public String toString() {
        return "Booking for " + customer.getName() + " at " + startTime + " - " + endTime + " - " + isBooked + " - " + notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookingOwner() {
        return this.customer.getName();
    }

    public void setBookingOwner(String bookingOwner) {
    customer.setName(bookingOwner);
    }

    public void isBooked() {
        this.isBooked = true;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((activity == null) ? 0 : activity.hashCode());
        result = prime * result + ((customer == null) ? 0 : customer.hashCode());
        result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
        result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
        result = prime * result + ((isBooked == null) ? 0 : isBooked.hashCode());
        result = prime * result + ((notes == null) ? 0 : notes.hashCode());
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
        Booking other = (Booking) obj;
        if (id != other.id)
            return false;
        if (activity == null) {
            if (other.activity != null)
                return false;
        } else if (!activity.equals(other.activity))
            return false;
        if (customer == null) {
            if (other.customer != null)
                return false;
        } else if (!customer.equals(other.customer))
            return false;
        if (startTime == null) {
            if (other.startTime != null)
                return false;
        } else if (!startTime.equals(other.startTime))
            return false;
        if (endTime == null) {
            if (other.endTime != null)
                return false;
        } else if (!endTime.equals(other.endTime))
            return false;
        if (isBooked == null) {
            if (other.isBooked != null)
                return false;
        } else if (!isBooked.equals(other.isBooked))
            return false;
        if (notes == null) {
            if (other.notes != null)
                return false;
        } else if (!notes.equals(other.notes))
            return false;
        return true;
    }



    

}
