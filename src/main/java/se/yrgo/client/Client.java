package se.yrgo.client;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import se.yrgo.domain.*;
import se.yrgo.init.*;
import se.yrgo.services.activities.*;
import se.yrgo.services.bookings.BookingService;
import se.yrgo.services.customers.*;
import se.yrgo.services.staff.*;

public class Client {
    public static void main(String[] args) {

        try (ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml")) {
            DataInitializer initializer = container.getBean(DataInitializer.class);
            initializer.run();
            BookingService booking = container.getBean(BookingService.class);
            CustomerManagementService customerService = container.getBean(CustomerManagementService.class);
            ActivityService activityService = container.getBean(ActivityService.class);
            StaffService staffService = container.getBean(StaffService.class);

            // Skapa och lagra en kund
            customerService.newCustomer(new Customer("Mario", "mario@gmail.com", "0736559015"));

            // Skapa och lagra en personal
            Staff staff = new Staff("Haimen", "haimen@gmail.com", "0738888888");
            staffService.newStaff(staff);

            // Skapa och lagra aktiviteter
            Activity spa = new Activity(staff, ActivityType.SPA, "Relaxing spa experience", 60);
            Activity crossfit = new Activity(staff, ActivityType.CROSSFIT, "Intense workout", 45);
            activityService.newActivity(spa);
            activityService.newActivity(crossfit);

            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("Ange kundens namn:");
                String customerName = scanner.nextLine().toLowerCase();

                Customer givenCustomer = customerService.getAllCustomers()
                        .stream()
                        .filter(c -> c.getName().equalsIgnoreCase(customerName))
                        .findFirst()
                        .orElse(null);

                if (givenCustomer == null) {
                    System.out.println("Kund hittades inte.");
                    return;
                }

                List<Activity> activities = activityService.getAllActivities();
                System.out.println("Tillgängliga aktiviteter:");
                for (int i = 0; i < activities.size(); i++) {
                    System.out.println(i + 1 + ": " + activities.get(i));
                }

                System.out.println("Ange numret på aktiviteten du vill boka:");
                int position = Integer.parseInt(scanner.nextLine());

                if (position < 0 || position >= activities.size()) {
                    System.out.println("Ogiltigt val.");
                    return;
                }

                Activity chosenActivity = activities.get(position);

                System.out.println("Ange starttid (t.ex. 2025-12-11 10:00):");
                String start = scanner.nextLine();

                System.out.println("Ange sluttid (t.ex. 2025-12-11 11:00):");
                String end = scanner.nextLine();

                System.out.println("Anteckningar:");
                String notes = scanner.nextLine();

                Booking createdBooking = new Booking(givenCustomer, start, end, true, notes, chosenActivity);
                booking.newBooking(createdBooking);
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
