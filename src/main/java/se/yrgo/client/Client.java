package se.yrgo.client;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import se.yrgo.domain.*;
import se.yrgo.services.activities.*;
import se.yrgo.services.bookings.BookingService;
import se.yrgo.services.customers.*;
import se.yrgo.services.staff.*;

public class Client {
    public static void main(String[] args) {

        try (ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml")) {
            BookingService booking = container.getBean(BookingService.class);
            CustomerManagementService customerService = container.getBean(CustomerManagementService.class);
            ActivityService activityService = container.getBean(ActivityService.class);
            StaffService staffService = container.getBean(StaffService.class);

            // Skapa och lagra en kund
            customerService.newCustomer(new Customer("Mario", "mario@gmail.com", "0736559015"));

            // Skapa och lagra en personal
            Staff personal = new Staff("Haimen", "haimen@gmail.com", "0738888888");
            staffService.newStaff(personal);

            // Skapa och lagra aktiviteter
            Activity aktivitet1 = new Activity(personal, ActivityType.SPA, "Relaxing spa experience", 60);
            Activity aktivitet2 = new Activity(personal, ActivityType.CROSSFIT, "Intense workout", 45);
            activityService.newActivity(aktivitet1);
            activityService.newActivity(aktivitet2);

            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("Ange kundens namn:");
                String kundnamn = scanner.nextLine();

                Customer kund = customerService.getAllCustomers()
                        .stream()
                        .filter(c -> c.getName().equalsIgnoreCase(kundnamn))
                        .findFirst()
                        .orElse(null);

                if (kund == null) {
                    System.out.println("Kund hittades inte.");
                    return;
                }

                List<Activity> aktiviteter = activityService.getAllActivities();
                System.out.println("Tillgängliga aktiviteter:");
                for (int i = 0; i < aktiviteter.size(); i++) {
                    System.out.println(i + ": " + aktiviteter.get(i));
                }

                System.out.println("Ange numret på aktiviteten du vill boka:");
                int aktivitetIndex = Integer.parseInt(scanner.nextLine());

                if (aktivitetIndex < 0 || aktivitetIndex >= aktiviteter.size()) {
                    System.out.println("Ogiltigt val.");
                    return;
                }

                Activity valdAktivitet = aktiviteter.get(aktivitetIndex);

                System.out.println("Ange starttid (t.ex. 2025-12-11 10:00):");
                String start = scanner.nextLine();

                System.out.println("Ange sluttid (t.ex. 2025-12-11 11:00):");
                String end = scanner.nextLine();

                System.out.println("Anteckningar:");
                String notes = scanner.nextLine();

                Booking nyBokning = new Booking(kund, start, end, true, notes, valdAktivitet);
                booking.newBooking(nyBokning);
                System.out.println("Bokning skapad!");
            }

            System.out.println("\nAlla kunder:");
            for (Customer c : customerService.getAllCustomers()) {
                System.out.println(c);
            }

            System.out.println("\nAlla bokningar:");
            for (Booking b : booking.getAllBookings()) {
                System.out.println(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
