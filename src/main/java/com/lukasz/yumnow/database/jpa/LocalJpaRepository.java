package com.lukasz.yumnow.database.jpa;

import com.lukasz.yumnow.database.entity.LocalEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocalJpaRepository extends JpaRepository<LocalEntity, Integer> {

    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {
                    "owner",
                    "opinions",
                    "foods",
                    "purchases",
                    "localDeliveryAddresses",
            }
    )
    Optional<LocalEntity> findByName(String name);
}
