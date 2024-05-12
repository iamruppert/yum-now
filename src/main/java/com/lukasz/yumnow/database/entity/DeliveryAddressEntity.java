package com.lukasz.yumnow.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@With
@Builder
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of= {
        "code",
        "country",
        "city",
        "postalCode",
        "street",
        "buildingNumber",
        "apartmentNumber"
})
@ToString(of= {"deliveryAddressId", "code", "country","city","postalCode","buildingNumber","apartmentNumber"})
@AllArgsConstructor
@Table(name = "delivery_address")
public class DeliveryAddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_address_id")
    private Integer deliveryAddressId;

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

    @Column(name = "building_number")
    private Integer buildingNumber;

    @Column(name = "apartment_number")
    private Integer apartmentNumber;

    @OneToMany(mappedBy = "deliveryAddress")
    private Set<PurchaseEntity> purchases;
}
