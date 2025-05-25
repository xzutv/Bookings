package se.yrgo.services.customers;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import se.yrgo.dataaccess.*;
import se.yrgo.domain.*;
@Service("customerService")
public class CustomerManagementServiceProductionImpl implements CustomerManagementService {

    @Autowired
    private final CustomerDao customerDao;

    public CustomerManagementServiceProductionImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public void newCustomer(Customer newCustomer) {
        customerDao.create(newCustomer);
    }

    @Override
    public void updateCustomer(Customer changedCustomer) {
        try {
            customerDao.update(changedCustomer);
        } catch (CustomerNotFoundException e) {
            System.out.println("Could not update customer details for " + changedCustomer.getCustomerId());
        }
    }

    @Override
    public void deleteCustomer(Customer oldCustomer) {
        try {
            customerDao.delete(oldCustomer);
        } catch (CustomerNotFoundException e) {
            System.out.println("Could not delete customer " + oldCustomer);
        }
    }

    @Override
    public Customer findCustomerById(String customerId) throws CustomerNotFoundException {

            return customerDao.getById(customerId);
        
        
    }

    @Override
    public List<Customer> findCustomersByName(String name) {
        return customerDao.getByName(name);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    @Override
    public Customer getFullCustomerDetail(String customerId) throws CustomerNotFoundException {
            return customerDao.getFullCustomerDetail(customerId);
        
    }


}
