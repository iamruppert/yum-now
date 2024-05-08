package com.lukasz.yumnow.buisness;

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

        //given
        Customer nonExistingCustomer = Customer.builder()
                .name("Peter")
                .surname("Example")
                .email("peter@gmail.com")
                .address("Polna 3")
                .opinions(new HashSet<>())
                .purchases(new HashSet<>())
                .build();

        when(customerDao.findCustomerByEmail(nonExistingCustomer.getEmail())).thenReturn(Optional.empty());
        when(customerDao.create(nonExistingCustomer)).thenReturn(nonExistingCustomer);

        //when
        Customer createdCustomer = customerService.create(nonExistingCustomer);

        //then
        assertEquals(nonExistingCustomer, createdCustomer);
        verify(customerDao, times(1)).findCustomerByEmail(nonExistingCustomer.getEmail());
        verify(customerDao, times(1)).create(nonExistingCustomer);
    }

    @Test
    void shouldFailWhenSavingExistingCustomer() {

        //given
        Customer existingCustomer = Customer.builder()
                .name("Peter")
                .surname("Example")
                .email("peter@gmail.com")
                .address("Polna 3")
                .opinions(new HashSet<>())
                .purchases(new HashSet<>())
                .build();

        when(customerDao.findCustomerByEmail(existingCustomer.getEmail())).thenReturn(Optional.of(existingCustomer));

        //when, then
        assertThrows(RuntimeException.class, () -> customerService.create(existingCustomer));
        verify(customerDao, never()).create(existingCustomer);
    }

}