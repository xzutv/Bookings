package se.yrgo.client;

import java.time.*;
import java.time.format.*;
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

            try (Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    System.out.println("\n--- HUVUDMENY ---");
                    System.out.println("1. Sök efter personal");
                    System.out.println("2. Sök efter kund");
                    System.out.println("3. Skapa en bokning");
                    System.out.println("4. Avsluta");
                    System.out.print("Ditt val: ");

                    String input = scanner.nextLine().trim();

                    switch (input) {
                        case "1":
                            System.out.print("Ange namn på personal: ");
                            String staffName = scanner.nextLine().trim();
                            Staff staff = staffService.getAllStaff().stream()
                                    .filter(s -> s.getName().equalsIgnoreCase(staffName))
                                    .findFirst()
                                    .orElse(null);

                            if (staff != null) {
                                System.out.println("Hittade personal: " + staff.getName());
                            } else {
                                System.out.println("Ingen personal hittades med det namnet.");
                            }
                            break;

                        case "2":
                            System.out.print("Ange namn på kund: ");
                            String customerName = scanner.nextLine().trim();
                            Customer customer = customerService.getAllCustomers().stream()
                                    .filter(c -> c.getName().equalsIgnoreCase(customerName))
                                    .findFirst()
                                    .orElse(null);

                            if (customer != null) {
                                System.out.println("Hittade kund: " + customer.getName());
                            } else {
                                System.out.println("Ingen kund hittades med det namnet.");
                            }
                            break;

                        case "3":
                            System.out.println("Skapar en ny bokning...");

                            Customer givenCustomer = null;
                            while (givenCustomer == null) {
                                System.out.println("Ange kundens namn (eller 'x' för att avbryta):");
                                String name = scanner.nextLine().trim();
                                if (name.equalsIgnoreCase("x"))
                                    break;

                                givenCustomer = customerService.getAllCustomers().stream()
                                        .filter(c -> c.getName().equalsIgnoreCase(name))
                                        .findFirst()
                                        .orElse(null);

                                if (givenCustomer == null) {
                                    System.out.println("Kund hittades inte. Försök igen.");
                                }
                            }

                            if (givenCustomer == null)
                                break;

                            List<Activity> activities = activityService.getAllActivities();
                            System.out.println("Tillgängliga aktiviteter:");
                            for (int i = 0; i < activities.size(); i++) {
                                System.out.println((i + 1) + ": " + activities.get(i).getActivity() + " ("
                                        + activities.get(i).getDuration() + " min)");
                            }

                            System.out.print("Ange numret på aktiviteten du vill boka: ");
                            int position;
                            try {
                                position = Integer.parseInt(scanner.nextLine().trim()) - 1;
                            } catch (NumberFormatException e) {
                                System.out.println("Felaktig inmatning.");
                                break;
                            }

                            if (position < 0 || position >= activities.size()) {
                                System.out.println("Ogiltigt val.");
                                break;
                            }

                            Activity chosenActivity = activities.get(position);

                            System.out.print("Ange starttid (t.ex. 2025-12-11 10:00): ");
                            String startInput = scanner.nextLine();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                            LocalDateTime startTime;

                            try {
                                startTime = LocalDateTime.parse(startInput, formatter);
                            } catch (Exception e) {
                                System.out.println("Ogiltigt datumformat.");
                                break;
                            }

                            System.out.print("Anteckningar (valfritt): ");
                            String notes = scanner.nextLine();

                            Booking newBooking = new Booking(givenCustomer, startTime, true, notes, chosenActivity);
                            booking.newBooking(newBooking);

                            System.out.println("Bokning skapad för " + givenCustomer.getName() + " - " +
                                    chosenActivity.getActivity() + " från " + newBooking.getStartTime() +
                                    " till " + newBooking.getEndTime());
                            break;

                        case "4":
                            System.out.println("Avslutar programmet...");
                            return;

                        default:
                            System.out.println("Ogiltigt val, försök igen.");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// try (Scanner scanner = new Scanner(System.in)) {

// System.out.println("Vill du bok");
// String exit = "";

// System.out.println("Ange kundens namn:");
// String customerName = scanner.nextLine().toLowerCase();

// Customer givenCustomer = customerService.getAllCustomers()
// .stream()
// .filter(c -> c.getName().equalsIgnoreCase(customerName))
// .findFirst()
// .orElse(null);

// if (givenCustomer == null) {
// System.out.println("Kund hittades inte.");
// }

// List<Activity> activities = activityService.getAllActivities();
// System.out.println("Tillgängliga aktiviteter:");
// for (int i = 0; i < activities.size(); i++) {
// System.out.println(i + 1 + ": " + activities.get(i));
// }

// System.out.println("Ange numret på aktiviteten du vill boka:");
// int position = Integer.parseInt(scanner.nextLine());

// if (position < 0 || position >= activities.size()) {
// System.out.println("Ogiltigt val.");
// return;
// }

// Activity chosenActivity = activities.get(position);

// System.out.println("Ange starttid (t.ex. 2025-12-11 10:00):");
// String start = scanner.nextLine();

// // System.out.println("Ange sluttid (t.ex. 2025-12-11 11:00):");
// // String end = scanner.nextLine();

// System.out.println("Anteckningar:");
// String notes = scanner.nextLine();

// Booking createdBooking = new Booking(givenCustomer, start, true, notes,
// chosenActivity);
// booking.newBooking(createdBooking);
// System.out.println("Bokning skapad!");
// }

// System.out.println("\nAlla kunder:");
// for (Customer c : customerService.getAllCustomers()) {
// System.out.println(c);
// }

// System.out.println("\nAlla bokningar:");
// for (Booking b : booking.getAllBookings()) {
// System.out.println(b);
// }

// } catch (Exception e) {
// e.printStackTrace();
// }