package com.lukasz.yumnow.database.repository;

import com.lukasz.yumnow.buisness.dao.FoodDao;
import com.lukasz.yumnow.database.entity.FoodEntity;
import com.lukasz.yumnow.database.entity.LocalEntity;
import com.lukasz.yumnow.database.jpa.FoodJpaRepository;
import com.lukasz.yumnow.database.jpa.LocalJpaRepository;
import com.lukasz.yumnow.database.jpa.mapper.FoodMapper;
import com.lukasz.yumnow.domain.Food;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class FoodRepository implements FoodDao {

    private final FoodJpaRepository foodJpaRepository;
    private final LocalJpaRepository localJpaRepository;
    private final FoodMapper foodMapper;

    @Override
    public Optional<Food> findByCode(String code) {
        Optional<FoodEntity> optionalFood = foodJpaRepository.findByCode(code);
        if(optionalFood.isPresent()){
            FoodEntity foodEntity = optionalFood.get();
            Food food = foodMapper.mapFromEntity(foodEntity);
            return Optional.of(food);
        }
        else return Optional.empty();
    }

    @Override
    public Optional<Food> findById(Integer id) {
        Optional<FoodEntity> optionalFood = foodJpaRepository.findById(id);
        if(optionalFood.isPresent()){
            FoodEntity foodEntity = optionalFood.get();
            Food food = foodMapper.mapFromEntity(foodEntity);
            return Optional.of(food);
        }
        else return Optional.empty();
    }

    @Override
    public Food create(String name, Food food) {

        LocalEntity localEntity = localJpaRepository.findByName(name).orElseThrow();
        FoodEntity foodEntity = foodMapper.mapToEntity(food);
        foodEntity.setLocal(localEntity);
        FoodEntity saved = foodJpaRepository.save(foodEntity);
        return foodMapper.mapFromEntity(saved);

    }
}
