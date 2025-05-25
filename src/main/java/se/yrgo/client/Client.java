package se.yrgo.client;

import org.springframework.context.support.*;

import se.yrgo.dataaccess.*;
import se.yrgo.domain.*;
import se.yrgo.services.bookings.*;
import java.util.List;

public class Client {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");
        
        try {
            BookingService booking = container.getBean(BookingService.class);

            booking.newBooking(new Booking("Mario", "2025-12-11", "2025-12-11", true, "This is a gym booking"));

            List<Booking> allBookings = booking.getAllBookings();

            for(Booking b : allBookings) {
                System.out.println(b);
            }
        } finally {
            container.close();
        }
    }
}
