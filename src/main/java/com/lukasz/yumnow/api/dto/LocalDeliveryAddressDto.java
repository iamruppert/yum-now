package com.lukasz.yumnow.api.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocalDeliveryAddressDto {

    String country;
    String city;
    String street;

}
