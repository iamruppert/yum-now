package com.lukasz.yumnow.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@With
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = "code")
@ToString(of = {"foodId", "code", "name", "category", "description", "price", "calories"})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food")
public class FoodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private Integer foodId;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "calories")
    private Integer calories;

    @ManyToOne
    @JoinColumn(name = "local_id")
    private LocalEntity local;

    @OneToMany(mappedBy = "food")
    private Set<FoodPurchaseEntity> foodPurchases;
}
