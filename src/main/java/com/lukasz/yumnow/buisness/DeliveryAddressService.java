package com.lukasz.yumnow.buisness;

import com.lukasz.yumnow.buisness.dao.DeliveryAddressDao;
import com.lukasz.yumnow.domain.DeliveryAddress;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DeliveryAddressService {

    private final DeliveryAddressDao deliveryAddressDao;

    public DeliveryAddress create(DeliveryAddress deliveryAddress){

        DeliveryAddress updatedDeliveryAddress = deliveryAddress.withCode(generateDeliveryAddressCode(deliveryAddress));

        List<DeliveryAddress> allByCode = deliveryAddressDao.findAllByCode(updatedDeliveryAddress.getCode());

        if(!allByCode.contains(updatedDeliveryAddress)){

            return deliveryAddressDao.create(updatedDeliveryAddress);
        }

        return deliveryAddress;
    }

    private String generateDeliveryAddressCode(DeliveryAddress deliveryAddress) {
        return
                deliveryAddress.getCountry() + '/' +
                        deliveryAddress.getCity() + '/' +
                        deliveryAddress.getStreet();
    }
}
