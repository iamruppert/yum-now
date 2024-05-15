package com.lukasz.yumnow.services.unit;

import com.lukasz.yumnow.buisness.DeliveryAddressService;
import com.lukasz.yumnow.buisness.dao.DeliveryAddressDao;
import com.lukasz.yumnow.domain.DeliveryAddress;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeliveryAddressTest {

    @Mock
    private DeliveryAddressDao deliveryAddressDao;

    @InjectMocks
    private DeliveryAddressService deliveryAddressService;


    @Test
    void thisTestShouldSuccessfullyCreateDeliveryAddress() {
        DeliveryAddress deliveryAddress = DeliveryAddress.builder()
                .country("Poland")
                .city("Lublin")
                .street("Polna 4")
                .build();

        DeliveryAddress updatedDeliveryAddress = deliveryAddress.withCode("Poland/Lublin/Polna 4");

        when(deliveryAddressDao.findAllByCode(updatedDeliveryAddress.getCode()))
                .thenReturn(Collections.emptyList());
        when(deliveryAddressDao.create(updatedDeliveryAddress))
                .thenReturn(updatedDeliveryAddress);


        DeliveryAddress result = deliveryAddressService.create(deliveryAddress);

        assertEquals(updatedDeliveryAddress, result);

        verify(deliveryAddressDao, times(1)).findAllByCode(updatedDeliveryAddress.getCode());
        verify(deliveryAddressDao, times(1)).create(updatedDeliveryAddress);
    }

}
