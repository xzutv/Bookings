package se.yrgo.services.customers;

import java.util.List;

import se.yrgo.domain.Customer;

public interface CustomerManagementService {

    public void newCustomer(Customer newCustomer);

    public void updateCustomer(Customer changedCustomer) throws CustomerNotFoundException;

    public void deleteCustomer(Customer oldCustomer);

    public Customer findCustomerById(int customerId) throws CustomerNotFoundException;

    public List<Customer> findCustomersByName(String name);

    public List<Customer> getAllCustomers();

    public Customer getFullCustomerDetail(int customerId) throws CustomerNotFoundException;

}
