package com.lukasz.yumnow.database.entity;

import com.lukasz.yumnow.domain.LocalDeliveryAddress;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Setter
@Builder
@Getter
@EqualsAndHashCode(of= "localId")
@ToString(of= {"localId", "name", "address","description"})
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
    private Set<PurchaseEntity> purchases;

    @ManyToMany
    @JoinTable(
            name = "local_local_delivery_address",
            joinColumns = {@JoinColumn(name = "local_delivery_address_id")},
            inverseJoinColumns = {@JoinColumn(name = "local_id")}
    )
    private Set<LocalDeliveryAddressEntity> localDeliveryAddresses;

}
