package com.alkemy.disney.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonajeInterface extends JpaRepository<Personaje, Long> {

    @Override
    List<Personaje> findAll();

    @Override
    void deleteById(Long id);

    @Override
    Optional<Personaje> findById(Long aLong);

    //... Agregar interfaces necesarias
}
