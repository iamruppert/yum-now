package com.lukasz.yumnow.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@With
@Builder
@Getter
@Setter
@EqualsAndHashCode(of= "localDeliveryAddressLocalId")
@ToString(of= {"localDeliveryAddressLocalId"})
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
    @JoinColumn(name = "local_delivery_address_id")
    private LocalDeliveryAddressEntity localDeliveryAddress;
}
