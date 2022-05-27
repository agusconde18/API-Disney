package com.alkemy.disney.repository;

import com.alkemy.disney.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    @Override
    Optional<Film> findById(Long aLong);

}
