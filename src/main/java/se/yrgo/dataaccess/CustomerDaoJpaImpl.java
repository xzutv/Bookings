package se.yrgo.dataaccess;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import se.yrgo.domain.Customer;
import se.yrgo.services.customers.CustomerNotFoundException;

@Repository
public class CustomerDaoJpaImpl implements CustomerDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createTables() {}


    @Override
    public void create(Customer newCustomer) {
        em.persist(newCustomer);
    }

    @Override
    public Customer getById(int id) throws CustomerNotFoundException {
        Customer customer = em.find(Customer.class, id);
        if (customer == null) {
            throw new CustomerNotFoundException("No customer found with ID: " + id);
        }
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
    }

    @Override
    public List<Customer> getByName(String name) {
        return em.createQuery(
            "SELECT c FROM Customer c WHERE c.name = :name", Customer.class)
            .setParameter("name", name)
            .getResultList();
    }

    @Override
    public Customer getFullCustomerDetail(int customerId) throws CustomerNotFoundException {
        Customer customer = em.find(Customer.class, customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("No customer found with ID: " + customerId);
        }

        
        customer.getClass();
        return customer;
    }


    @Override
    public void update(Customer updateCustomer) throws CustomerNotFoundException {
        getFullCustomerDetail(updateCustomer.getCustomerId());
        em.merge(updateCustomer);
    }

    @Override
    public void delete(Customer oldCustomer) throws CustomerNotFoundException {
        Customer customer = getById(oldCustomer.getCustomerId());
        em.remove(customer);
    }
}