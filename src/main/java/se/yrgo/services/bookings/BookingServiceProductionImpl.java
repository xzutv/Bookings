package se.yrgo.services.bookings;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import se.yrgo.dataaccess.*;
import se.yrgo.domain.*;

@Transactional
@Service("bookingService")
public class BookingServiceProductionImpl implements BookingService {

    @Autowired
    private BookingDao dao;

    @Transactional
    @Override
    public void newBooking(Booking newBooking) {
        dao.create(newBooking);
    }

    @Override
    public void updateBooking(Booking updateBooking) {
        dao.update(updateBooking);
    }

    @Override
    public void deleteBooking(Booking oldBooking) {
        dao.delete(oldBooking);
    }

    @Override
    public Booking findBookingById(String bookingId) throws BookingNotFoundException {
        return dao.findById(bookingId);
    }

    // @Override
    // public Booking fullBooking(String bookingId) throws BookingNotFoundException
    // {
    // // return ;
    // }

    @Override
    public List<Booking> getAllBookings() {
        return dao.allBookings();
    }

}
