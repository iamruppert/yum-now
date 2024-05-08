package com.lukasz.yumnow.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.With;

@Entity
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "local_delivery_address_local")
public class LocalDeliveryAddressLocalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "local_delivery_address_local_id")
    private Integer localDeliveryAddressLocalId;

    @ManyToOne
    @JoinColumn(name = "local_id")
    private LocalEntity local;

    @ManyToOne
    @JoinColumn(name = "lcoal_delivery_address_id")
    private LocalDeliveryAddressEntity localDeliveryAddress;
}
