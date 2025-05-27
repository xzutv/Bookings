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
                    System.out.println("4. Ändra en bokning");
                    System.out.println("5. Ta bort en bokning");
                    System.out.println("6. Avsluta");
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
                            List<Booking> allBookings = booking.getAllBookings();
                            if (allBookings.isEmpty()) {
                                System.out.println("Inga bokningar att visa.");
                                break;
                            }

                            System.out.println("Lista över bokningar:");
                            for (int i = 0; i < allBookings.size(); i++) {
                                Booking b = allBookings.get(i);
                                System.out.println((i + 1) + ": " + b.getCustomer().getName() +
                                        " - " + b.getActivity() + " (" + b.getStartTime() + ")");
                            }

                            System.out.print("Välj en bokning att ändra (ange nummer): ");
                            int editIndex = Integer.parseInt(scanner.nextLine()) - 1;
                            if (editIndex < 0 || editIndex >= allBookings.size()) {
                                System.out.println("Ogiltigt val.");
                                break;
                            }

                            Booking bookingToEdit = allBookings.get(editIndex);

                            System.out.println("Nuvarande starttid: " + bookingToEdit.getStartTime());
                            System.out.print("Ange ny starttid (yyyy-MM-dd HH:mm) eller tryck Enter för att behålla: ");
                            String newStart = scanner.nextLine().trim();
                            if (!newStart.isEmpty()) {
                                try {
                                    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                                    LocalDateTime newStartTime = LocalDateTime.parse(newStart, fmt);
                                    bookingToEdit.setStartTime(newStartTime);
                                    // räkna om sluttid
                                    bookingToEdit.setEndTime(
                                            newStartTime.plusMinutes(bookingToEdit.getActivity().getDuration()));
                                } catch (Exception e) {
                                    System.out.println("Ogiltigt datumformat.");
                                    break;
                                }
                            }

                            System.out.print("Ange nya anteckningar eller tryck Enter för att behålla: ");
                            String newNotes = scanner.nextLine().trim();
                            if (!newNotes.isEmpty()) {
                                bookingToEdit.setNotes(newNotes);
                            }

                            booking.updateBooking(bookingToEdit);
                            System.out.println("Bokningen har uppdaterats.");
                            break;
                        case "5":
                            List<Booking> bookings = booking.getAllBookings();
                            if (bookings.isEmpty()) {
                                System.out.println("Inga bokningar att ta bort.");
                                break;
                            }

                            System.out.println("Lista över bokningar:");
                            for (int i = 0; i < bookings.size(); i++) {
                                Booking b = bookings.get(i);
                                System.out.println((i + 1) + ": " + b.getCustomer().getName() +
                                        " - " + b.getActivity() + " (" + b.getStartTime() + ")");
                            }

                            System.out.print("Välj bokning att ta bort (ange nummer): ");
                            int removeIndex = Integer.parseInt(scanner.nextLine()) - 1;
                            if (removeIndex < 0 || removeIndex >= bookings.size()) {
                                System.out.println("Ogiltigt val.");
                                break;
                            }

                            Booking toRemove = bookings.get(removeIndex);
                            System.out.print("Är du säker på att du vill ta bort bokningen? (ja/nej): ");
                            String confirm = scanner.nextLine().trim().toLowerCase();
                            if (confirm.equals("ja")) {
                                booking.deleteBooking(toRemove);
                                System.out.println("Bokningen är borttagen.");
                            } else {
                                System.out.println("Avbröt borttagning.");
                            }
                            break;
                        case "6":
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

