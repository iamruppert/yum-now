package com.lukasz.yumnow.database.jpa.mapper;

import com.lukasz.yumnow.database.entity.OwnerEntity;
import com.lukasz.yumnow.domain.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OwnerMapper {

    Owner mapFromEntity(OwnerEntity entity);

    OwnerEntity mapToEntity(Owner owner);
}
