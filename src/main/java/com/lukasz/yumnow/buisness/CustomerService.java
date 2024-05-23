package com.lukasz.yumnow.buisness;

import com.lukasz.yumnow.buisness.dao.CustomerDao;
import com.lukasz.yumnow.domain.Customer;
import com.lukasz.yumnow.domain.exception.AlreadyExistsException;
import com.lukasz.yumnow.domain.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerDao customerDao;

    public Customer findByEmail(String email) {
        Optional<Customer> optionalCustomer = customerDao.findByEmail(email);
        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        } else throw new NotFoundException("Cannot find customer with email: [%s]".formatted(email));
    }

    @Transactional
    public Customer create(Customer customer) {

        Optional<Customer> customerOptional = customerDao.findByEmail(customer.getEmail());

        if (customerOptional.isPresent()) {
            throw new AlreadyExistsException("Customer with email: [%s] already exists.".formatted(customer.getEmail()));
        } else {
            return customerDao.create(customer);
        }
    }
}
