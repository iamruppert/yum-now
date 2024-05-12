package com.lukasz.yumnow.database.repository;

import com.lukasz.yumnow.buisness.dao.OwnerDao;
import com.lukasz.yumnow.database.entity.OwnerEntity;
import com.lukasz.yumnow.database.jpa.OwnerJpaRepository;
import com.lukasz.yumnow.database.jpa.mapper.OwnerMapper;
import com.lukasz.yumnow.domain.Owner;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class OwnerRepository implements OwnerDao {


    private final OwnerJpaRepository ownerJpaRepository;
    private final OwnerMapper ownerMapper;

    @Override
    public Owner create(Owner owner) {

        OwnerEntity ownerEntity = ownerMapper.mapToEntity(owner);
        OwnerEntity saved = ownerJpaRepository.save(ownerEntity);

        return ownerMapper.mapFromEntity(saved);
    }

    @Override
    public Optional<Owner> findByEmail(String email) {
        return ownerJpaRepository.findByEmail(email).map(ownerMapper::mapFromEntity);
    }
}
