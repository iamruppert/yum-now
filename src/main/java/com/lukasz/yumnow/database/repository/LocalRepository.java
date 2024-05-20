package com.lukasz.yumnow.database.repository;

import com.lukasz.yumnow.buisness.dao.LocalDao;
import com.lukasz.yumnow.database.entity.LocalEntity;
import com.lukasz.yumnow.database.entity.OwnerEntity;
import com.lukasz.yumnow.database.jpa.LocalJpaRepository;
import com.lukasz.yumnow.database.jpa.OwnerJpaRepository;
import com.lukasz.yumnow.database.jpa.mapper.LocalMapper;
import com.lukasz.yumnow.database.jpa.mapper.OwnerMapper;
import com.lukasz.yumnow.domain.Local;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class LocalRepository implements LocalDao {

    private final LocalJpaRepository localJpaRepository;
    private final OwnerJpaRepository ownerJpaRepository;
    private final LocalMapper localMapper;
    private final OwnerMapper ownerMapper;



    @Override
    public Optional<Local> findByName(String name) {
        Optional<LocalEntity> optionalLocal = localJpaRepository.findByName(name);
        if(optionalLocal.isPresent()){
            LocalEntity localEntity = optionalLocal.get();
            Local local = localMapper.mapFromEntity(localEntity);
            return Optional.of(local);
        }
        else{
            return Optional.empty();
        }
    }

    @Override
    public Optional<Local> findById(Integer id) {
        Optional<LocalEntity> optionalLocal = localJpaRepository.findById(id);
        if(optionalLocal.isPresent()){
            LocalEntity localEntity = optionalLocal.get();
            Local local = localMapper.mapFromEntity(localEntity);
            return Optional.of(local);
        }
        else{
            return Optional.empty();
        }
    }

    @Override
    public Local create(Local local) {
        OwnerEntity ownerEntity = ownerJpaRepository.findByEmail(local.getOwner().getEmail()).get();
        LocalEntity localEntity = localMapper.mapToEntity(local);
        localEntity.setOwner(ownerEntity);
        LocalEntity saved = localJpaRepository.save(localEntity);
        return localMapper.mapFromEntity(saved);
    }
}
