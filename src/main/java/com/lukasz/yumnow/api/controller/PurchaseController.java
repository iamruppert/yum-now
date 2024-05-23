package com.lukasz.yumnow.api.controller;

import com.lukasz.yumnow.api.dto.PurchaseDto;
import com.lukasz.yumnow.api.dto.mapper.FoodPurchaseDtoMapper;
import com.lukasz.yumnow.buisness.LocalService;
import com.lukasz.yumnow.buisness.PurchaseService;
import com.lukasz.yumnow.domain.FoodPurchase;
import com.lukasz.yumnow.domain.Purchase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PurchaseController {

    private LocalService localService;
    private PurchaseService purchaseService;
    private FoodPurchaseDtoMapper purchaseDtoMapper;


    @PostMapping("/local/{id}/purchase")
    public ResponseEntity<?> createPurchase(
            @PathVariable int id,
            @Valid @RequestBody PurchaseDto purchaseDto
    ) {
        List<FoodPurchase> foodPurchases = purchaseDto.getFoodPurchases().stream()
                .map(purchaseDtoMapper::mapToFoodPurchase)
                .collect(Collectors.toList());

        purchaseService.createPurchase(
                purchaseDto.getEmail(),
                purchaseDto.getCustomer(),
                localService.findById(id).getName(),
                foodPurchases,
                purchaseDto.getDeliveryAddress()
        );

        return ResponseEntity.ok().body("Purchase created successfully.");
    }

    @PostMapping("/customer/purchases/{purchaseId}/cancel")
    public ResponseEntity<?> cancelPurchase(
            @PathVariable int purchaseId
    ) {
        Purchase purchase = purchaseService.findById(purchaseId);
        purchaseService.cancelPurchase(purchase.getPurchaseNumber());
        return ResponseEntity.ok().body("Purchase canceled successfully");
    }

}
