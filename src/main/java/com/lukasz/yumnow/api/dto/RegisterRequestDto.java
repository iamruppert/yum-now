package com.lukasz.yumnow.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {

    private String name;
    private String surname;
    private String email;
    private String address;
    private String accountType;

}
