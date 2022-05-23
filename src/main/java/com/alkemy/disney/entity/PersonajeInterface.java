package com.alkemy.disney.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonajeInterface extends JpaRepository<Personaje, Long> {

    @Override
    List<Personaje> findAll();

    //... Agregar interfaces necesarias
}
