package com.lukasz.yumnow.database.entity;

import com.lukasz.yumnow.domain.Local;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@With
@Builder
@Getter
@Setter
@EqualsAndHashCode(of= "localDeliveryAddressId")
@ToString(of= {"localDeliveryAddressId", "code", "country","city","postalCode","street"})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "local_delivery_address")
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

    @ManyToMany(mappedBy = "localDeliveryAddresses")
    private Set<LocalEntity> locals;

}
