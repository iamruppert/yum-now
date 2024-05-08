package com.lukasz.yumnow.buisness.dao;

import com.lukasz.yumnow.domain.Customer;

import java.util.Optional;

public interface CustomerDao {

    Customer create(Customer customer);

    Optional<Customer> findCustomerByEmail(String email);
}
