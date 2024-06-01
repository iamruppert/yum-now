package com.lukasz.yumnow.api.controller;

import com.lukasz.yumnow.api.dto.FoodDto;
import com.lukasz.yumnow.api.dto.mapper.FoodDtoMapper;
import com.lukasz.yumnow.buisness.FoodService;
import com.lukasz.yumnow.buisness.LocalService;
import com.lukasz.yumnow.domain.Food;
import com.lukasz.yumnow.domain.Local;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class FoodController {

    public LocalService localService;
    public FoodService foodService;
    public FoodDtoMapper foodDtoMapper;

    @GetMapping("/local/{id}/food")
    public ResponseEntity<?> getFood(@PathVariable Integer id) {
        Local local = localService.findById(id);
        Set<Food> foods = local.getFoods();
        return ResponseEntity.ok().body(foods);
    }

    @PostMapping("/local/{id}/food/create-food")
    public ResponseEntity<?> addFood(
            @PathVariable Integer id,
            @Valid @RequestBody FoodDto foodDto
    ) {
        Food food = foodDtoMapper.map(foodDto);
        Local local = localService.findById(id);
        foodService.create(local.getName(), food);
        return ResponseEntity.ok("Food successfully added to local with id [%s]".formatted(id));
    }
}
