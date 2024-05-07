package com.lukasz.yumnow.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.With;

import java.math.BigDecimal;

@Entity
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "confirmation")
public class ConfirmationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "confirmation_id")
    private Integer confirmationId;

    @Column(name = "confirmation_number")
    private String confirmationNumber;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @OneToOne
    @JoinColumn(name = "purchase_id")
    private PurchaseEntity purchase;
}
