package com.lukasz.yumnow.database.repository;

import com.lukasz.yumnow.buisness.dao.CustomerDao;
import com.lukasz.yumnow.database.entity.CustomerEntity;
import com.lukasz.yumnow.database.jpa.CustomerJpaRepository;
import com.lukasz.yumnow.database.jpa.mapper.CustomerMapper;
import com.lukasz.yumnow.domain.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class CustomerRepository implements CustomerDao {

    private final CustomerJpaRepository customerJpaRepository;
    private final CustomerMapper customerMapper;

    @Override
    public Customer create(Customer customer) {

        CustomerEntity customerEntity = customerMapper.mapToEntity(customer);
        CustomerEntity saved = customerJpaRepository.save(customerEntity);

        return customerMapper.mapFromEntity(saved);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        Optional<CustomerEntity> customerOptional = customerJpaRepository.findCustomerEntityByEmail(email);
        if (customerOptional.isPresent()) {
            CustomerEntity customerEntityToMap = customerOptional.get();
            Customer customer = customerMapper.mapFromEntity(customerEntityToMap);
            return Optional.of(customer);
        } else return Optional.empty();
    }
}
