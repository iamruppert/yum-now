package com.lukasz.yumnow.database.jpa;

import com.lukasz.yumnow.database.entity.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerJpaRepository extends JpaRepository<OwnerEntity,Integer> {

    Optional<OwnerEntity> findOwnerEntityByEmail(String email);
}
