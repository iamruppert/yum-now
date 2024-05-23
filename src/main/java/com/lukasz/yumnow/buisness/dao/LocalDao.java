package com.lukasz.yumnow.buisness.dao;


import com.lukasz.yumnow.domain.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface LocalDao {

    Optional<Local> findByName(String name);

    Optional<Local> findById(Integer id);

    Local create(Local local);

    Page<Local> findAll(Pageable pageable);
}
