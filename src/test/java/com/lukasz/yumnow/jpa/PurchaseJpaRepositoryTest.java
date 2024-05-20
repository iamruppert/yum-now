package com.lukasz.yumnow.jpa;

import com.lukasz.yumnow.configuration.PersistenceContainerTestConfiguration;
import com.lukasz.yumnow.database.entity.*;
import com.lukasz.yumnow.database.jpa.*;
import com.lukasz.yumnow.domain.Owner;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PurchaseJpaRepositoryTest {

    private PurchaseJpaRepository purchaseJpaRepository;
    private LocalJpaRepository localJpaRepository;
    private DeliveryAddressJpaRepository deliveryAddressJpaRepository;
    private CustomerJpaRepository customerJpaRepository;
    @Autowired
    private OwnerJpaRepository ownerJpaRepository;


    @Test
    public void testFindByPurchaseNumber() {

        OwnerEntity owner = OwnerEntity.builder()
                .name("Jan")
                .surname("Polk")
                .email("jan.polk@gmail.com")
                .address("Road 3")
                .locals(new HashSet<>())
                .build();

        ownerJpaRepository.save(owner);


        LocalEntity local = LocalEntity.builder()
                .name("Local Test")
                .address("123 Test St")
                .description("Test Description")
                .owner(owner)
                .build();

        localJpaRepository.save(local);

        DeliveryAddressEntity deliveryAddress = DeliveryAddressEntity.builder()
                .code("12345")
                .country("Test Country")
                .city("Test City")
                .postalCode("12345")
                .street("Test Street")
                .buildingNumber(1)
                .apartmentNumber(1)
                .build();

        deliveryAddressJpaRepository.save(deliveryAddress);

        CustomerEntity customer = CustomerEntity.builder()
                .name("John")
                .surname("Doe")
                .email("john.doe@test.com")
                .address("123 Test St")
                .build();

        customerJpaRepository.save(customer);

        PurchaseEntity purchase = PurchaseEntity.builder()
                .purchaseNumber("12345")
                .totalPrice(BigDecimal.valueOf(100))
                .time(OffsetDateTime.now())
                .status("NEW")
                .local(local)
                .deliveryAddress(deliveryAddress)
                .customer(customer)
                .build();

        local.setPurchases(Set.of(purchase));
        deliveryAddress.setPurchases(Set.of(purchase));
        customer.setPurchases(Set.of(purchase));

        purchaseJpaRepository.save(purchase);

        Optional<PurchaseEntity> foundPurchase = purchaseJpaRepository.findByPurchaseNumber("12345");

        assertThat(foundPurchase).isPresent();
        assertThat(foundPurchase.get().getPurchaseNumber()).isEqualTo("12345");
        assertThat(foundPurchase.get().getLocal()).isEqualTo(local);
        assertThat(foundPurchase.get().getDeliveryAddress()).isEqualTo(deliveryAddress);
        assertThat(foundPurchase.get().getCustomer()).isEqualTo(customer);
    }


}
