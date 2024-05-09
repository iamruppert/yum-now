package com.lukasz.yumnow.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of= "confirmationNumber")
@ToString(of= {"confirmationId", "confirmationNumber", "totalPrice"})
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
