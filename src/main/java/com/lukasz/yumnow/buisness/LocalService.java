package com.lukasz.yumnow.buisness;

import com.lukasz.yumnow.buisness.dao.LocalDao;
import com.lukasz.yumnow.domain.Local;
import com.lukasz.yumnow.domain.Owner;
import com.lukasz.yumnow.domain.exception.AlreadyExistsException;
import com.lukasz.yumnow.domain.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
            throw new NotFoundException("Cannot find local with name: [%s]".formatted(name));
        }
    }

    public Local findById(Integer id) {
        Optional<Local> optionalLocal = localDao.findById(id);
        if (optionalLocal.isPresent()) {
            return optionalLocal.get();
        } else {
            throw new NotFoundException("Cannot find local with id: [%s]".formatted(id));
        }
    }

    public Local create(String email, Local local) {
        Optional<Local> optionalLocal = localDao.findByName(local.getName());
        Owner owner = ownerService.findByEmail(email);

        if (optionalLocal.isPresent()) {
            throw new AlreadyExistsException("Local with name [%s] already exists.".formatted(local.getName()));
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

    public Page<Local> findAll(Pageable pageable) {
        return localDao.findAll(pageable);
    }
    public Page<Local> findAllByDeliveryAddressStreet(String street, Pageable pageable){
        return localDao.findAllByDeliveryAddressStreet(street, pageable);
    }
}
