package se.yrgo.dataaccess;

import java.util.*;

import org.springframework.dao.*;

import org.springframework.stereotype.Repository;
import se.yrgo.domain.*;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class BookingDaoJpaImpl implements BookingDao {

    @PersistenceContext
    private EntityManager em;

//     @PostConstruct
//     @Override
//     public void createTables() throws DataAccessException {
//         try {
// //            this.template.update();
//         }
//     }

    @Override
    public void create(Booking newBooking) {
        em.persist(newBooking);
    }

    @Override
    public List<Booking> getAllBookings(String companyId) {
       return em.createQuery("select * from Bookings").getResultList();
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

}
