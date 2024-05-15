package com.lukasz.yumnow.buisness;

import com.lukasz.yumnow.domain.Customer;
import com.lukasz.yumnow.domain.Owner;
import com.lukasz.yumnow.domain.RegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@AllArgsConstructor
public class RegisterService {

    private final CustomerService customerService;
    private final OwnerService ownerService;

    public String register(RegisterRequest registerRequest) {
        if (registerRequest.getAccountType().equals("customer")) {

            Customer customer = Customer.builder()
                    .name(registerRequest.getName())
                    .surname(registerRequest.getSurname())
                    .email(registerRequest.getEmail())
                    .address(registerRequest.getAddress())
                    .opinions(new HashSet<>())
                    .purchases(new HashSet<>())
                    .build();

            customerService.create(customer);
            return "Customer account created successfully";

        } else if (registerRequest.getAccountType().equals("owner")) {

            Owner owner = Owner.builder()
                    .name(registerRequest.getName())
                    .surname(registerRequest.getSurname())
                    .email(registerRequest.getEmail())
                    .address(registerRequest.getAddress())
                    .locals(new HashSet<>())
                    .build();

            ownerService.create(owner);
            return "Owner account created successfully";

        } else {
            throw new RuntimeException("Bad account type provided [%s]"
                    .formatted(registerRequest.getAccountType()));
        }
    }
}
