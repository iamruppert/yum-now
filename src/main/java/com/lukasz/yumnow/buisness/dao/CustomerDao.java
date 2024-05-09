package com.lukasz.yumnow.buisness.dao;

import com.lukasz.yumnow.domain.Customer;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface CustomerDao {

    Customer create(Customer customer);

    Optional<Customer> findByEmail(String email);
}
