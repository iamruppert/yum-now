package com.lukasz.yumnow.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "localDeliveryAddressLocalId")
@ToString(of = {"localDeliveryAddressLocalId"})
public class LocalDeliveryAddressLocal {

    Integer localDeliveryAddressLocalId;
    Local local;
    LocalDeliveryAddress localDeliveryAddress;
}
