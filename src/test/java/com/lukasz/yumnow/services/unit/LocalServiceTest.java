package com.lukasz.yumnow.services.unit;

import com.lukasz.yumnow.buisness.LocalService;
import com.lukasz.yumnow.buisness.OwnerService;
import com.lukasz.yumnow.buisness.dao.LocalDao;
import com.lukasz.yumnow.domain.Local;
import com.lukasz.yumnow.domain.Owner;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LocalServiceTest {

    @Mock
    private LocalDao localDao;

    @Mock
    private OwnerService ownerService;

    @InjectMocks
    private LocalService localService;

    @Test
    void thisTestShouldFindExistingLocalByNameCorrectly(){

        String localName = "local1";
        Local local = Local.builder()
                .name(localName)
                .build();

        when(localDao.findByName(localName)).thenReturn(Optional.of(local));

        Local result = localService.findByName(localName);

        Assertions.assertEquals(local, result);
    }

    @Test
    void thisTestFailWileFindingNonExitingLocalByName(){

        String localName = "local1";
        Local local = Local.builder()
                .name(localName)
                .build();

        when(localDao.findByName(localName)).thenReturn(Optional.empty());

        Assertions.assertThrows(RuntimeException.class, ()->localService.findByName(localName));

    }

    @Test
    void thisTestShouldCreateLocalCorrectly(){

        String email = "john.doe@example.com";
        String localName = "local1";

        Owner owner = Owner.builder()
                .email(email)
                .build();

        Local local = Local.builder()
                .name(localName)
                .build();


        when(localDao.findByName(localName)).thenReturn(Optional.empty());
        when(ownerService.findByEmail(email)).thenReturn(owner);
        when(localDao.create(any(Local.class))).thenReturn(local);

        Local result = localService.create(email, local);

        verify(localDao).create(any(Local.class));
        Assertions.assertNotNull(result);
    }

    @Test
    void thisTestShouldFailsWhileCreatingExistingLocal(){

        String email = "john.doe@example.com";
        String localName = "local1";

        Owner owner = Owner.builder()
                .email(email)
                .build();

        Local local = Local.builder()
                .name(localName)
                .build();

        when(localDao.findByName(localName)).thenReturn(Optional.of(local));

        Assert.assertThrows(RuntimeException.class, ()-> localService.create(email, local));

    }
}
