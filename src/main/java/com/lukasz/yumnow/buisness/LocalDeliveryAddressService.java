package com.lukasz.yumnow.buisness;

import com.lukasz.yumnow.buisness.dao.LocalDeliveryAddressDao;
import com.lukasz.yumnow.domain.Local;
import com.lukasz.yumnow.domain.LocalDeliveryAddress;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class LocalDeliveryAddressService {

    private final LocalDeliveryAddressDao localDeliveryAddressDao;
    private LocalService localService;

    public LocalDeliveryAddress create(String name, LocalDeliveryAddress localDeliveryAddress) {

        Local local = localService.findByName(name);

        LocalDeliveryAddress deliveryAddressToSave = LocalDeliveryAddress.builder()
                .code(generateDeliveryAddressCode(localDeliveryAddress))
                .country(localDeliveryAddress.getCountry())
                .city(localDeliveryAddress.getCity())
                .street(localDeliveryAddress.getStreet())
                .locals(new HashSet<>())
                .build();

        List<LocalDeliveryAddress> list = local.getLocalDeliveryAddresses().stream()
                .filter(address -> address.getCode().equals(deliveryAddressToSave.getCode()))
                .toList();
        if(list.isEmpty()){
            return localDeliveryAddressDao.create(local,deliveryAddressToSave);
        }
        else{
            throw new RuntimeException("This delivery address code [%s] is already in the local's delivery addresses list".formatted(
                    deliveryAddressToSave.getCode()
            ));
        }

    }

    private String generateDeliveryAddressCode(LocalDeliveryAddress deliveryAddress) {
        return
                deliveryAddress.getCountry()+'/'+
                deliveryAddress.getCity()+'/'+
                deliveryAddress.getStreet();
    }
}
