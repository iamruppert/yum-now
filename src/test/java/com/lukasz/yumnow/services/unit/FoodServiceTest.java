package com.lukasz.yumnow.services.unit;

import com.lukasz.yumnow.buisness.FoodService;
import com.lukasz.yumnow.buisness.LocalService;
import com.lukasz.yumnow.buisness.dao.FoodDao;
import com.lukasz.yumnow.domain.Food;
import com.lukasz.yumnow.domain.Local;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FoodServiceTest {

    @Mock
    private FoodDao foodDao;

    @Mock
    private LocalService localService;

    @InjectMocks
    private FoodService foodService;

    @Test
    void thisTestShouldFindByCodeExistingFoodCorrectly() {

        String code = "some local 1/burger with fries";

        Food expectedFood = Food.builder()
                .code(code)
                .build();

        when(foodDao.findByCode(code)).thenReturn(Optional.of(expectedFood));

        Food result = foodService.findByCode(code);

        Assertions.assertEquals(expectedFood, result);

        verify(foodDao, times(1)).findByCode(code);

    }

    @Test
    void thisTestShouldThrowExceptionWhileFindingByNonExistingCode() {


        String code = "some local 1/burger with fries";

        when(foodDao.findByCode(code)).thenReturn(Optional.empty());

        Assertions.assertThrows(RuntimeException.class, () -> foodService.findByCode(code));

        verify(foodDao, times(1)).findByCode(code);

    }

    @Test
    void thisTestShouldCreateNonExistingFoodSuccessfully() {

        String localName = "local1";
        String foodName = "burger";
        String foodCode = "local1/burger";

        Food food = Food.builder()
                .code(foodCode)
                .name(foodName)
                .build();

        Local local = Local.builder()
                .name(localName)
                .build();

        when(localService.findByName(localName)).thenReturn(local);
        when(foodDao.findByCode(food.getCode())).thenReturn(Optional.empty());
        when(foodDao.create(localName, food)).thenReturn(food);

        Food result = foodService.create(localName, food);

        Assertions.assertEquals(food, result);

        verify(localService, times(1)).findByName(localName);
        verify(foodDao, times(1)).findByCode(food.getCode());
        verify(foodDao, times(1)).create(localName, food);
    }

    @Test
    void thisTestShouldFailWhenCreatingExistingFood() {

        String localName = "local1";
        String foodName = "burger";
        String foodCode = "local1/burger";

        Food food = Food.builder()
                .code(foodCode)
                .name(foodName)
                .build();

        Local local = Local.builder()
                .name(localName)
                .build();

        when(localService.findByName(localName)).thenReturn(local);
        when(foodDao.findByCode(food.getCode())).thenReturn(Optional.of(food));

        Assertions.assertThrows(RuntimeException.class, () -> foodService.create(localName, food));

        verify(localService, times(1)).findByName(localName);
        verify(foodDao, times(1)).findByCode(food.getCode());
        verify(foodDao, never()).create(localName, food);
    }
}
