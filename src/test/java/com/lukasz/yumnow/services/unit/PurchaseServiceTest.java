package com.lukasz.yumnow.services.unit;

import com.lukasz.yumnow.buisness.CustomerService;
import com.lukasz.yumnow.buisness.LocalService;
import com.lukasz.yumnow.buisness.PurchaseService;
import com.lukasz.yumnow.buisness.dao.PurchaseDao;
import com.lukasz.yumnow.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PurchaseServiceTest {

    @Mock
    private PurchaseDao purchaseDao;

    @Mock
    private LocalService localService;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private PurchaseService purchaseService;


    @Test
    void testCreatePurchase() {

        String email = "test@example.com";
        Customer customer = Customer.builder().email(email).build();
        Local local = Local.builder().name("Test Local").localDeliveryAddresses(Set.of(
                LocalDeliveryAddress.builder().code("USA/New York/Main St").build()
        )).build();
        List<FoodPurchase> foods = List.of(
                FoodPurchase.builder().food(Food.builder().price(BigDecimal.valueOf(10)).build()).quantity(2).build(),
                FoodPurchase.builder().food(Food.builder().price(BigDecimal.valueOf(5)).build()).quantity(3).build()
        );
        DeliveryAddress deliveryAddress = DeliveryAddress.builder().country("USA").city("New York").street("Main St").build();

        when(localService.findByName("Test Local")).thenReturn(local);
        when(customerService.findByEmail(email)).thenReturn(customer);

        Purchase expected = Purchase.builder()
                .purchaseNumber(UUID.randomUUID().toString())
                .totalPrice(BigDecimal.valueOf(35))
                .time(OffsetDateTime.now())
                .status("CREATED").local(local)
                .deliveryAddress(deliveryAddress.withCode("USA/New York/Main St"))
                .confirmation(null).customer(customer)
                .foodPurchases(new HashSet<>(foods))
                .build();

        when(purchaseDao.create(any(Purchase.class))).thenReturn(expected);

        Purchase result = purchaseService.createPurchase(email, customer, "Test Local", foods, deliveryAddress);

        assertNotNull(result);
        assertEquals(expected.getCustomer(), result.getCustomer());
        assertEquals(expected.getLocal(), result.getLocal());
        assertEquals(expected.getDeliveryAddress(), result.getDeliveryAddress());
        assertEquals(expected.getTotalPrice(), result.getTotalPrice());
        assertEquals(expected.getFoodPurchases(), result.getFoodPurchases());
        assertEquals(expected.getStatus(), result.getStatus());
        assertNotNull(result.getPurchaseNumber());
        assertNotNull(result.getTime());
    }

    @Test
    void testCreatePurchase_InvalidDeliveryAddress() {

        String email = "test@example.com";
        Customer customer = Customer.builder().email(email).build();
        Local local = Local.builder().name("Test Local").localDeliveryAddresses(Set.of(
                LocalDeliveryAddress.builder().code("USA/New York/Main St").build()
        )).build();
        List<FoodPurchase> foods = List.of(
                FoodPurchase.builder().food(Food.builder().price(BigDecimal.valueOf(10)).build()).quantity(2).build(),
                FoodPurchase.builder().food(Food.builder().price(BigDecimal.valueOf(5)).build()).quantity(3).build()
        );
        DeliveryAddress deliveryAddress = DeliveryAddress.builder().country("USA").city("Los Angeles").street("Main St").build();

        when(localService.findByName("Test Local")).thenReturn(local);
        when(customerService.findByEmail(email)).thenReturn(customer);

        assertThrows(RuntimeException.class, () -> purchaseService.createPurchase(email, customer, "Test Local", foods, deliveryAddress));
    }

    @Test
    void testCancelPurchase() {

        String purchaseNumber = UUID.randomUUID().toString();
        Purchase purchase = Purchase.builder().purchaseNumber(purchaseNumber).time(OffsetDateTime.now()).build();

        when(purchaseDao.findByPurchaseNumber(purchaseNumber)).thenReturn(Optional.of(purchase));

        purchaseService.cancelPurchase(purchaseNumber);

        verify(purchaseDao).cancelPurchase(purchase);
    }

    @Test
    void testCancelPurchaseNotFound() {

        String purchaseNumber = UUID.randomUUID().toString();

        when(purchaseDao.findByPurchaseNumber(purchaseNumber)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> purchaseService.cancelPurchase(purchaseNumber));
    }

    @Test
    void testCancelPurchaseTooLate() {

        String purchaseNumber = UUID.randomUUID().toString();
        Purchase purchase = Purchase.builder().purchaseNumber(purchaseNumber).time(OffsetDateTime.now().minusMinutes(21)).build();

        when(purchaseDao.findByPurchaseNumber(purchaseNumber)).thenReturn(Optional.of(purchase));

        assertThrows(RuntimeException.class, () -> purchaseService.cancelPurchase(purchaseNumber));
    }

}
