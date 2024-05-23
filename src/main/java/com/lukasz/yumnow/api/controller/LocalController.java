package com.lukasz.yumnow.api.controller;

import com.lukasz.yumnow.api.dto.LocalDeliveryAddressDto;
import com.lukasz.yumnow.api.dto.LocalDto;
import com.lukasz.yumnow.api.dto.LocalRequestDto;
import com.lukasz.yumnow.api.dto.mapper.LocalDeliveryAddressDtoMapper;
import com.lukasz.yumnow.api.dto.mapper.LocalDtoMapper;
import com.lukasz.yumnow.buisness.LocalDeliveryAddressService;
import com.lukasz.yumnow.buisness.LocalService;
import com.lukasz.yumnow.domain.Local;
import com.lukasz.yumnow.domain.LocalDeliveryAddress;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class LocalController {

    private final LocalDeliveryAddressService localDeliveryAddressService;
    private final LocalDeliveryAddressDtoMapper localDeliveryAddressDtoMapper;
    private LocalService localService;
    private LocalDtoMapper localDtoMapper;

    @GetMapping("/locals/find")
    public ResponseEntity<?> getAllLocalsWhereDeliveryAddress(
            @RequestParam String street,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy
    ){

        PageRequest pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Local> localsPage = localService.findAllByDeliveryAddressStreet(street, pageable);
        List<LocalDto> localDtos = localsPage.stream().map(localDtoMapper::map).collect(Collectors.toList());
        return ResponseEntity.ok(localDtos);
    }

    @GetMapping("/locals")
    public ResponseEntity<?> getAllLocals(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Local> localsPage = localService.findAll(pageable);
        List<LocalDto> localDtos = localsPage.stream().map(localDtoMapper::map).collect(Collectors.toList());

        return ResponseEntity.ok(localDtos);
    }

    @PostMapping("/owner/local/create")
    public ResponseEntity<?> createLocal(
            @RequestBody @Valid LocalRequestDto localRequestDto
    ) {
        localService.create(localRequestDto.getEmail(), localRequestDto.getLocal());
        return ResponseEntity.ok().body("Local created successfully.");
    }

    @PostMapping("/owner/local/{localId}/add-delivery-address")
    public ResponseEntity<?> addDeliveryAddress(
            @PathVariable int localId,
            @Valid @RequestBody LocalDeliveryAddressDto localDeliveryAddressDto
    ){
        Local local = localService.findById(localId);
        LocalDeliveryAddress localDeliveryAddress = localDeliveryAddressDtoMapper.map(localDeliveryAddressDto);
        localDeliveryAddressService.create(local.getName(), localDeliveryAddress);
        return ResponseEntity.ok().body("Delivery address added successfully");
    }
}
