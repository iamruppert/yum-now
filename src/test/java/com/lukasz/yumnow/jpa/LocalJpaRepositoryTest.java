package com.lukasz.yumnow.jpa;

import com.lukasz.yumnow.configuration.PersistenceContainerTestConfiguration;
import com.lukasz.yumnow.database.entity.LocalEntity;
import com.lukasz.yumnow.database.entity.OwnerEntity;
import com.lukasz.yumnow.database.jpa.LocalJpaRepository;
import com.lukasz.yumnow.database.jpa.OwnerJpaRepository;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AllArgsConstructor(onConstructor_ = @__(@Autowired))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
public class LocalJpaRepositoryTest {

    private LocalJpaRepository localJpaRepository;

    private OwnerJpaRepository ownerJpaRepository;

    @Test
    public void thisTestShouldFindLocalByNameCorrectly() {

        OwnerEntity ownerEntity = OwnerEntity.builder()
                .name("Peter")
                .surname("Example")
                .email("peter@gmail.com")
                .address("Main Street 3")
                .build();

        ownerJpaRepository.save(ownerEntity);

        LocalEntity localEntity = LocalEntity.builder()
                .name("Test Local")
                .address("123 Test St")
                .description("Test Description")
                .owner(ownerEntity)
                .opinions(Set.of())
                .foods(Set.of())
                .purchases(Set.of())
                .localDeliveryAddresses(Set.of())
                .build();

        localJpaRepository.save(localEntity);

        Optional<LocalEntity> foundEntity = localJpaRepository.findByName("Test Local");
        assertThat(foundEntity).isPresent();
        assertThat(foundEntity.get().getName()).isEqualTo(localEntity.getName());
    }

    @Test
    public void thisTestShouldSaveLocalCorrectly() {

        OwnerEntity ownerEntity = OwnerEntity.builder()
                .name("Peter")
                .surname("Example")
                .email("peter@gmail.com")
                .address("Main Street 3")
                .build();

        ownerJpaRepository.save(ownerEntity);

        LocalEntity newLocalEntity = LocalEntity.builder()
                .name("New Local")
                .address("456 New St")
                .description("New Description")
                .owner(ownerEntity)
                .opinions(Set.of())
                .foods(Set.of())
                .purchases(Set.of())
                .localDeliveryAddresses(Set.of())
                .build();

        LocalEntity savedEntity = localJpaRepository.save(newLocalEntity);
        assertThat(savedEntity).isNotNull();
        assertThat(savedEntity.getName()).isEqualTo("New Local");
    }
}
