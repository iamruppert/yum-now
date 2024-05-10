package com.lukasz.yumnow.database.jpa;

import com.lukasz.yumnow.database.entity.LocalDeliveryAddressEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocalDeliveryAddressJpaRepository extends JpaRepository<LocalDeliveryAddressEntity, Integer> {

    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {
                    "locals",
            }
    )
    Optional<LocalDeliveryAddressEntity> findByCode(String code);
}
