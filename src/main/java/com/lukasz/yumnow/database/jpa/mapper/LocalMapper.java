package com.lukasz.yumnow.database.jpa.mapper;

import com.lukasz.yumnow.database.entity.*;
import com.lukasz.yumnow.domain.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LocalMapper {

    @Mapping(target = "owner", qualifiedByName = "mapOwner")
    @Mapping(target = "opinions", qualifiedByName = "mapOpinions")
    @Mapping(source= "foods",target = "foods", qualifiedByName = "mapFood")
    @Mapping(source = "purchases", target = "purchases", qualifiedByName = "mapPurchase")
    @Mapping(source = "localDeliveryAddresses", target = "localDeliveryAddresses", qualifiedByName = "mapDeliveryAddress")
    Local mapFromEntity(LocalEntity entity);


    @Named("mapOwner")
    default Owner mapOwner(OwnerEntity entity){
        if (entity == null) {
            return null;
        }
        return Owner.builder()
                .ownerId((entity.getOwnerId()))
                .name((entity.getName()))
                .surname((entity.getSurname()))
                .email((entity.getEmail()))
                .address((entity.getAddress()))
                .build();
    }

    @Named("mapOpinions")
    default Set<Opinion> mapOpinion(Set<OpinionEntity> entities){
        return entities.stream().map(this::mapOpinionFromEntity).collect(Collectors.toSet());
    }

    @Mapping(target = "local", ignore = true)
    Opinion mapOpinionFromEntity(OpinionEntity opinionEntity);

    @Named("mapFood")
    default Set<Food> mapFood(Set<FoodEntity> entities){
        return entities.stream().map(this::mapFoodFromEntity).collect(Collectors.toSet());
    }

    @Mapping(target = "local", ignore = true)
    @Mapping(target = "foodPurchases", ignore = true)
    Food mapFoodFromEntity(FoodEntity food);

    @Named("mapDeliveryAddress")
    default Set<LocalDeliveryAddress> mapLocalDeliveryAddress(Set<LocalDeliveryAddressEntity> entities) {
        return entities.stream().map(this::mapLocalDeliveryAddressFromEntity).collect(Collectors.toSet());
    }

    @Mapping(target = "locals", ignore = true)
    LocalDeliveryAddress mapLocalDeliveryAddressFromEntity(LocalDeliveryAddressEntity localDeliveryAddressEntity);


    @Named("mapPurchase")
    default Set<Purchase> mapPurchase(Set<PurchaseEntity> entities) {
        return entities.stream().map(this::mapPurchaseFromEntity).collect(Collectors.toSet());
    }

    @Mapping(target = "local", ignore = true)
    @Mapping(target = "deliveryAddress", ignore = true)
    @Mapping(target = "confirmation", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "foodPurchases", ignore = true)
    Purchase mapPurchaseFromEntity(PurchaseEntity purchase);


    LocalEntity mapToEntity(Local local);
}
