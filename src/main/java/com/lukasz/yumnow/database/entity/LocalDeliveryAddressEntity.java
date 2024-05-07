package com.lukasz.yumnow.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.Set;

@Entity
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "local_delivery_addess")
public class LocalDeliveryAddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "local_delivery_address_id")
    private Integer localDeliveryAddressId;

    @Column(name = "code")
    private String code;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "street")
    private String street;

    @OneToMany(mappedBy = "localDeliveryAddress")
    private Set<LocalDeliveryAddressLocalEntity> localDeliveryAddressLocals;

}
