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
@Table(name = "local")
public class LocalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "local_id")
    private Integer localId;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private OwnerEntity owner;

    @OneToMany(mappedBy = "local")
    private Set<OpinionEntity> opinions;

    @OneToMany(mappedBy = "local")
    private Set<FoodEntity> foods;

    @OneToMany(mappedBy = "local")
    private Set<PurchaseEntity> purchaseEntitySet;

    @OneToMany(mappedBy = "local")
    private Set<LocalDeliveryAddressLocalEntity> localDeliveryAddressLocals;

}
