package se.yrgo.dataaccess;

import java.util.*;

import org.springframework.dao.*;

import org.springframework.stereotype.Repository;
import se.yrgo.domain.*;
import se.yrgo.exceptions.*;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class BookingDaoJpaImpl implements BookingDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Booking newBooking) {
        em.persist(newBooking);
    }

    @Override
    public void update(Booking updateBooking) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Booking oldBooking) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<Booking> allBookings() {
        return em.createQuery("SELECT b from Booking b", Booking.class).getResultList();
    }

    @Override
    public Booking findById(String id) throws BookingNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

}
