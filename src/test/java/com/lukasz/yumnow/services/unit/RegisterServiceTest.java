package com.lukasz.yumnow.services.unit;

import com.lukasz.yumnow.buisness.CustomerService;
import com.lukasz.yumnow.buisness.OwnerService;
import com.lukasz.yumnow.buisness.RegisterService;
import com.lukasz.yumnow.domain.Customer;
import com.lukasz.yumnow.domain.Owner;
import com.lukasz.yumnow.domain.RegisterRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RegisterServiceTest {

    @Mock
    private CustomerService customerService;

    @Mock
    private OwnerService ownerService;

    @InjectMocks
    private RegisterService registerService;


    @Test
    public void thisTestShouldSuccessfullyRegisterCustomerFromRegisterRequest() {

        RegisterRequest request = RegisterRequest.builder()
                .name("John")
                .surname("Doe")
                .email("john.doe@example.com")
                .address("123 Main St")
                .accountType("customer")
                .build();

        Customer expectedCustomer = Customer.builder()
                .name("John")
                .surname("Doe")
                .email("john.doe@example.com")
                .address("123 Main St")
                .opinions(new HashSet<>())
                .purchases(new HashSet<>())
                .build();


        String result = registerService.register(request);

        verify(customerService, times(1)).create(expectedCustomer);
        assertEquals("Customer account created successfully", result);
    }

    @Test
    public void thisTestShouldSuccessfullyRegisterOwnerFromRegisterRequest() {

        RegisterRequest request = RegisterRequest.builder()
                .name("John")
                .surname("Doe")
                .email("john.doe@example.com")
                .address("123 Main St")
                .accountType("owner")
                .build();

        Owner expectedOwner = Owner.builder()
                .name("John")
                .surname("Doe")
                .email("john.doe@example.com")
                .address("123 Main St")
                .locals(new HashSet<>())
                .build();


        String result = registerService.register(request);

        verify(ownerService, times(1)).create(expectedOwner);
        assertEquals("Owner account created successfully", result);
    }

    @Test
    public void thisTestShouldFailWhenRegisterRequestWithInvalidAccountType() {

        RegisterRequest request = RegisterRequest.builder()
                .name("John")
                .surname("Doe")
                .email("john.doe@example.com")
                .address("123 Main St")
                .accountType("invalidType")
                .build();

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            registerService.register(request);
        });

        assertEquals("Bad account type provided [invalidType]", exception.getMessage());
    }
}
