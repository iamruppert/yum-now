package com.lukasz.yumnow.buisness;

import com.lukasz.yumnow.buisness.dao.FoodDao;
import com.lukasz.yumnow.domain.Food;
import com.lukasz.yumnow.domain.Local;
import com.lukasz.yumnow.domain.exception.AlreadyExistsException;
import com.lukasz.yumnow.domain.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FoodService {

    private final FoodDao foodDao;
    private final LocalService localService;

    public Food findByCode(String code) {
        Optional<Food> optionalFood = foodDao.findByCode(code);
        if (optionalFood.isPresent()) {
            return optionalFood.get();
        } else {
            throw new NotFoundException("Cannot find food with code: [%s]".formatted(code));
        }
    }

    public Food findById(Integer id) {
        Optional<Food> optionalFood = foodDao.findById(id);
        if (optionalFood.isPresent()) {
            return optionalFood.get();
        } else {
            throw new NotFoundException("Cannot find food with id: [%s]".formatted(id));
        }
    }

    public Food create(String localName, Food food) {
        Local local = localService.findByName(localName);

        Optional<Food> optionalFood = foodDao.findByCode(food.getCode());

        if (optionalFood.isPresent()) {
            throw new AlreadyExistsException("Food with code: [%s] is already defined in this local"
                    .formatted(food.getCode()));
        } else {

            Food foodToSave = Food.builder()
                    .code(generateCode(localName, food))
                    .name(food.getName())
                    .category(food.getCategory())
                    .description(food.getDescription())
                    .price(food.getPrice())
                    .calories(food.getCalories())
                    .local(local)
                    .foodPurchases(new HashSet<>())
                    .build();

            return foodDao.create(localName, foodToSave);
        }

    }

    private String generateCode(String localName, Food food) {
        return localName + "/" + food.getName();
    }
}
