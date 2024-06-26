package com.lukasz.yumnow.database.jpa;

import com.lukasz.yumnow.database.entity.OwnerEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerJpaRepository extends JpaRepository<OwnerEntity, Integer> {

    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {
                    "locals",
            }
    )
    Optional<OwnerEntity> findByEmail(final @Param("email") String email);
}
