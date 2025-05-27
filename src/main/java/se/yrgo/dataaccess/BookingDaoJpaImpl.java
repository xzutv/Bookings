package se.yrgo.dataaccess;

import java.util.*;

import org.springframework.dao.*;

import org.springframework.stereotype.Repository;
import se.yrgo.domain.*;
import se.yrgo.exceptions.*;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.*;

@Transactional
@Repository
public class BookingDaoJpaImpl implements BookingDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public void create(Booking newBooking) {
            em.persist(newBooking);
    }

    @Override
    public void update(Booking updateBooking) {
        em.merge(updateBooking);
    }

    @Override
    public void delete(Booking oldBooking) {
        Booking booking = em.find(Booking.class, oldBooking.getId());
        em.remove(booking);
    }

    @Override
    public List<Booking> allBookings() {
        return em.createQuery("select b from Booking b", Booking.class).getResultList();
    }

    @Override
    public Booking findById(String id) throws BookingNotFoundException {
        try {
            return (Booking)em.createQuery("select b from Booking as b where b.id=:id").setParameter("id", id).getSingleResult();

        }catch (javax.persistence.NoResultException e) {
            throw new BookingNotFoundException();
        }
    }

}
