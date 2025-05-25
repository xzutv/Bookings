package se.yrgo.dataaccess;

import java.util.*;

import org.springframework.dao.*;

import se.yrgo.domain.*;

public interface BookingDao {
    
    public void create(Booking newBooking);
    public List<Booking> allBookings();
    public void update(Booking updateBooking);
    public void delete(Booking oldBooking);
    public Booking findById(String id) throws BookingNotFoundException;

}
