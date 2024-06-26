package com.lukasz.yumnow.database.jpa.mapper;

import com.lukasz.yumnow.database.entity.CustomerEntity;
import com.lukasz.yumnow.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {

    @Mapping(target = "purchases", ignore = true)
    Customer mapFromEntity(CustomerEntity entity);

    CustomerEntity mapToEntity(Customer customer);
}
