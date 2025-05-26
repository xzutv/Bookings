package se.yrgo.dataaccess;

import java.util.*;

import org.springframework.dao.*;

import se.yrgo.domain.*;
import se.yrgo.services.customers.*;
import se.yrgo.dataaccess.CustomerDao;

public interface CustomerDao {

    public void createTables() throws DataAccessException;

    public void create(Customer newCustomer);

    public List<Customer> allCustomers();

    public void update(Customer updateCustomer) throws CustomerNotFoundException;

    public void delete(Customer oldCustomer) throws BookingNotFoundException;

}
