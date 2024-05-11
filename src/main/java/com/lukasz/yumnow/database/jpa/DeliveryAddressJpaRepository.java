package com.lukasz.yumnow.database.jpa;

import com.lukasz.yumnow.database.entity.DeliveryAddressEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryAddressJpaRepository extends JpaRepository<DeliveryAddressEntity, Integer> {

    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {
                    "purchases",
            }
    )
    List<DeliveryAddressEntity> findAllByCode(String code);

}
