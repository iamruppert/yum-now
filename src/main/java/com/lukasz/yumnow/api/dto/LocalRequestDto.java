package com.lukasz.yumnow.api.dto;

import com.lukasz.yumnow.domain.Local;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocalRequestDto {
    String email;
    Local local;
}
