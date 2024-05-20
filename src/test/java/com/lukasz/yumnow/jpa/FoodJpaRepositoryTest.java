package com.lukasz.yumnow.jpa;


import com.lukasz.yumnow.configuration.PersistenceContainerTestConfiguration;
import com.lukasz.yumnow.database.entity.FoodEntity;
import com.lukasz.yumnow.database.entity.LocalEntity;
import com.lukasz.yumnow.database.entity.OwnerEntity;
import com.lukasz.yumnow.database.jpa.FoodJpaRepository;
import com.lukasz.yumnow.database.jpa.LocalJpaRepository;
import com.lukasz.yumnow.database.jpa.OwnerJpaRepository;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AllArgsConstructor(onConstructor_ = @__(@Autowired))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
public class FoodJpaRepositoryTest {

    private FoodJpaRepository foodJpaRepository;

    private LocalJpaRepository localJpaRepository;

    private OwnerJpaRepository ownerJpaRepository;


    @Test
    public void thisTestShouldFindFoodByCodeCorrectly() {

        OwnerEntity ownerEntity = OwnerEntity.builder()
                .name("Peter")
                .surname("Example")
                .email("peter@gmail.com")
                .address("Main Street 3")
                .build();

        ownerJpaRepository.save(ownerEntity);

        LocalEntity localEntity = LocalEntity.builder()
                .name("LocalName")
                .address("LocalAddress")
                .description("this is new description")
                .owner(ownerEntity)
                .build();

        localJpaRepository.save(localEntity);

        FoodEntity foodEntity = FoodEntity.builder()
                .code("FOOD123")
                .name("FoodName")
                .category("FoodCategory")
                .description("FoodDescription")
                .price(new BigDecimal("9.99"))
                .calories(200)
                .local(localEntity)
                .build();

        foodJpaRepository.save(foodEntity);

        // When
        Optional<FoodEntity> foundEntity = foodJpaRepository.findByCode("FOOD123");

        // Then
        assertThat(foundEntity).isNotEmpty();
        FoodEntity foundFood = foundEntity.get();
        assertThat(foundFood.getCode()).isEqualTo("FOOD123");
        assertThat(foundFood.getName()).isEqualTo("FoodName");
        assertThat(foundFood.getCategory()).isEqualTo("FoodCategory");
        assertThat(foundFood.getDescription()).isEqualTo("FoodDescription");
        assertThat(foundFood.getPrice()).isEqualTo(new BigDecimal("9.99"));
        assertThat(foundFood.getCalories()).isEqualTo(200);
        assertThat(foundFood.getLocal().getName()).isEqualTo("LocalName");
    }
}
