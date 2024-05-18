package com.lukasz.yumnow.jpa;

import com.lukasz.yumnow.configuration.PersistenceContainerTestConfiguration;
import com.lukasz.yumnow.database.entity.OwnerEntity;
import com.lukasz.yumnow.database.jpa.OwnerJpaRepository;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OwnerJpaRepositoryTest {

    private OwnerJpaRepository ownerJpaRepository;

    @Test
    public void thisTestShouldFindOwnerByEmailSuccessfully() {

        OwnerEntity owner = OwnerEntity.builder()
                .name("John")
                .surname("Doe")
                .email("john.doe@example.com")
                .address("123 Main St")
                .build();
        ownerJpaRepository.save(owner);

        Optional<OwnerEntity> foundOwner = ownerJpaRepository.findByEmail("john.doe@example.com");
        List<OwnerEntity> all = ownerJpaRepository.findAll();

        assertThat(all).hasSize(1);
        assertThat(foundOwner).isPresent();
        assertThat(foundOwner.get().getEmail()).isEqualTo("john.doe@example.com");
        assertThat(foundOwner.get().getName()).isEqualTo("John");
    }

    @Test
    public void thisTestShouldFailsWhenFindingOwnerWhenNonExistingEmail() {

        OwnerEntity owner = OwnerEntity.builder()
                .name("John")
                .surname("Doe")
                .email("john.doe@example.com")
                .address("123 Main St")
                .build();
        ownerJpaRepository.save(owner);


        Optional<OwnerEntity> foundOwner = ownerJpaRepository.findByEmail("nonexistent@example.com");
        List<OwnerEntity> all = ownerJpaRepository.findAll();

        assertThat(all).hasSize(1);
        assertThat(foundOwner).isNotPresent();
    }

    @Test
    public void thisTestShouldSaveOwnerSuccessfully() {
        OwnerEntity owner = OwnerEntity.builder()
                .name("Jane")
                .surname("Smith")
                .email("jane.smith@example.com")
                .address("456 Elm St")
                .build();

        OwnerEntity savedOwner = ownerJpaRepository.save(owner);

        assertThat(savedOwner).isNotNull();
        assertThat(savedOwner.getOwnerId()).isNotNull();
        assertThat(savedOwner.getName()).isEqualTo("Jane");
        assertThat(savedOwner.getSurname()).isEqualTo("Smith");
        assertThat(savedOwner.getEmail()).isEqualTo("jane.smith@example.com");
        assertThat(savedOwner.getAddress()).isEqualTo("456 Elm St");

        Optional<OwnerEntity> foundOwner = ownerJpaRepository.findById(savedOwner.getOwnerId());
        assertThat(foundOwner).isPresent();
        assertThat(foundOwner.get()).isEqualTo(savedOwner);
    }

}
