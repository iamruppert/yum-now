package com.lukasz.yumnow.jpa;

import com.lukasz.yumnow.configuration.PersistenceContainerTestConfiguration;
import com.lukasz.yumnow.database.entity.CustomerEntity;
import com.lukasz.yumnow.database.jpa.CustomerJpaRepository;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerJpaTest {

    private CustomerJpaRepository customerJpaRepository;

    @Test
    public void shouldFindCustomerCorrectly() {

        CustomerEntity customerEntity = CustomerEntity.builder()
                .name("Joe")
                .surname("Tester")
                .email("joe.tester@gmail.com")
                .address("Main Street")
                .build();


        customerJpaRepository.saveAndFlush(customerEntity);

        Optional<CustomerEntity> customerEntityOptional = customerJpaRepository.findCustomerEntityByEmail("joe.tester@gmail.com");

        assertThat(customerEntityOptional).isPresent();
        CustomerEntity foundCustomer = customerEntityOptional.get();
        assertThat(foundCustomer.getEmail()).isEqualTo("joe.tester@gmail.com");
        assertThat(foundCustomer.getName()).isEqualTo("Joe");
        assertThat(foundCustomer.getSurname()).isEqualTo("Tester");
    }

    @Test
    public void shouldSaveCustomerCorrectly() {

        CustomerEntity customerEntity = CustomerEntity.builder()
                .name("Joe")
                .surname("Tester")
                .email("joe.tester@gmail.com")
                .address("Main Street")
                .build();

        CustomerEntity savedEntity = customerJpaRepository.save(customerEntity);

        assertThat(savedEntity.getCustomerId()).isNotNull();
        assertThat(savedEntity.getName()).isEqualTo("Joe");
        assertThat(savedEntity.getSurname()).isEqualTo("Tester");
        assertThat(savedEntity.getEmail()).isEqualTo("joe.tester@gmail.com");
        assertThat(savedEntity.getAddress()).isEqualTo("Main Street");
    }
}
