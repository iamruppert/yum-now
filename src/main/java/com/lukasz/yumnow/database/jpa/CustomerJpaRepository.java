package com.lukasz.yumnow.database.jpa;

import com.lukasz.yumnow.database.entity.CustomerEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Integer> {

    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {
                    "opinions",
                    "purchases",
            }
    )
    Optional<CustomerEntity> findCustomerEntityByEmail(String email);
}
