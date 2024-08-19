package com.ust.customer.service;

import com.ust.customer.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Optional<Customer> findCustomerById(long id);
    Customer addNewCustomer(Customer customer);
    Customer updateCustomer(Customer customer,long id);
    void deleteCustomer(Customer customer);
}
