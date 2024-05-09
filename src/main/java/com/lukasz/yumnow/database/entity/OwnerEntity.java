package com.lukasz.yumnow.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Entity
@With
@Builder
@Getter
@Setter
@EqualsAndHashCode(of= "email")
@ToString(of= {"ownerId", "name", "surname","email","address"})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "owner")
public class OwnerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id")
    private Integer ownerId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "owner")
    private Set<LocalEntity> locals;
}
