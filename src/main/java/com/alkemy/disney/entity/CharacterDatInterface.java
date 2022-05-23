package com.alkemy.disney.entity;

import com.alkemy.disney.entity.CharacterDat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CharacterDatInterface extends JpaRepository<CharacterDat, Long> {

    @Override
    List<CharacterDat> findAll();

    @Override
    void deleteById(Long id);

    @Override
    Optional<CharacterDat> findById(Long aLong);

    //... Agregar interfaces necesarias
}
