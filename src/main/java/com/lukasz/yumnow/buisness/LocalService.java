package com.lukasz.yumnow.buisness;

import com.lukasz.yumnow.buisness.dao.LocalDao;
import com.lukasz.yumnow.domain.Local;
import com.lukasz.yumnow.domain.Owner;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LocalService {

    private final LocalDao localDao;

    private OwnerService ownerService;

    public Local findByName(String name) {
        Optional<Local> optionalLocal = localDao.findByName(name);
        if (optionalLocal.isPresent()) {
            return optionalLocal.get();
        } else {
            throw new RuntimeException("Cannot find local with name: [%s]".formatted(name));
        }
    }

    public Local create(String email, Local local) {
        Optional<Local> optionalLocal = localDao.findByName(local.getName());
        Owner owner = ownerService.findByEmail(email);

        if (optionalLocal.isPresent()) {
            throw new RuntimeException("Local with name [%s] already exists.".formatted(local.getName()));
        } else {

            Local localToSave = Local.builder()
                    .name(local.getName())
                    .address(local.getAddress())
                    .description(local.getDescription())
                    .owner(owner)
                    .opinions(new HashSet<>())
                    .foods(new HashSet<>())
                    .purchases(new HashSet<>())
                    .localDeliveryAddresses(new HashSet<>())
                    .build();

            return localDao.create(localToSave);

        }
    }
}
