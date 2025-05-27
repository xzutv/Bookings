package se.yrgo.domain;

import java.util.*;

import javax.persistence.*;

@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Staff staff;

    @Enumerated(EnumType.STRING)
    private ActivityType activity;

    private String description;
    private int duration;

    @OneToMany(mappedBy = "activity")
    private List<Booking> bookings = new ArrayList<>();

    public Activity() {

    };

        public Activity(Staff staff, ActivityType activity, String description, int duration) {
            this.staff = staff;
            this.activity = activity;
            this.description = description;
            this.duration = duration;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Staff getStaff() {
            return staff;
        }

        public void setStaff(Staff staff) {
            this.staff = staff;
        }

        public ActivityType getActivity() {
            return activity;
        }

        public void setActivity(ActivityType activity) {
            this.activity = activity;
        }

        public List<Booking> getBookings() {
            return bookings;
        }

        public void setBookings(List<Booking> bookings) {
            this.bookings = bookings;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        };
    
    @Override
    public String toString() {
        return "Activity: " + activity + " Description: " + description + " Duration: " + duration + " minutes";
    }
}
