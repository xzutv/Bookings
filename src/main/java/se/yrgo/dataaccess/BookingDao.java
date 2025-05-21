package se.yrgo.dataaccess;

import java.util.*;

import org.springframework.dao.*;

import se.yrgo.domain.*;

public interface BookingDao {
    
    public void createTables() throws DataAccessException;
    public void create(Booking newBooking);
    public List<Booking> getAllBookings(String companyId);
    public void update(Booking updateBooking);
    public void delete(Booking oldBooking);

}
