package com.lukasz.yumnow.database.jpa;

import com.lukasz.yumnow.database.entity.LocalEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
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
                    "localDeliveryAddresses.locals",
            }
    )
    Optional<LocalEntity> findByName(String name);


    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {
                    "owner",
                    "opinions",
                    "foods",
                    "purchases",
                    "localDeliveryAddresses",
                    "localDeliveryAddresses.locals",
            }
    )
    Optional<LocalEntity> findById(Integer id);

    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {
                    "owner",
                    "opinions",
                    "foods",
                    "purchases",
                    "localDeliveryAddresses",
                    "localDeliveryAddresses.locals",
            }
    )
    Page<LocalEntity> findAll(Pageable pageable);
}
