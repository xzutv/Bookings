package se.yrgo.dataaccess;

import java.util.*;

import org.springframework.dao.*;
import org.springframework.jdbc.core.*;

import se.yrgo.domain.*;

public class BookingDaoJdbcImpl implements BookingDao {

    private JdbcTemplate template;

    public BookingDaoJdbcImpl(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public void createTables() throws DataAccessException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createTables'");
    }

    @Override
    public void create(Booking newBooking) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public List<Booking> getAllBookings(String companyId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllBookings'");
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
