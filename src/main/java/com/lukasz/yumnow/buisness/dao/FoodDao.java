package com.lukasz.yumnow.buisness.dao;

import com.lukasz.yumnow.domain.Food;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface FoodDao {

    Optional<Food> findByCode(String code);

    Optional<Food> findById(Integer id);

    Food create(String name, Food food);
}
