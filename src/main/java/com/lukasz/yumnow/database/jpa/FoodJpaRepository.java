package com.lukasz.yumnow.database.jpa;

import com.lukasz.yumnow.database.entity.FoodEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodJpaRepository extends JpaRepository<FoodEntity, Integer> {

    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {
                    "local",
                    "foodPurchases"
            }
    )
    Optional<FoodEntity> findByCode(String code);

    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {
                    "local",
                    "foodPurchases"
            }
    )
    Optional<FoodEntity> findById(Integer id);

}
