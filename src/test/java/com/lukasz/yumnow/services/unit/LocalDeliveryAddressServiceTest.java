package com.lukasz.yumnow.services.unit;

import com.lukasz.yumnow.buisness.LocalDeliveryAddressService;
import com.lukasz.yumnow.buisness.LocalService;
import com.lukasz.yumnow.buisness.dao.LocalDeliveryAddressDao;
import com.lukasz.yumnow.domain.Local;
import com.lukasz.yumnow.domain.LocalDeliveryAddress;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LocalDeliveryAddressServiceTest {

    @Mock
    private LocalDeliveryAddressDao localDeliveryAddressDao;

    @Mock
    private LocalService localService;

    @InjectMocks
    private LocalDeliveryAddressService localDeliveryAddressService;

    @Test
    void thisTestShouldCreateNewLocalDeliveryAddressCorrectly(){

        String localName = "local1";

        Local local = Local.builder()
                .name(localName)
                .localDeliveryAddresses(new HashSet<>())
                .build();

        LocalDeliveryAddress localDeliveryAddress = LocalDeliveryAddress.builder()
                .code("Poland/Lublin/Polna")
                .country("Poland")
                .city("Lublin")
                .street("Polna")
                .build();

        when(localService.findByName(localName)).thenReturn(local);
        when(localDeliveryAddressDao.create(any(Local.class), any(LocalDeliveryAddress.class))).thenReturn(localDeliveryAddress);

        LocalDeliveryAddress result = localDeliveryAddressService.create(localName, localDeliveryAddress);

        Assertions.assertEquals(localDeliveryAddress, result);

        verify(localService,times(1)).findByName(localName);
        verify(localDeliveryAddressDao, times(1)).create(local, localDeliveryAddress);

    }

    @Test
    void testCreateExistingLocalDeliveryAddress() {

        String localName = "local1";
        Local local = Local.builder().name(localName).build();
        LocalDeliveryAddress localDeliveryAddress = LocalDeliveryAddress.builder()
                .code("Poland/Lublin/Polna")
                .country("Poland")
                .city("Lublin")
                .street("Polna")
                .build();

        HashSet<LocalDeliveryAddress> existingDeliveryAddresses = new HashSet<>();
        existingDeliveryAddresses.add(localDeliveryAddress);
        Local updatedLocal = local.withLocalDeliveryAddresses(existingDeliveryAddresses);

        when(localService.findByName(localName)).thenReturn(updatedLocal);

        assertThrows(RuntimeException.class, () -> localDeliveryAddressService.create(localName, localDeliveryAddress));

        verify(localService, times(1)).findByName(localName);
        verify(localDeliveryAddressDao, never()).create(any(Local.class), any(LocalDeliveryAddress.class));
    }
}
