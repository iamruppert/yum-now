package com.lukasz.yumnow.database.jpa;

import com.lukasz.yumnow.database.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PurchaseJpaRepository extends JpaRepository<PurchaseEntity, Integer> {

    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {
                    "local",
                    "deliveryAddress",
                    "confirmation",
                    "customer",
                    "foodPurchases",
            }
    )
    Optional<PurchaseEntity> findByPurchaseNumber(String purchaseNumber);

    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {
                    "local",
                    "deliveryAddress",
                    "confirmation",
                    "customer",
                    "foodPurchases",
            }
    )
    Optional<PurchaseEntity> findById(Integer id);
}
