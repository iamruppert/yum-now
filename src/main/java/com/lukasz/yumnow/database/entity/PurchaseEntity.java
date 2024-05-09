package com.lukasz.yumnow.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Entity
@With
@Builder
@Getter
@Setter
@EqualsAndHashCode(of= "purchaseNumber")
@ToString(of= {"purchaseId", "purchaseNumber", "totalPrice","time","status"})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "purchase")
public class PurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id")
    private Integer purchaseId;

    @Column(name = "purchase_number")
    private String purchaseNumber;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "time")
    private OffsetDateTime time;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "local_id")
    private LocalEntity local;

    @ManyToOne
    @JoinColumn(name = "delivery_addrees_id")
    private DeliveryAddressEntity deliveryAddress;

    @OneToOne(mappedBy = "purchase")
    private ConfirmationEntity confirmation;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @OneToMany(mappedBy = "purchase")
    private Set<FoodPurchaseEntity> foodPurchases;
}
