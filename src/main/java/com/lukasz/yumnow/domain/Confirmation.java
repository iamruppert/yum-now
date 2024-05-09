package com.lukasz.yumnow.domain;

import lombok.*;

import java.math.BigDecimal;

@With
@Value
@Builder
@EqualsAndHashCode(of = "confirmationNumber")
@ToString(of = {"confirmationId", "confirmationNumber", "totalPrice"})
public class Confirmation {

    Integer confirmationId;
    String confirmationNumber;
    BigDecimal totalPrice;
    Purchase purchase;

}
