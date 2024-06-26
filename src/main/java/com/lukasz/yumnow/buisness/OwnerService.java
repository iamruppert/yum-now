package com.lukasz.yumnow.buisness;

import com.lukasz.yumnow.buisness.dao.OwnerDao;
import com.lukasz.yumnow.domain.Owner;
import com.lukasz.yumnow.domain.exception.AlreadyExistsException;
import com.lukasz.yumnow.domain.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OwnerService {

    private final OwnerDao ownerDao;

    public Owner findByEmail(String email){
        Optional<Owner> optionalOwner = ownerDao.findByEmail(email);
        if(optionalOwner.isPresent()){
            return optionalOwner.get();
        }
        else {
            throw new NotFoundException("Cannot find owner with email: [%s]".formatted(email));
        }
    }

    @Transactional
    public Owner create(Owner owner){
        Optional<Owner> optionalOwner = ownerDao.findByEmail(owner.getEmail());

        if(optionalOwner.isPresent()){
            throw new AlreadyExistsException("Owner with email: [%s] already exists.".formatted(owner.getEmail()));
        }
        else {
            return ownerDao.create(owner);
        }
    }

}
