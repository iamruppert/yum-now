package com.lukasz.yumnow.buisness.dao;

import com.lukasz.yumnow.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PurchaseDao {

    Purchase create(Purchase purchase);
}
