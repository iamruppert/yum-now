package com.lukasz.yumnow.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@With
@Builder
@Getter
@Setter
@EqualsAndHashCode(of= "foodPurchaseId")
@ToString(of= {"foodPurchaseId", "quantity", "totalPrice"})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food_purchase")
public class FoodPurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_purchase_id")
    private Integer foodPurchaseId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private PurchaseEntity purchase;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private FoodEntity food;


}
