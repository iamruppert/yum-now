package com.lukasz.yumnow.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@With
@Getter
@Setter
@EqualsAndHashCode(of= "email")
@ToString(of= {"customerId", "name", "surname","email","address"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class CustomerEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "customer")
    private Set<OpinionEntity> opinions;

    @OneToMany(mappedBy = "customer")
    private Set<PurchaseEntity> purchases;
}
