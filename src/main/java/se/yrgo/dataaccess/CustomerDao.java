package se.yrgo.dataaccess;

import java.util.*;

import org.springframework.dao.*;

import se.yrgo.domain.*;

public interface CustomerDao {

    public void createTables() throws DataAccessException;

    public void create(Customer newCustomer);

    public List<Customer> getAllCustomers(String customerId);

    public void update(Customer updateCustomer);

    public void delete(Customer oldCustomer) throws BookingNotFoundException;
}
