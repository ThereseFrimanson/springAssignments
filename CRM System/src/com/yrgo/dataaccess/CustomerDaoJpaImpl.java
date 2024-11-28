package com.yrgo.dataaccess;

import com.yrgo.domain.Call;
import com.yrgo.domain.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CustomerDaoJpaImpl implements CustomerDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Customer customer) {
        em.persist(customer);
    }

    @Override
    public Customer getById(String customerId) throws RecordNotFoundException {
        Customer customer = em.find(Customer.class, customerId);
        if (customer == null){
            throw new RecordNotFoundException();
        }
        return customer;
    }

    @Override
    public List<Customer> getByName(String name) {
        return em.createQuery("SELECT c FROM Customer c WHERE c.companyName =:name", Customer.class)
                .setParameter("name", name).getResultList();
    }

    @Override
    public void update(Customer changedCustomer) throws RecordNotFoundException {
        Customer customer = em.find(Customer.class, changedCustomer.getCustomerId());
        if (customer == null) {
            throw new RecordNotFoundException();
        }
        em.merge(changedCustomer);
    }


    @Override
    public void delete(Customer oldCustomer) throws RecordNotFoundException {
        Customer managedCustomer = em.find(Customer.class, oldCustomer.getCustomerId());
        if(managedCustomer == null){
            throw new RecordNotFoundException();
        }
        em.remove(managedCustomer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
    }

    @Override
    public Customer getFullCustomerDetail(String customerId) throws RecordNotFoundException {
        try {
            return em.createQuery("SELECT c FROM Customer c LEFT JOIN FETCH c.calls WHERE c.customerId =:id", Customer.class)
                    .setParameter("id", customerId)
                    .getSingleResult();
        }
        catch (Exception e){
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void addCall(Call newCall, String customerId) throws RecordNotFoundException {
        Customer customer = em.find(Customer.class, customerId);
        if(customer == null) {
            throw new RecordNotFoundException();
        }
        customer.getCalls().add(newCall);
        em.merge(customer);
    }
}
