package se.yrgo.domain;

import java.time.*;

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

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Boolean isBooked;
    private String notes;

    public Booking() {
    };

    public Booking(Customer customer, LocalDateTime startTime, Boolean isBooked, String notes, Activity activity) {
        this.customer = customer;
        this.startTime = startTime;
        this.endTime = startTime.plusMinutes(activity.getDuration());
        this.isBooked = isBooked;
        this.notes = notes;
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "Booking for " + customer.getName() + " Activity: " + activity.getActivity() + " at " + startTime
                + " Duration: " + activity.getDuration() + " minutes." + " Notes: " + notes;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Boolean getIsBooked() {
        return isBooked;
    }

    public void setIsBooked(Boolean isBooked) {
        this.isBooked = isBooked;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getEndTime() {
        return endTime;
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    // public String getEndTime() {
    // return endTime;
    // }

    // public void setEndTime(String endTime) {
    // this.endTime = endTime;
    // }

}
