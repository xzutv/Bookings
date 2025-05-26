package se.yrgo.client;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import se.yrgo.domain.Booking;
import se.yrgo.services.bookings.BookingService;

public class Client {
    public static void main(String[] args) {

        
        
        try (ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");)
         {
            BookingService booking = container.getBean(BookingService.class);

            booking.newBooking(new Booking("Mario", "2025-12-11", "2025-12-11", true, "This is a gym booking"));

            List<Booking> allBookings = booking.getAllBookings();

            for(Booking b : allBookings) {
                System.out.println(b);
            }
        } 
    }
}
