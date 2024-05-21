package com.lukasz.yumnow.database.jpa;

import com.lukasz.yumnow.database.entity.DeliveryAddressEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DeliveryAddressJpaRepository extends JpaRepository<DeliveryAddressEntity, Integer> {

    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {
                    "purchases",
            }
    )
    List<DeliveryAddressEntity> findAllByCode(String code);


    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {
                    "purchases",
            }
    )
    @Query("SELECT da FROM DeliveryAddressEntity da " +
            "WHERE da.code = :code " +
            "AND da.country = :country " +
            "AND da.city = :city " +
            "AND da.postalCode = :postalCode " +
            "AND da.street = :street " +
            "AND da.buildingNumber = :buildingNumber " +
            "AND da.apartmentNumber = :apartmentNumber")
    Optional<DeliveryAddressEntity> findOneByAllFields(
            @Param("code") String code,
            @Param("country") String country,
            @Param("city") String city,
            @Param("postalCode") String postalCode,
            @Param("street") String street,
            @Param("buildingNumber") Integer buildingNumber,
            @Param("apartmentNumber") Integer apartmentNumber
    );


}
