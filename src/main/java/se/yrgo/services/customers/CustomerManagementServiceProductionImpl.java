package se.yrgo.services.customers;

import java.util.List;

import javax.transaction.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.yrgo.dataaccess.CustomerDao;
import se.yrgo.domain.Customer;
import se.yrgo.exceptions.*;
@Service("customerService")
@Transactional
public class CustomerManagementServiceProductionImpl implements CustomerManagementService {

    @Autowired
    private final CustomerDao customerDao;

    public CustomerManagementServiceProductionImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Transactional
    @Override
    public void newCustomer(Customer newCustomer) {
        customerDao.create(newCustomer);
    }

    @Override
    public void updateCustomer(Customer changedCustomer) {
        try {
            customerDao.update(changedCustomer);
        } catch (CustomerNotFoundException e) {
            System.out.println("Could not update customer details for " + changedCustomer.getName());
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
    public Customer findCustomerById(int customerId) throws CustomerNotFoundException {

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
    public Customer getFullCustomerDetail(int customerId) throws CustomerNotFoundException {
            return customerDao.getFullCustomerDetail(customerId);
        
    }


}
