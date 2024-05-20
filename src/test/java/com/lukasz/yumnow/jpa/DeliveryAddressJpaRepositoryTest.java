package com.lukasz.yumnow.jpa;

import com.lukasz.yumnow.configuration.PersistenceContainerTestConfiguration;
import com.lukasz.yumnow.database.entity.DeliveryAddressEntity;
import com.lukasz.yumnow.database.jpa.DeliveryAddressJpaRepository;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AllArgsConstructor(onConstructor_ = @__(@Autowired))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
public class DeliveryAddressJpaRepositoryTest {

    private DeliveryAddressJpaRepository deliveryAddressJpaRepository;

    @Test
    public void thisTestShouldSaveDeliveryAddressCorrectly(){

        DeliveryAddressEntity deliveryAddressEntity = DeliveryAddressEntity.builder()
                .code("Poland/Lublin/Lubelska")
                .country("Poland")
                .city("Lublin")
                .postalCode("20-999")
                .street("Lubelska")
                .buildingNumber(1)
                .apartmentNumber(1)
                .build();

        deliveryAddressJpaRepository.save(deliveryAddressEntity);

        List<DeliveryAddressEntity> all = deliveryAddressJpaRepository.findAll();

        Assertions.assertEquals(1, all.size());

    }

    @Test
    public void testFindAllByCode() {

        DeliveryAddressEntity deliveryAddressEntity = DeliveryAddressEntity.builder()
                .code("Poland/Lublin/Lubelska")
                .country("Poland")
                .city("Lublin")
                .postalCode("20-999")
                .street("Lubelska")
                .buildingNumber(1)
                .apartmentNumber(1)
                .build();

        DeliveryAddressEntity deliveryAddressEntity2 = DeliveryAddressEntity.builder()
                .code("Poland/Lublin/Lubelska")
                .country("Poland")
                .city("Lublin")
                .postalCode("20-985")
                .street("Lubelska")
                .buildingNumber(12)
                .apartmentNumber(3)
                .build();

        DeliveryAddressEntity deliveryAddressEntity3 = DeliveryAddressEntity.builder()
                .code("Poland/Lublin/Radomska")
                .country("Poland")
                .city("Lublin")
                .postalCode("20-744")
                .street("Radomska")
                .buildingNumber(11)
                .apartmentNumber(3)
                .build();

        deliveryAddressJpaRepository.saveAllAndFlush(Set.of(deliveryAddressEntity, deliveryAddressEntity2, deliveryAddressEntity3));

        List<DeliveryAddressEntity> all = deliveryAddressJpaRepository.findAll();
        List<DeliveryAddressEntity> addresses = deliveryAddressJpaRepository.findAllByCode("Poland/Lublin/Lubelska");

        assertThat(all).hasSize(3);
        assertThat(addresses).hasSize(2);
    }

    @Test
    public void testFindOneByAllFields() {

        DeliveryAddressEntity deliveryAddressEntity1 = DeliveryAddressEntity.builder()
                .code("Poland/Lublin/Radomska")
                .country("Poland")
                .city("Lublin")
                .postalCode("20-744")
                .street("Radomska")
                .buildingNumber(11)
                .apartmentNumber(3)
                .build();

        DeliveryAddressEntity deliveryAddressEntity2 = DeliveryAddressEntity.builder()
                .code("Poland/Lublin/Lubelska")
                .country("Poland")
                .city("Lublin")
                .postalCode("20-985")
                .street("Lubelska")
                .buildingNumber(12)
                .apartmentNumber(3)
                .build();

        deliveryAddressJpaRepository.saveAllAndFlush(Set.of(deliveryAddressEntity1, deliveryAddressEntity2));

        List<DeliveryAddressEntity> all = deliveryAddressJpaRepository.findAll();
        Optional<DeliveryAddressEntity> optionalFoundAddress = deliveryAddressJpaRepository.findOneByAllFields(
                "Poland/Lublin/Lubelska", "Poland",
                "Lublin", "20-985",
                "Lubelska", 12, 3);

        assertThat(all).hasSize(2);
        assertThat(optionalFoundAddress).isNotEmpty();



    }
}
