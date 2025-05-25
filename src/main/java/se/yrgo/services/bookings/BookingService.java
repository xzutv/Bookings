package se.yrgo.services.bookings;

import java.util.*;

import se.yrgo.dataaccess.*;
import se.yrgo.domain.*;

public interface BookingService {

    public void newBooking(Booking newBooking);

    public void updateBooking(Booking updateBooking);

    public void deleteBooking(Booking oldBooking);

    public List<Booking> getAllBookings(String companyId);

    public Booking findBookingById(String bookingId) throws BookingNotFoundException; 

    public Booking fullBooking(String bookingId) throws BookingNotFoundException;

}
