package com.lukasz.yumnow.api.controller;

import com.lukasz.yumnow.api.dto.LocalDeliveryAddressDto;
import com.lukasz.yumnow.api.dto.LocalRequestDto;
import com.lukasz.yumnow.api.dto.mapper.LocalDeliveryAddressDtoMapper;
import com.lukasz.yumnow.buisness.LocalDeliveryAddressService;
import com.lukasz.yumnow.buisness.LocalService;
import com.lukasz.yumnow.domain.Local;
import com.lukasz.yumnow.domain.LocalDeliveryAddress;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class LocalController {

    private final LocalDeliveryAddressService localDeliveryAddressService;
    private final LocalDeliveryAddressDtoMapper localDeliveryAddressDtoMapper;
    private LocalService localService;

    @PostMapping("/owner/local/create")
    public ResponseEntity<?> createLocal(
            @RequestBody @Valid LocalRequestDto localRequestDto
    ) {

        try {
            localService.create(localRequestDto.getEmail(),localRequestDto.getLocal());
            return ResponseEntity.ok().body("Local created successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/owner/local/{localId}/add-delivery-address")
    public ResponseEntity<?> addDeliveryAddress(
            @PathVariable int localId,
            @Valid @RequestBody LocalDeliveryAddressDto localDeliveryAddressDto
    ){
        try{

            Local local = localService.findById(localId);
            LocalDeliveryAddress localDeliveryAddress = localDeliveryAddressDtoMapper.map(localDeliveryAddressDto);

            localDeliveryAddressService.create(local.getName(),localDeliveryAddress);

            return ResponseEntity.ok().body("Delivery address added successfully");
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
