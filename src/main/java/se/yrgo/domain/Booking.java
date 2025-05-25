package se.yrgo.domain;

import javax.persistence.*;
import java.util.Calendar;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String customerName;
    private String startTime;
    private String endTime;
    private Boolean booked;
    private String notes;

    public Booking() {};

    public Booking(String customerName, String startTime, String endTime, Boolean booked, String notes) {
        this.customerName = customerName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.booked = booked;
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Booking for " + this.customerName + " at " + this.startTime.toString() + " - " + this.endTime.toString() + " - " + this.booked + " - " + this.notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookingOwner() {
        return this.customerName;
    }

    public void setBookingOwner(String bookingOwner) {
        this.customerName = bookingOwner;
    }

    public void isBooked() {
        this.booked = true;
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
        result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
        result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
        result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
        result = prime * result + ((booked == null) ? 0 : booked.hashCode());
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
        if (customerName == null) {
            if (other.customerName != null)
                return false;
        } else if (!customerName.equals(other.customerName))
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
        if (booked == null) {
            if (other.booked != null)
                return false;
        } else if (!booked.equals(other.booked))
            return false;
        if (notes == null) {
            if (other.notes != null)
                return false;
        } else if (!notes.equals(other.notes))
            return false;
        return true;
    }


    

}
