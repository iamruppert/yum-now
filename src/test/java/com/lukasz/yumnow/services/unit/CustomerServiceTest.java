package com.lukasz.yumnow.services.unit;

import com.lukasz.yumnow.buisness.CustomerService;
import com.lukasz.yumnow.buisness.dao.CustomerDao;
import com.lukasz.yumnow.domain.Customer;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class CustomerServiceTest {

    @Mock
    private CustomerDao customerDao;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void shouldSaveCustomerThatDoesntExistCorrectly() {

        Customer nonExistingCustomer = Customer.builder()
                .name("Peter")
                .surname("Example")
                .email("peter@gmail.com")
                .address("Polna 3")
                .opinions(new HashSet<>())
                .purchases(new HashSet<>())
                .build();

        when(customerDao.findByEmail(nonExistingCustomer.getEmail())).thenReturn(Optional.empty());
        when(customerDao.create(nonExistingCustomer)).thenReturn(nonExistingCustomer);

        Customer createdCustomer = customerService.create(nonExistingCustomer);

        assertEquals(nonExistingCustomer, createdCustomer);
        verify(customerDao, times(1)).findByEmail(nonExistingCustomer.getEmail());
        verify(customerDao, times(1)).create(nonExistingCustomer);
    }

    @Test
    void shouldFailWhenSavingExistingCustomer() {

        Customer existingCustomer = Customer.builder()
                .name("Peter")
                .surname("Example")
                .email("peter@gmail.com")
                .address("Polna 3")
                .opinions(new HashSet<>())
                .purchases(new HashSet<>())
                .build();

        when(customerDao.findByEmail(existingCustomer.getEmail())).thenReturn(Optional.of(existingCustomer));

        assertThrows(RuntimeException.class, () -> customerService.create(existingCustomer));
        verify(customerDao, never()).create(existingCustomer);
    }

    @Test
    void shouldFindCustomerByEmailCorrectly() {

        String email = "john.doe@example.com";
        Customer customer = Customer.builder()
                .name("John")
                .surname("Doe")
                .email(email)
                .address("123 Main Street")
                .build();

        when(customerDao.findByEmail(email)).thenReturn(Optional.of(customer));

        Customer foundCustomer = customerService.findByEmail(email);

        verify(customerDao, times(1)).findByEmail(email);

        assertEquals(customer, foundCustomer);
    }

    @Test
    void shouldThrowExceptionWhenCustomerNotFoundByEmail() {

        String email = "john.doe@example.com";

        when(customerDao.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(
                RuntimeException.class,
                () -> customerService.findByEmail(email),
                "Cannot find customer with email: [%s]".formatted(email)
        );

        verify(customerDao, times(1)).findByEmail(email);
    }

}