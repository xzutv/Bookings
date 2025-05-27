package se.yrgo.services.bookings;

import java.util.*;

import se.yrgo.dataaccess.*;
import se.yrgo.domain.*;
import se.yrgo.exceptions.*;

public interface BookingService {

    public void newBooking(Booking newBooking);

    public void updateBooking(Booking updateBooking) throws BookingNotFoundException;

    public void deleteBooking(Booking oldBooking) throws BookingNotFoundException;

    public List<Booking> getAllBookings();

    public Booking findBookingById(String bookingId) throws BookingNotFoundException; 

}
