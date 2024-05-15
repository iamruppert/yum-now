package com.lukasz.yumnow.services.unit;

import com.lukasz.yumnow.buisness.OwnerService;
import com.lukasz.yumnow.buisness.dao.OwnerDao;
import com.lukasz.yumnow.domain.Owner;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
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
class OwnerServiceTest {

    @Mock
    private OwnerDao ownerDao;

    @InjectMocks
    private OwnerService ownerService;


    @Test
    void thisTestShouldFindExistingCustomerCorrectly() {

        String email = "peter@gmail.com";
        Owner owner = Owner.builder()
                .email(email)
                .build();

        when(ownerDao.findByEmail(email)).thenReturn(Optional.of(owner));

        Owner result = ownerService.findByEmail(email);

        Assertions.assertEquals(owner, result);

    }

    @Test
    void thisTestShouldFailWhileFindingNonExistingCustomer() {

        String email = "peter@gmail.com";
        Owner owner = Owner.builder()
                .email(email)
                .build();

        when(ownerDao.findByEmail(email)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> ownerService.findByEmail(email));
    }

    @Test
    void shouldSaveOwnerThatDoesntExistCorrectly() {

        Owner nonExistingOwner = Owner.builder()
                .name("Peter")
                .surname("Example")
                .email("peter@gmail.com")
                .address("Polna 3")
                .locals(new HashSet<>())
                .build();

        when(ownerDao.findByEmail(nonExistingOwner.getEmail())).thenReturn(Optional.empty());
        when(ownerDao.create(nonExistingOwner)).thenReturn(nonExistingOwner);

        //when
        Owner createdOwner = ownerService.create(nonExistingOwner);

        //then
        assertEquals(nonExistingOwner, createdOwner);
        verify(ownerDao, times(1)).findByEmail(nonExistingOwner.getEmail());
        verify(ownerDao, times(1)).create(nonExistingOwner);

    }

    @Test
    void shouldFailWhenSavingExistingOwner() {
        //given
        Owner existingOwner = Owner.builder()
                .name("Peter")
                .surname("Example")
                .email("peter@gmail.com")
                .address("Polna 3")
                .build();

        when(ownerDao.findByEmail(existingOwner.getEmail())).thenReturn(Optional.of(existingOwner));

        //when, then
        assertThrows(RuntimeException.class, () -> ownerService.create(existingOwner));
        verify(ownerDao, never()).create(existingOwner);
    }
}