package se.yrgo.dataaccess;

import java.util.List;

import org.springframework.dao.DataAccessException;

import se.yrgo.domain.Customer;
import se.yrgo.exceptions.*;

public interface CustomerDao {


    public void create(Customer newCustomer);

    public List<Customer> getAllCustomers();

    public void update(Customer updateCustomer) throws CustomerNotFoundException;

    public void delete(Customer oldCustomer) throws CustomerNotFoundException;

    Customer getById(int id) throws CustomerNotFoundException;

    List<Customer> getByName(String name);

    Customer getFullCustomerDetail(int customerId) throws CustomerNotFoundException; 




   
}
