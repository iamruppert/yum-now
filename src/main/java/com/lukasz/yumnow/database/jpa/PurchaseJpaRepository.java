package com.lukasz.yumnow.database.jpa;

import com.lukasz.yumnow.database.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseJpaRepository extends JpaRepository<PurchaseEntity, Integer> {
}
